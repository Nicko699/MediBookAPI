package org.medibook.Listener;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.servlet.http.HttpServletRequest;
import org.medibook.Config.SpringContextHolder;
import org.medibook.Model.AuditAction;
import org.medibook.Service.Audit.AuditService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Instant;

public class AuditEntityListener {

    @PostPersist
    public void onPostPersist(Object entity) {
        auditChange(entity, AuditAction.CREATE);
    }

    @PostUpdate
    public void onPostUpdate(Object entity) {
        auditChange(entity, AuditAction.UPDATE);
    }

    @PostRemove
    public void onPostRemove(Object entity) {
        auditChange(entity, AuditAction.DELETE);
    }

    private void auditChange(Object entity, AuditAction action) {
        try {
            AuditService auditService = SpringContextHolder.getBean(AuditService.class);

            Long entityId = extractEntityId(entity);
            String entityType = entity.getClass().getSimpleName();
            String performedBy = extractPerformedBy();
            String ipAddress = extractIpAddress();
            String details = buildDetails(entity, action);

            auditService.logAudit(entityId, entityType, action, performedBy, Instant.now(), ipAddress, details);
        } catch (Exception e) {
            System.err.println("Error logging audit: " + e.getMessage());
        }
    }

    private Long extractEntityId(Object entity) {
        try {
            return (Long) entity.getClass().getMethod("getId").invoke(entity);
        } catch (Exception e) {
            return null;
        }
    }

    private String extractPerformedBy() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
            return authentication.getName();
        }
        return "SYSTEM";
    }

    private String extractIpAddress() {
        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            if (requestAttributes instanceof ServletRequestAttributes servletRequestAttributes) {
                HttpServletRequest httpRequest = servletRequestAttributes.getRequest();
                String forwardedFor = httpRequest.getHeader("X-Forwarded-For");
                String ipAddress = (forwardedFor != null && !forwardedFor.isBlank())
                        ? forwardedFor.split(",")[0].trim()
                        : httpRequest.getRemoteAddr();
                return normalizeIpAddress(ipAddress);
            }
        } catch (Exception ignored) {
        }
        return "UNKNOWN";
    }

    private String normalizeIpAddress(String ipAddress) {
        if (ipAddress == null || ipAddress.isBlank()) {
            return "UNKNOWN";
        }

        String normalized = ipAddress.trim();
        if ("0:0:0:0:0:0:0:1".equals(normalized) || "::1".equals(normalized)) {
            return "127.0.0.1";
        }

        return normalized.split(",")[0].trim();
    }

    private String buildDetails(Object entity, AuditAction action) {
        String entityName = translateEntityName(entity.getClass().getSimpleName());

        return switch (action) {
            case CREATE -> "Se ha creado un " + entityName;
            case UPDATE -> "Se ha actualizado un " + entityName;
            case DELETE -> "Se ha eliminado un " + entityName;
            default -> "Se ha realizado una acción sobre un " + entityName;
        };
    }

    private String translateEntityName(String entityName) {
        if (entityName == null || entityName.isEmpty()) {
            return "registro";
        }

        return switch (entityName) {
            case "User" -> "usuario";
            case "Doctor" -> "doctor";
            case "Patient" -> "paciente";
            case "Appointment" -> "cita";
            case "MedicalHistory" -> "historial médico";
            default -> entityName.toLowerCase();
        };
    }
}
