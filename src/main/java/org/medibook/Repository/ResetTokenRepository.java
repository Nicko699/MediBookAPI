package org.medibook.Repository;

import org.medibook.Model.ResetToken;
import org.medibook.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResetTokenRepository extends JpaRepository<ResetToken,Long> {

    Optional<ResetToken> findByUser(User user);

    void deleteByResetTokenId(String resetTokenId);


}
