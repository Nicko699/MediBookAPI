package org.medibook.Dto.AuditDto;

import org.medibook.Model.AuditAction;
import java.time.Instant;

public class AuditFilterDto {

    private String entityType;
    private AuditAction action;
    private String performedBy;
    private Instant startDate;
    private Instant endDate;

    public AuditFilterDto() {
    }

    public AuditFilterDto(String entityType, AuditAction action, String performedBy, Instant startDate, Instant endDate) {
        this.entityType = entityType;
        this.action = action;
        this.performedBy = performedBy;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }
}
