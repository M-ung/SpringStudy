package stdy.springstudy.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stdy.springstudy.core.annotation.MyLog;
import stdy.springstudy.core.exception.Exception400;
import stdy.springstudy.core.exception.Exception404;
import stdy.springstudy.core.exception.Exception500;
import stdy.springstudy.dto.user.UserRequestDTO;
import stdy.springstudy.dto.user.UserResponseDTO;
import stdy.springstudy.entitiy.user.User;
import stdy.springstudy.repository.user.UserRepository;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    final private UserRepository userRepository;
    final private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    @Transactional
    @MyLog
    public UserResponseDTO.UserJoinDTO join(UserRequestDTO.UserJoinDTO userJoinDTO) {
        if (userRepository.existsByUserEmail(userJoinDTO.getUserEmail())) {
            throw new Exception400("userEmail", "이미 가입된 이메일 주소입니다.");
        }
        userJoinDTO.setUserPassword(bCryptPasswordEncoder.encode(userJoinDTO.getUserPassword()));

        try {
            User user = userJoinDTO.toEntity();
            userRepository.save(user);
            return new UserResponseDTO.UserJoinDTO(user);
        }catch (Exception e){
            throw new Exception500("회원 가입 실패 : "+e.getMessage());
        }
    }

    @Override
    @MyLog
    @Transactional
    public void delete(String userEmail) {
        try {
            User findUser = userRepository.findByUserEmail(userEmail);
            userRepository.delete(findUser);
        }catch (Exception e){
            throw new Exception500("회원 탈퇴 실패 : "+e.getMessage());
        }
    }

    @Override
    @Transactional
    @MyLog
    public UserResponseDTO.UserUpdateDTO update(UserRequestDTO.UserUpdateDTO userUpdateDTO, String userEmail) {
        if(!userUpdateDTO.getUserEmail().equals(userEmail)) {
            throw new Exception400("userEmail", "회원이 맞지 않습니다.");
        }

        try {
            User findUser = userRepository.findByUserEmail(userEmail);
            findUser.updateName(userUpdateDTO.getUserName());
            findUser.updateRole(userUpdateDTO.getRole());

            return new UserResponseDTO.UserUpdateDTO(findUser);
        }catch (Exception e){
            throw new Exception500("회원 수정 실패 : "+e.getMessage());
        }
    }

    @Override
    @MyLog
    public UserResponseDTO.UserFindDTO find(String userEmail) {
        try {
            User findUser = userRepository.findByUserEmail(userEmail);
            return new UserResponseDTO.UserFindDTO(findUser);
        }catch (Exception e) {
            throw new Exception404("회원 조회 실패 : "+e.getMessage());
        }
    }
}
