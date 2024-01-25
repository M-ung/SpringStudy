package stdy.springstudy.service.user;

import stdy.springstudy.dto.user.UserRequestDTO;
import stdy.springstudy.dto.user.UserResponseDTO;

public interface UserService {
    // 회원가입
    UserResponseDTO.UserJoinDTO join(UserRequestDTO.UserJoinDTO userJoinDTO);

    // 회원탈퇴
    void delete(String userEmail);

    // 회원수정
    UserResponseDTO.UserUpdateDTO update(UserRequestDTO.UserUpdateDTO userUpdateDTO, String userEmail);

    // 회원조회
    UserResponseDTO.UserFindDTO find(String userEmail);
}
