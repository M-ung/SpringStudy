package stdy.springstudy.entitiy.profile;

import jakarta.persistence.*;
import lombok.Getter;
import stdy.springstudy.entitiy.user.User;

@Entity
@Getter
public class Profile {
    @Id
    @GeneratedValue
    @Column(name = "profile_id")
    private Long id;
    private String nickName;
    private String profileImg;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void setUser(User user) {
        this.user = user;
        user.setProfile(this);
    }
}
