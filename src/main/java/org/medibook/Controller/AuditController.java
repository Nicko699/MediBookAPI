package org.medibook.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.medibook.Dto.AuditDto.AuditResponseDto;
import org.medibook.Exception.BadRequestException;
import org.medibook.Service.Audit.AuditService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/audit")
@Tag(name = "Audit")
public class AuditController {

    private final AuditService auditService;

    public AuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @Operation(summary = "Get audit logs with optional filters and pagination")
    @GetMapping
    public ResponseEntity<Page<AuditResponseDto>> getAudits(
            @RequestParam(required = false) String entityType,
            @RequestParam(required = false) String performedBy,
            @RequestParam(required = false) Long startDate,
            @RequestParam(required = false) Long endDate,
            @ParameterObject Pageable pageable) throws BadRequestException {

        Instant start = startDate != null ? Instant.ofEpochMilli(startDate) : null;
        Instant end = endDate != null ? Instant.ofEpochMilli(endDate) : null;

        Page<AuditResponseDto> audits = auditService.getAudits(entityType, performedBy, start, end, pageable);
        return ResponseEntity.ok(audits);
    }

    @Operation(summary = "Get an audit record by id")
    @GetMapping("/{id}")
    public ResponseEntity<AuditResponseDto> getAuditById(@PathVariable Long id) throws org.medibook.Exception.NotFoundException {
        return ResponseEntity.ok(auditService.getAuditById(id));
    }
}
