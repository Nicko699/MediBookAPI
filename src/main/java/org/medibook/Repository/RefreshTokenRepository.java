package org.medibook.Repository;

import org.medibook.Model.RefreshToken;
import org.medibook.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {


    List<RefreshToken> findRefreshTokenByUserAndActive(User user, Boolean active);



}
