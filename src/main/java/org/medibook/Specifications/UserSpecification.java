package org.medibook.Specifications;

import jakarta.persistence.criteria.Join;
import org.medibook.Model.Rol;
import org.medibook.Model.User;
import org.springframework.data.jpa.domain.Specification;

public interface UserSpecification {

        public static Specification<User> nombreLike(String nombre) {
            return (root, query, cb) -> {
                if (nombre == null || nombre.trim().isEmpty()) return null;
                return cb.like(cb.lower(root.get("nombre")), "%" + nombre.toLowerCase() + "%");
            };
        }

        public static Specification<User> activoEqual(Boolean activo) {
            return (root, query, cb) -> {
                if (activo == null) return null;
                return cb.equal(root.get("activo"), activo);
            };
        }

        public static Specification<User> rolEqual(String rol) {
            return (root, query, cb) -> {
                if (rol == null) return null;
                Join<User, Rol> rolesJoin = root.join("listaRol");
                return cb.equal(rolesJoin.get("nombre"), rol);
            };
        }

        public static Specification<User> noEliminados() {
            return (root, query, cb) -> cb.equal(root.get("softDelete"), false);
        }




}
