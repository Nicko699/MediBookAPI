package org.medibook.Specifications;

import jakarta.persistence.criteria.Join;
import org.medibook.Model.Doctor;
import org.medibook.Model.Speciality;
import org.medibook.Model.User;
import org.springframework.data.jpa.domain.Specification;

public interface DoctorSpecification {

    static Specification<Doctor> nameLike(String name) {
        return (root, query, cb) -> {
            if (name == null || name.trim().isEmpty()) {
                return null;
            }

            Join<Doctor, User> userJoin = root.join("user");

            return cb.like(
                    cb.lower(userJoin.get("name")),
                    "%" + name.toLowerCase() + "%"
            );
        };
    }

    static Specification<Doctor> lastNameLike(String lastName) {
        return (root, query, cb) -> {
            if (lastName == null || lastName.trim().isEmpty()) {
                return null;
            }

            Join<Doctor, User> userJoin = root.join("user");

            return cb.like(
                    cb.lower(userJoin.get("lastName")),
                    "%" + lastName.toLowerCase() + "%"
            );
        };
    }

    static Specification<Doctor> specialityLike(String speciality) {
        return (root, query, cb) -> {
            if (speciality == null || speciality.trim().isEmpty()) {
                return null;
            }

            Join<Doctor, Speciality> specialityJoin = root.join("speciality");

            return cb.like(
                    cb.lower(specialityJoin.get("name")),
                    "%" + speciality.toLowerCase() + "%"
            );
        };
    }

    static Specification<Doctor> noDeleted() {
        return (root, query, cb) ->
                cb.equal(root.get("softDelete"), false);
    }
}