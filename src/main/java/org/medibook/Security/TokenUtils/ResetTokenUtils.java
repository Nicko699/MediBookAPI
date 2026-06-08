package org.medibook.Security.TokenUtils;

import org.medibook.Dto.ResetTokenDto;
import org.medibook.Model.ResetToken;
import org.medibook.Model.User;
import org.medibook.Repository.ResetTokenRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class ResetTokenUtils {

    private final ResetTokenRepository resetTokenRepository;
    private final PasswordEncoder passwordEncoder;

    public ResetTokenUtils(ResetTokenRepository resetTokenRepository, PasswordEncoder passwordEncoder) {
        this.resetTokenRepository = resetTokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public ResetTokenDto createResetToken(User user) {

      Optional< ResetToken> resetToken=resetTokenRepository.findByUser(user);

        resetToken.ifPresent(resetTokenGet -> resetTokenRepository
                .deleteByResetTokenId(resetTokenGet.getResetTokenId()));

        ResetToken newResetToken=new ResetToken();

        String resetTokenS= UUID.randomUUID().toString();

        String resetTokenId=UUID.randomUUID().toString();

        String resetTokenEncript=passwordEncoder.encode(resetTokenS);

        newResetToken.setResetTokenId(resetTokenId);
        newResetToken.setResetTokenEncript(resetTokenEncript);
        newResetToken.setActive(true);
        newResetToken.setCreatedAt(Instant.now());
        newResetToken.setExpiredAt(Instant.now().plus(Duration.ofMinutes(10)));
        newResetToken.setUser(user);

        resetTokenRepository.save(newResetToken);

        return new ResetTokenDto(resetTokenId,resetTokenS);
    }
}
