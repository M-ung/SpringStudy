package stdy.springstudy.repository.user;

import stdy.springstudy.dto.user.UserResponseDTO;
import stdy.springstudy.entitiy.user.User;

import java.util.List;

public interface UserRepositoryCustom {
    UserResponseDTO.UserFindDTO findUserWithProfileByEmail(String userEmail);
}
