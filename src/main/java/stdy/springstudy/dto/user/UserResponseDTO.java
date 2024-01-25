package stdy.springstudy.dto.user;

import lombok.Getter;
import lombok.Setter;
import stdy.springstudy.entitiy.user.User;

public class UserResponseDTO {
    @Setter
    @Getter
    public static class UserJoinDTO {
        private Long id;
        private String userEmail;
        private String userPassword;
        private String userName;

        public UserJoinDTO(User user) {
            this.id = user.getId();
            this.userEmail = user.getUserEmail();
            this.userPassword = user.getUserPassword();
            this.userName = user.getUserName();
        }
    }
}
