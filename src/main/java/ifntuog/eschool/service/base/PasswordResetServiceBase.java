package ifntuog.eschool.service.base;

import ifntuog.eschool.dto.PasswordResetDTO;

public interface PasswordResetServiceBase {
    String trySendPasswordResetEmail(String username);
    String tryChangePassword(PasswordResetDTO passwordDTO);
}
