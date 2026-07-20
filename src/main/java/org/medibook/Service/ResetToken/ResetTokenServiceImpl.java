package org.medibook.Service.ResetToken;

import org.medibook.Dto.ResetTokenDto.ForgotPasswordRequestDto;
import org.medibook.Dto.ResetTokenDto.ResetPasswordRequestDto;
import org.medibook.Dto.ResetTokenDto.ResetTokenDto;
import org.medibook.Exception.BadRequestException;
import org.medibook.Exception.NotFoundException;
import org.medibook.Model.ResetToken;
import org.medibook.Model.User;
import org.medibook.Repository.ResetTokenRepository;
import org.medibook.Repository.UserRepository;
import org.medibook.Utils.EmailServiceUtils.EmailServiceUtil;
import org.medibook.Utils.TokenUtils.ResetTokenUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;


@Service
public class ResetTokenServiceImpl implements ResetTokenService {

    private final UserRepository userRepository;
    private final ResetTokenUtils resetTokenUtils;
    private final EmailServiceUtil emailService;
    private final ResetTokenRepository resetTokenRepository;
    private final PasswordEncoder passwordEncoder;

    public ResetTokenServiceImpl(UserRepository userRepository, ResetTokenUtils resetTokenUtils, EmailServiceUtil emailService, ResetTokenRepository resetTokenRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.resetTokenUtils = resetTokenUtils;
        this.emailService = emailService;
        this.resetTokenRepository = resetTokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Async
    @Transactional
    @Override
    public void processForgotPassword(ForgotPasswordRequestDto forgotPasswordRequestDto) {

        userRepository.findUserByEmail(forgotPasswordRequestDto.getEmail())
                .ifPresent(user -> {

                    ResetTokenDto resetTokenDto = resetTokenUtils.createResetToken(user);

                    String resetLink = emailService.buildResetLink(
                            resetTokenDto.getResetTokenId(),
                            resetTokenDto.getResetToken());

                    emailService.sendPasswordResetEmail(user.getEmail(), user.getName(), resetLink);
                });
    }

    @Transactional
    @Override
    public void resetPassword(ResetPasswordRequestDto resetPasswordRequestDto) throws NotFoundException, BadRequestException {

        ResetToken resetToken = resetTokenRepository.findByResetTokenId(resetPasswordRequestDto.getResetTokenId())
                .orElseThrow(() -> new NotFoundException("El token de recuperación no existe"));

        if (!resetToken.getActive()) {
            throw new BadRequestException("El token de recuperación ya fue utilizado o no es válido.");
        }

        if (resetToken.getExpiredAt().isBefore(Instant.now())) {
            throw new BadRequestException("El token de recuperación ha expirado.");
        }

        if (!passwordEncoder.matches(resetPasswordRequestDto.getResetToken(), resetToken.getResetTokenEncript())) {
            throw new BadRequestException("El token de recuperación no es válido.");
        }

        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(resetPasswordRequestDto.getNewPassword()));
        userRepository.save(user);

        resetToken.setActive(false);
        resetTokenRepository.save(resetToken);
    }

}


