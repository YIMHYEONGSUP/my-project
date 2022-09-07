package hyeong.backend.Service;

import hyeong.backend.Repository.UserRepository;
import hyeong.backend.jwt.SecurityUtils;
import hyeong.backend.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl {

    private final UserRepository userRepository;


    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities() {
        return SecurityUtils.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername);
    }
}
