package stdy.springstudy.service.user;

import stdy.springstudy.dto.user.UserJoinDTO;
import stdy.springstudy.entitiy.user.User;

public interface UserService {
    // 회원가입
    void join(UserJoinDTO userJoinDTO);

    // 회원탈퇴
    void delete(String userEmail);

    // 회원수정

    // 회원조회
}
