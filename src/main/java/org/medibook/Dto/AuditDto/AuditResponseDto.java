package org.medibook.Dto.AuditDto;

import org.medibook.Model.AuditAction;
import java.time.Instant;

public class AuditResponseDto {

    private Long id;
    private Long entityId;
    private String entityType;
    private AuditAction action;
    private String performedBy;
    private Instant performedAt;
    private String ipAddress;
    private String details;

    public AuditResponseDto() {
    }

    public AuditResponseDto(Long id, Long entityId, String entityType, AuditAction action, String performedBy, Instant performedAt, String ipAddress, String details) {
        this.id = id;
        this.entityId = entityId;
        this.entityType = entityType;
        this.action = action;
        this.performedBy = performedBy;
        this.performedAt = performedAt;
        this.ipAddress = ipAddress;
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public AuditAction getAction() {
        return action;
    }

    public void setAction(AuditAction action) {
        this.action = action;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    public Instant getPerformedAt() {
        return performedAt;
    }

    public void setPerformedAt(Instant performedAt) {
        this.performedAt = performedAt;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
