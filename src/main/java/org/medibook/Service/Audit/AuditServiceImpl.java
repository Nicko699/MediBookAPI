package org.medibook.Service.Audit;

import org.medibook.Dto.AuditDto.AuditResponseDto;
import org.medibook.Exception.BadRequestException;
import org.medibook.Exception.NotFoundException;
import org.medibook.Model.Audit;
import org.medibook.Model.AuditAction;
import org.medibook.Repository.AuditRepository;
import org.medibook.Specifications.AuditSpecification;
import org.medibook.mapper.AuditMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;
    private final AuditMapper auditMapper;

    public AuditServiceImpl(AuditRepository auditRepository, AuditMapper auditMapper) {
        this.auditRepository = auditRepository;
        this.auditMapper = auditMapper;
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void logAudit(Long entityId, String entityType, AuditAction action, String performedBy, Instant performedAt, String ipAddress, String details) {
        Audit audit = new Audit(entityId, entityType, action, performedBy, performedAt, ipAddress, details);
        auditRepository.save(audit);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<AuditResponseDto> getAudits(String entityType, String performedBy, Instant startDate, Instant endDate, Pageable pageable) throws BadRequestException {
        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            throw new BadRequestException("La fecha de inicio no puede ser mayor que la fecha fin.");
        }

        Specification<Audit> spec = Specification.where(AuditSpecification.byEntityType(entityType))
                .and(AuditSpecification.byPerformedBy(performedBy))
                .and(AuditSpecification.byStartDate(startDate))
                .and(AuditSpecification.byEndDate(endDate));

        return auditRepository.findAll(spec, pageable)
                .map(auditMapper::auditToAuditResponseDto);
    }

    @Transactional(readOnly = true)
    @Override
    public AuditResponseDto getAuditById(Long id) throws NotFoundException {
        Audit audit = auditRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontró la auditoría con id: " + id));
        return auditMapper.auditToAuditResponseDto(audit);
    }
}
