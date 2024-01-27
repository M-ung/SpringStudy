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

    @Setter
    @Getter
    public static class UserFindDTO {
        private Long id;
        private String userEmail;
        private String userName;
        private String role;
        private String nickName;
        private String profileImg;

        public UserFindDTO(User user) {
            this.id = user.getId();
            this.userEmail = user.getUserEmail();
            this.userName = user.getUserName();
            this.role = user.getRole();
            this.nickName = user.getProfile().getNickName();
            this.profileImg = user.getProfile().getProfileImg();
        }
    }

    @Setter
    @Getter
    public static class UserUpdateDTO {
        private String userEmail;
        private String userName;
        private String role;

        public UserUpdateDTO(User user) {
            this.userEmail = user.getUserEmail();
            this.userName = user.getUserName();
            this.role = user.getRole();
        }
    }
}
