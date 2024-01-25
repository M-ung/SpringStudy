package stdy.springstudy.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import stdy.springstudy.entitiy.user.User;
import stdy.springstudy.repository.user.UserRepository;

// http://localhost:8080/login 로그인 요청이 오면
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        System.out.println("PrincipalDetailsService의 loadUserByUsername");
        User userEntity = userRepository.findByUserName(userName);
        return new PrincipalDetails(userEntity);
    }
}
