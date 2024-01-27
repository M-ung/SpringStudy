package stdy.springstudy.entitiy.profile;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import stdy.springstudy.entitiy.user.User;

@Entity
@Getter
@Setter
public class Profile {
    @Id
    @GeneratedValue
    @Column(name = "profile_id")
    private Long id;
    private String nickName;
    private String profileImg;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;
}
