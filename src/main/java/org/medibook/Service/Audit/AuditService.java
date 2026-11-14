package org.medibook.Service.Audit;

import org.medibook.Dto.AuditDto.AuditResponseDto;
import org.medibook.Exception.BadRequestException;
import org.medibook.Exception.NotFoundException;
import org.medibook.Model.AuditAction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;

public interface AuditService {

    void logAudit(Long entityId, String entityType, AuditAction action, String performedBy, Instant performedAt, String ipAddress, String details);

    Page<AuditResponseDto> getAudits(String entityType, String performedBy, Instant startDate, Instant endDate, Pageable pageable) throws BadRequestException;

    AuditResponseDto getAuditById(Long id) throws NotFoundException;
}
