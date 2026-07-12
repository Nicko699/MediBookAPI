package org.medibook.Repository;

import org.medibook.Model.Rol;
import org.medibook.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

    Optional<User> findUserByEmail(String email);

    boolean existsByEmailAndSoftDelete(String email, Boolean softDelete);

    long countByListRoles_NameAndActiveAndSoftDelete(String roleName, Boolean active, Boolean softDelete);


}
