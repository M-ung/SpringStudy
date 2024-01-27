package stdy.springstudy.repository.user;

import stdy.springstudy.dto.user.UserResponseDTO;

public interface UserRepositoryCustom {
    UserResponseDTO.UserFindDTO findUserWithProfileByEmail(String userEmail);
}
