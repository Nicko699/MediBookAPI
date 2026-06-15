package org.medibook.Utils.TokenUtils;

import org.medibook.Dto.ResetTokenDto.ResetTokenDto;
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

        ResetToken resetToken = resetTokenRepository.findByUser(user)
                .orElseGet(ResetToken::new);

        String resetTokenS = UUID.randomUUID().toString();
        String resetTokenId = UUID.randomUUID().toString();
        String resetTokenEncript = passwordEncoder.encode(resetTokenS);

        resetToken.setResetTokenId(resetTokenId);
        resetToken.setResetTokenEncript(resetTokenEncript);
        resetToken.setActive(true);
        resetToken.setCreatedAt(Instant.now());
        resetToken.setExpiredAt(Instant.now().plus(Duration.ofMinutes(10)));
        resetToken.setUser(user);

        resetTokenRepository.save(resetToken);

        return new ResetTokenDto(resetTokenId, resetTokenS);
    }
}
