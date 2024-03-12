package stdy.springstudy.service.profile;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import stdy.springstudy.domain.profile.dto.ProfileRequestDTO;
import stdy.springstudy.domain.profile.dto.ProfileResponseDTO;
import stdy.springstudy.domain.profile.service.ProfileServiceImpl;
import stdy.springstudy.domain.user.dto.UserRequestDTO;
import stdy.springstudy.domain.user.entity.User;
import stdy.springstudy.domain.user.repository.UserRepository;
import stdy.springstudy.domain.user.service.UserService;

@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ProfileServiceImplTest {
    @Autowired
    private ProfileServiceImpl profileService;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Test
    @Transactional
    @DisplayName("프로필 수정 테스트")
    void update() {
        // given
        insertUserJoinDTO();
        ProfileRequestDTO.ProfileUpdateDTO profileUpdateDTO = new ProfileRequestDTO.ProfileUpdateDTO();
        profileUpdateDTO.setProfileImg("updateProfileImg");
        profileUpdateDTO.setNickName("updateNickName");

        // when
        User findUser = userRepository.findById(1L).get();
        profileService.update(profileUpdateDTO, findUser.getProfile().getId());

        // then
        Assertions.assertThat(findUser.getProfile().getNickName()).isEqualTo("updateNickName");
        Assertions.assertThat(findUser.getProfile().getProfileImg()).isEqualTo("updateProfileImg");
    }

    @Test
    @Transactional
    @DisplayName("프로필 조회 테스트")
    void find() {
        // given
        insertUserJoinDTO();

        // when
        User findUser = userRepository.findById(1L).get();
        ProfileResponseDTO.ProfileFindDTO profileFindDTO = profileService.find(findUser.getId());

        // then
        Assertions.assertThat(profileFindDTO.getNickName()).isEqualTo(findUser.getUserName());
        Assertions.assertThat(profileFindDTO.getProfileImg()).isEqualTo("starter");
    }

    private void insertUserJoinDTO() {
        UserRequestDTO.UserJoinDTO userJoinDTO = new UserRequestDTO.UserJoinDTO();
        userJoinDTO.setUserName("test");
        userJoinDTO.setUserEmail("test@test.com");
        userJoinDTO.setUserPassword("password");

        userService.join(userJoinDTO);
    }
}