package stdy.springstudy.domain.profile.dto;

import lombok.Getter;
import lombok.Setter;
import stdy.springstudy.domain.user.entity.User;

public class ProfileRequestDTO {
    @Setter
    @Getter
    public static class ProfileUpdateDTO {
        private String nickName;
        private String profileImg;
        private User user;
    }
}
