package stdy.springstudy.service.profile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stdy.springstudy.core.annotation.MyLog;
import stdy.springstudy.core.exception.Exception400;
import stdy.springstudy.core.exception.Exception404;
import stdy.springstudy.core.exception.Exception500;
import stdy.springstudy.dto.profile.ProfileRequestDTO;
import stdy.springstudy.dto.profile.ProfileResponseDTO;
import stdy.springstudy.dto.user.UserResponseDTO;
import stdy.springstudy.entitiy.profile.Profile;
import stdy.springstudy.entitiy.user.User;
import stdy.springstudy.repository.profile.ProfileRepository;
import stdy.springstudy.repository.user.UserRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ProfileServiceImpl implements ProfileService {
    final private ProfileRepository profileRepository;
    @Override
    @Transactional
    @MyLog
    public ProfileResponseDTO.ProfileUpdateDTO update(ProfileRequestDTO.ProfileUpdateDTO profileUpdateDTO, Long id) {
        Optional<Profile> findId = getProfile(id);
        try {
            Profile findProfile = findId.get();
            findProfile.setNickName(profileUpdateDTO.getNickName());
            findProfile.setProfileImg(profileUpdateDTO.getProfileImg());

            return new ProfileResponseDTO.ProfileUpdateDTO(findProfile);
        }catch (Exception e){
            throw new Exception500("프로필 수정 실패 : "+e.getMessage());
        }
    }
    @Override
    @MyLog
    public ProfileResponseDTO.ProfileFindDTO find(Long id) {
        Optional<Profile> findId = getProfile(id);
        try {
            Profile findProfile = findId.get();
            return new ProfileResponseDTO.ProfileFindDTO(findProfile);
        }catch (Exception e) {
            throw new Exception404("프로필 조회 실패 : "+e.getMessage());
        }
    }

    private Optional<Profile> getProfile(Long id) {
        Optional<Profile> findId = profileRepository.findById(id);
        if(!findId.isPresent()) {
            throw new Exception404("해당 프로필을 찾을 수 없습니다. id: " + id);
        }
        return findId;
    }
}
