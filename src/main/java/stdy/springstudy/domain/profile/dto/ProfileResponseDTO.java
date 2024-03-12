package stdy.springstudy.domain.profile.dto;

import lombok.Getter;
import lombok.Setter;
import stdy.springstudy.domain.profile.entity.Profile;

public class ProfileResponseDTO {
    @Setter
    @Getter
    public static class ProfileFindDTO {
        private Long id;
        private String nickName;
        private String profileImg;

        public ProfileFindDTO(Profile profile) {
            this.id = profile.getId();
            this.nickName = profile.getNickName();
            this.profileImg = profile.getProfileImg();
        }
    }

    @Setter
    @Getter
    public static class ProfileUpdateDTO {
        private String nickName;
        private String profileImg;

        public ProfileUpdateDTO(Profile profile) {
            this.nickName = profile.getNickName();
            this.profileImg = profile.getProfileImg();
        }
    }
}
