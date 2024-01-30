package stdy.springstudy.service.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import stdy.springstudy.dto.user.UserRequestDTO;
import stdy.springstudy.dto.user.UserResponseDTO;
import stdy.springstudy.entitiy.user.User;
import stdy.springstudy.repository.user.UserRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserServiceImplTest {
    @Autowired private UserServiceImpl userService;
    @Autowired private UserRepository userRepository;
    @Test
    @Transactional
    @DisplayName("회원 가입 테스트")
    void join() {
        // given
        insertUserJoinDTO();

        // when
        Optional<User> findUser = userRepository.findById(1L);

        // then
        Assertions.assertThat(findUser.get().getUserEmail()).isEqualTo("test@test.com");
    }

    @Test
    @Transactional
    @DisplayName("회원 탈퇴 테스트")
    void delete() {
        // given
        insertUserJoinDTO();

        // when
        Optional<User> findUser1 = userRepository.findById(1L);
        userService.delete(findUser1.get().getUserEmail());
        Optional<User> findUser2 = userRepository.findById(findUser1.get().getId());

        // then
        assertThatThrownBy(() -> findUser2.get())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @Transactional
    @DisplayName("회원 수정 테스트")
    void update() {
        // given
        insertUserJoinDTO();
        UserRequestDTO.UserUpdateDTO userUpdateDTO = new UserRequestDTO.UserUpdateDTO();
        userUpdateDTO.setUserName("updateName");
        userUpdateDTO.setUserEmail("test@test.com");
        userUpdateDTO.setRole("ROLE_MANAGER");

        // when
        Optional<User> findUser = userRepository.findById(1L);
        UserResponseDTO.UserUpdateDTO updateUser = userService.update(userUpdateDTO, findUser.get().getUserEmail());

        // then
        Assertions.assertThat(updateUser.getUserName()).isEqualTo("updateName");
        Assertions.assertThat(updateUser.getRole()).isEqualTo("ROLE_MANAGER");
    }

    @Test
    @Transactional
    @DisplayName("회원 조회 테스트")
    void find() {
        // given
        insertUserJoinDTO();

        // when
        Optional<User> findUser = userRepository.findById(1L);
        UserResponseDTO.UserFindDTO userFindDTO = userService.find(findUser.get().getUserEmail());

        // then
        Assertions.assertThat(userFindDTO.getUserEmail()).isEqualTo("test@test.com");
        Assertions.assertThat(userFindDTO.getUserName()).isEqualTo("test");
    }

    private void insertUserJoinDTO() {
        UserRequestDTO.UserJoinDTO userJoinDTO = new UserRequestDTO.UserJoinDTO();
        userJoinDTO.setUserName("test");
        userJoinDTO.setUserEmail("test@test.com");
        userJoinDTO.setUserPassword("password");

        userService.join(userJoinDTO);
    }
}