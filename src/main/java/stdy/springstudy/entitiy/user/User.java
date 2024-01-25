package stdy.springstudy.entitiy.user;

import jakarta.persistence.*;
import lombok.Getter;
import stdy.springstudy.entitiy.profile.Profile;
@Entity
@Getter
public class User extends BaseEntity {
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

    public User() {
    }

    public User(String userEmail, String userPassword, String userName) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
    }
}
