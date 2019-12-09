package ifntuog.eschool.service;

import java.util.Locale;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import ifntuog.eschool.auxiliary.PasswordResetTokenGenerator;
import ifntuog.eschool.dto.PasswordResetDTO;
import ifntuog.eschool.model.PasswordResetToken;
import ifntuog.eschool.model.User;
import ifntuog.eschool.repository.PasswordResetTokenRepository;
import ifntuog.eschool.repository.UserRepository;
import ifntuog.eschool.security.CustomPasswordEncoder;
import ifntuog.eschool.service.base.EmailServiceBase;
import ifntuog.eschool.service.base.PasswordResetServiceBase;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PasswordResetService implements PasswordResetServiceBase{
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Value("#{messageSource.getMessage(\"password_recovery.email.body\", null, T(java.util.Locale).getDefault())}")
    private String passwordResetEmail;
    
    @Value("#{messageSource.getMessage(\"password_recovery.email.subject\", null, T(java.util.Locale).getDefault())}")
    private String passwordResetEmailSubject;
    
    //TODO change host to appropriate host address
    @Value("${ifntuog.eschool.host}")
    private String host;
    
    @Value("${ifntuog.eschool.password_reset_link_template}")
    private String passwordResetLinkTemplate;
    
    @NonNull
    private MessageSource messageSource;
    
    @NonNull
    private CustomPasswordEncoder passwordEncoder;
    
    @NonNull
    private PasswordResetTokenRepository passwordResetTokenRepo;
    
    @NonNull
    private EmailServiceBase mailService;
    
    @NonNull
    private UserRepository userRepo;
    
    @NonNull
    private PasswordResetTokenRepository passwordTokenRepo;
    
    @Override
    public String trySendPasswordResetEmail(String query) {
        User user = userRepo.findByLoginOrEmail(query, query);
        if (user == null) {
            return messageSource.getMessage("password_recovery.no_user", null, Locale.getDefault());
        }
        
        String email = user.getEmail();
        if (email != null) {
            String token = PasswordResetTokenGenerator.generateToken();
            passwordResetTokenRepo.save(new PasswordResetToken(token, user.getId()));
            mailService.sendHtmlMessage(
                    email,
                    passwordResetEmailSubject,
                    String.format(passwordResetEmail, String.format(passwordResetLinkTemplate, host, token)));
            return messageSource.getMessage("password_recovery.message_send", null, Locale.getDefault());
        } else {
            return messageSource.getMessage("password_recovery.contact_admin", null, Locale.getDefault());
        }
    }

    @Override
    public String tryChangePassword(PasswordResetDTO passwordDTO) {
        Optional<PasswordResetToken> token = passwordTokenRepo.findOptionalByToken(passwordDTO.getToken());
        logger.debug("Recovery token [{}], is present [{}]", passwordDTO.getToken(), token.isPresent());
        if (token.isPresent()) {
            long userId = token.get().getUserId();
            User user = userRepo.getOne((int)userId);
            logger.info("Setting password for userID [{}], user [{}]", userId, user);
            user.setPassword(passwordEncoder.encode(passwordDTO.getPassword()));
            userRepo.save(user);
            passwordTokenRepo.delete(token.get());
            logger.debug("PasswordResetToken removed from database");
            return messageSource.getMessage("password_recovery.password_updated", null, Locale.getDefault());
        } else {
            return messageSource.getMessage("password_recovery.link_expired", null, Locale.getDefault());
        }
    }

}
