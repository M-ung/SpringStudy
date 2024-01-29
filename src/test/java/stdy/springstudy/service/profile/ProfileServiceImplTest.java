package stdy.springstudy.service.profile;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import stdy.springstudy.dto.user.UserRequestDTO;
import stdy.springstudy.entitiy.profile.Profile;
import stdy.springstudy.entitiy.user.User;
import stdy.springstudy.repository.profile.ProfileRepository;
import stdy.springstudy.repository.user.UserRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
class ProfileServiceImplTest {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    @Transactional
    @DisplayName("프로필 수정 테스트")
    void update() {
        // given
        UserRequestDTO.UserJoinDTO userJoinDTO = new UserRequestDTO.UserJoinDTO();
        userJoinDTO.setUserEmail("test@example.com");
        userJoinDTO.setUserPassword(bCryptPasswordEncoder.encode("password"));
        userJoinDTO.setUserName("test");
        User user = userJoinDTO.toEntity();
        userRepository.save(user);

        // when
        Profile findProfile = profileRepository.findById(user.getProfile().getId()).get();
        findProfile.setNickName("updateNickName");
        findProfile.setProfileImg("updateProfileImg");

        // then
        Assertions.assertThat(user.getProfile().getNickName()).isEqualTo("updateNickName");
        Assertions.assertThat(user.getProfile().getProfileImg()).isEqualTo("updateProfileImg");
    }

    @Test
    @Transactional
    @DisplayName("프로필 조회 테스트")
    void find() {
        // given
        UserRequestDTO.UserJoinDTO userJoinDTO = new UserRequestDTO.UserJoinDTO();
        userJoinDTO.setUserEmail("test@example.com");
        userJoinDTO.setUserPassword(bCryptPasswordEncoder.encode("password"));
        userJoinDTO.setUserName("test");
        User user = userJoinDTO.toEntity();
        userRepository.save(user);

        // when
        Profile findProfile = profileRepository.findById(user.getProfile().getId()).get();

        // then
        Assertions.assertThat(findProfile.getNickName()).isEqualTo("test");
    }
}