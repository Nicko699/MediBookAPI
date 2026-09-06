package org.medibook.Specifications;

import jakarta.persistence.criteria.Join;
import org.medibook.Model.Patient;
import org.medibook.Model.User;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public interface PatientSpecification {

    static Specification<Patient> nameLike(String name) {
        return (root, query, cb) -> {
            if (name == null || name.trim().isEmpty()) return null;

            Join<Patient, User> userJoin = root.join("user");

            return cb.like(
                    cb.lower(userJoin.get("name")),
                    "%" + name.toLowerCase() + "%"
            );
        };
    }

    static Specification<Patient> lastNameLike(String lastName) {
        return (root, query, cb) -> {
            if (lastName == null || lastName.trim().isEmpty()) return null;

            Join<Patient, User> userJoin = root.join("user");

            return cb.like(
                    cb.lower(userJoin.get("lastName")),
                    "%" + lastName.toLowerCase() + "%"
            );
        };
    }

    static Specification<Patient> dniLike(String dni) {
        return (root, query, cb) -> {
            if (dni == null || dni.trim().isEmpty()) return null;

            return cb.like(
                    root.get("dni"),
                    "%" + dni.trim() + "%"
            );
        };
    }

    static Specification<Patient> genderEqual(String gender) {
        return (root, query, cb) -> {
            if (gender == null || gender.trim().isEmpty()) return null;

            return cb.equal(root.get("gender"), gender);
        };
    }

    static Specification<Patient> birthDateEqual(LocalDate birthDate) {
        return (root, query, cb) -> {
            if (birthDate == null) return null;

            return cb.equal(root.get("birthDate"), birthDate);
        };
    }

    static Specification<Patient> noDeleted() {
        return (root, query, cb) ->
                cb.equal(root.get("softDelete"), false);
    }
}