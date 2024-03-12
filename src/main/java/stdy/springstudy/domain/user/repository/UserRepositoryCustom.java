package stdy.springstudy.domain.user.repository;

import stdy.springstudy.domain.user.dto.UserResponseDTO;

public interface UserRepositoryCustom {
    UserResponseDTO.UserFindDTO findUserWithProfileByEmail(String userEmail);
}
