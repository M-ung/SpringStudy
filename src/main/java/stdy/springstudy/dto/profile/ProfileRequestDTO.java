package stdy.springstudy.dto.profile;

import lombok.Getter;
import lombok.Setter;
import stdy.springstudy.entitiy.user.User;

public class ProfileRequestDTO {
    @Setter
    @Getter
    public static class ProfileUpdateDTO {
        private String nickName;
        private String profileImg;
        private User user;
    }
}
