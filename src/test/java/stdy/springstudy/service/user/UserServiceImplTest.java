package stdy.springstudy.service.user;

import org.springframework.transaction.annotation.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import stdy.springstudy.dto.user.UserRequestDTO;
import stdy.springstudy.dto.user.UserResponseDTO;
import stdy.springstudy.entitiy.user.User;
import stdy.springstudy.repository.user.UserRepository;
import stdy.springstudy.repository.user.UserRepositoryImpl;
import java.util.NoSuchElementException;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
@SpringBootTest
@RunWith(SpringRunner.class)
class UserServiceImplTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRepositoryImpl userRepositoryImpl;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Test
    @Transactional
    @DisplayName("회원 가입 테스트")
    void join() throws Exception {
        // given
        UserRequestDTO.UserJoinDTO userJoinDTO = new UserRequestDTO.UserJoinDTO();
        userJoinDTO.setUserEmail("test@example.com");
        userJoinDTO.setUserPassword(bCryptPasswordEncoder.encode("password"));
        userJoinDTO.setUserName("test");
        User user = userJoinDTO.toEntity();
        userRepository.save(user);

        // when
        User findUser = userRepository.findByUserEmail(user.getUserEmail());

        // then
        Assertions.assertThat(findUser).isEqualTo(user);
    }

    @Test
    @Transactional
    @DisplayName("회원 삭제 테스트")
    void delete() throws Exception {
        // given
        UserRequestDTO.UserJoinDTO userJoinDTO = new UserRequestDTO.UserJoinDTO();
        userJoinDTO.setUserEmail("test@example.com");
        userJoinDTO.setUserPassword(bCryptPasswordEncoder.encode("password"));
        userJoinDTO.setUserName("test");
        User user = userJoinDTO.toEntity();
        userRepository.save(user);

        // when
        userRepository.delete(user);
        Optional<User> findUser = userRepository.findById(user.getId());

        // then
        assertThatThrownBy(() -> findUser.get())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @Transactional
    @DisplayName("회원 수정 테스트")
    void update() throws Exception {
        // given
        UserRequestDTO.UserJoinDTO userJoinDTO = new UserRequestDTO.UserJoinDTO();
        userJoinDTO.setUserEmail("test@example.com");
        userJoinDTO.setUserPassword(bCryptPasswordEncoder.encode("password"));
        userJoinDTO.setUserName("test");
        User user = userJoinDTO.toEntity();
        userRepository.save(user);

        // when
        user.updateName("updateUser");

        // then
        Assertions.assertThat(user.getUserName()).isEqualTo("updateUser");
    }

    @Test
    @Transactional
    @DisplayName("회원 조회 테스트")
    void find() throws Exception {
        // given
        UserRequestDTO.UserJoinDTO userJoinDTO = new UserRequestDTO.UserJoinDTO();
        userJoinDTO.setUserEmail("test@example.com");
        userJoinDTO.setUserPassword(bCryptPasswordEncoder.encode("password"));
        userJoinDTO.setUserName("test");
        User user = userJoinDTO.toEntity();
        userRepository.save(user);

        // when
        UserResponseDTO.UserFindDTO findUser = userRepositoryImpl.findUserWithProfileByEmail(user.getUserEmail());

        // then
        Assertions.assertThat(user.getUserEmail()).isEqualTo(findUser.getUserEmail());
    }

    @Test
    @DisplayName("없는 회원 조회 예외 테스트")
    @Transactional
    public void notExistUserFindTest() throws Exception {
        // given
        Long userNo = 99L;

        // when
        Optional<User> findUser = userRepository.findById(userNo);

        // then
        assertThatThrownBy(() -> findUser.get())
                .isInstanceOf(NoSuchElementException.class);

    }
}