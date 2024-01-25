package stdy.springstudy.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import stdy.springstudy.dto.user.UserJoinDTO;
import stdy.springstudy.entitiy.user.User;
import stdy.springstudy.repository.user.UserRepository;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    final private UserRepository userRepository;
    final private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public void join(UserJoinDTO userJoinDTO) {
        if (userRepository.existsByUserEmail(userJoinDTO.getUserEmail())) {
            log.info("[log] 회원가입 실패 : 이미 가입된 이메일 주소입니다.");
            throw new RuntimeException("이미 가입된 이메일 주소입니다.");
        }
        userJoinDTO.setUserPassword(bCryptPasswordEncoder.encode(userJoinDTO.getUserPassword()));

        User user = userJoinDTO.toEntity();
        userRepository.save(user);
        log.info("[log] 회원가입 완료 : " + user.getUserEmail() + "   " + user.getUserPassword() + "  " + user.getUserName());
    }

    @Override
    public void delete(String userEmail) {
        User findUser = userRepository.findByUserEmail(userEmail);
        userRepository.delete(findUser);
    }


}
