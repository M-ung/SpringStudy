package stdy.springstudy.entitiy.user;

import jakarta.persistence.*;
import lombok.Getter;
import stdy.springstudy.entitiy.like.Like;
import stdy.springstudy.entitiy.profile.Profile;

@Entity
@Getter
public class User {
    @Id @GeneratedValue @Column(name = "user_id")
    private Long id;
    private String userEmail;
    private String userPassword;
    private String userName;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Profile profile;
//    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
//    private Like like;

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
