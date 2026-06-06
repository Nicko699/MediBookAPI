package org.medibook.Security.TokenUtils;

import org.medibook.Dto.RefreshTokenDto;
import org.medibook.Exception.NotFoundException;
import org.medibook.Model.RefreshToken;
import org.medibook.Model.User;
import org.medibook.Repository.RefreshTokenRepository;
import org.medibook.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class RefreshTokenUtils {

    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository  userRepository;

    public RefreshTokenUtils(RefreshTokenRepository refreshTokenRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    //Metodo para crear un refresh token
    @Transactional
    public RefreshTokenDto crearRefreshToken(String email) throws NotFoundException {

        User user=userRepository.findUserByEmail(email).orElseThrow(
                ()->new NotFoundException("El usuario con el correo: "+email+" no se encuentra registrado en el sistema"));

        //Busca los refreshTokens del usuario activos
        List<RefreshToken> refreshTokenList = refreshTokenRepository.findRefreshTokenByUserAndActive(user, true);

      //Válida que no vengan la lista vacia
        if (refreshTokenList != null && !refreshTokenList.isEmpty()){

            refreshTokenList.forEach(refreshToken -> refreshToken.setActive(false));

            refreshTokenRepository.saveAll(refreshTokenList);

        }

        RefreshToken refreshToken= new RefreshToken();

        Instant createdAt=Instant.now();
        Instant expiredAt=createdAt.plus(Duration.ofDays(1));

        String refreshTokenId= UUID.randomUUID().toString();
        String newRefreshToken=UUID.randomUUID().toString();
        //Hasheamos el refreshToken
        String refreshTokenEncript=passwordEncoder.encode(newRefreshToken);

        refreshToken.setRefreshTokenId(refreshTokenId);
        refreshToken.setRefreshTokenEncript(refreshTokenEncript);
        refreshToken.setActive(true);
        refreshToken.setCreatedAt(createdAt);
        refreshToken.setExpiredAt(expiredAt);
        refreshToken.setUser(user);

        refreshTokenRepository.save(refreshToken);

        return new RefreshTokenDto(refreshTokenId,newRefreshToken);
    }


}
