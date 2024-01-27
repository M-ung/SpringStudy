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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Profile profile;
//    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
//    private Like like;

    public User() {
    }

    public void updateName(String name) {
        this.userName = name;
    }

    public User(String userEmail, String userPassword, String userName) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
        setProfile();
    }

    private void setProfile() {
        this.profile = new Profile();
        this.profile.setNickName(this.userName);
        this.profile.setProfileImg("starter");
    }
}
