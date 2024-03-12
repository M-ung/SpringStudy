package stdy.springstudy.domain.profile.service;

import stdy.springstudy.domain.profile.dto.ProfileRequestDTO;
import stdy.springstudy.domain.profile.dto.ProfileResponseDTO;

public interface ProfileService {
    // 프로필 수정
    ProfileResponseDTO.ProfileUpdateDTO update(ProfileRequestDTO.ProfileUpdateDTO profileUpdateDTO, Long id);

    // 프로필 조회
    ProfileResponseDTO.ProfileFindDTO find(Long id);
}
