package stdy.springstudy.dto.user;

import lombok.Data;
import stdy.springstudy.entitiy.user.User;

@Data
public class UserJoinDTO {
    private String userEmail;
    private String userPassword;
    private String userName;

    // DTO를 User 엔티티로 변환하는 메소드
    public User toEntity() {
        return new User(this.userEmail, this.userPassword, this.userName);
    }
}
