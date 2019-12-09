package ifntuog.eschool.service;

import ifntuog.eschool.model.User;
import ifntuog.eschool.repository.UserRepository;
import ifntuog.eschool.security.JwtUserFactory;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    @NonNull
    private UserRepository userRepository;

    /**
     * Find user by username in database
     * Returns {@link ifntuog.eschool.security.JwtUser} object
     * @param s Username
     * @return Object of {@link ifntuog.eschool.security.JwtUser}
     * @throws UsernameNotFoundException if no user find with specified username username
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(s);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", s));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}
