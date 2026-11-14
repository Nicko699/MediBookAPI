package org.medibook.Specifications;

import org.medibook.Model.Audit;
import org.medibook.Model.AuditAction;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;

public interface AuditSpecification {

    static Specification<Audit> byEntityType(String entityType) {
        return (root, query, cb) -> {
            if (entityType == null || entityType.trim().isEmpty()) return null;
            return cb.equal(root.get("entityType"), entityType);
        };
    }

    static Specification<Audit> byEntityId(Long entityId) {
        return (root, query, cb) -> {
            if (entityId == null) return null;
            return cb.equal(root.get("entityId"), entityId);
        };
    }

    static Specification<Audit> byAction(AuditAction action) {
        return (root, query, cb) -> {
            if (action == null) return null;
            return cb.equal(root.get("action"), action);
        };
    }

    static Specification<Audit> byPerformedBy(String performedBy) {
        return (root, query, cb) -> {
            if (performedBy == null || performedBy.trim().isEmpty()) return null;
            return cb.equal(root.get("performedBy"), performedBy);
        };
    }

    static Specification<Audit> byDateRange(Instant startDate, Instant endDate) {
        return (root, query, cb) -> {
            if (startDate == null || endDate == null) return null;
            return cb.between(root.get("performedAt"), startDate, endDate);
        };
    }

    static Specification<Audit> byStartDate(Instant startDate) {
        return (root, query, cb) -> {
            if (startDate == null) return null;
            return cb.greaterThanOrEqualTo(root.get("performedAt"), startDate);
        };
    }

    static Specification<Audit> byEndDate(Instant endDate) {
        return (root, query, cb) -> {
            if (endDate == null) return null;
            return cb.lessThanOrEqualTo(root.get("performedAt"), endDate);
        };
    }
}
