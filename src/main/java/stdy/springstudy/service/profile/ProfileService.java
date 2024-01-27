package stdy.springstudy.service.profile;

import stdy.springstudy.dto.profile.ProfileRequestDTO;
import stdy.springstudy.dto.profile.ProfileResponseDTO;

public interface ProfileService {
    // 프로필 수정
    ProfileResponseDTO.ProfileUpdateDTO update(ProfileRequestDTO.ProfileUpdateDTO profileUpdateDTO, Long id);

    // 프로필 조회
    ProfileResponseDTO.ProfileFindDTO find(Long id);
}
