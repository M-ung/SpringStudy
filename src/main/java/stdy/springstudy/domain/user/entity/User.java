package stdy.springstudy.domain.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import stdy.springstudy.domain.post.entity.Post;
import stdy.springstudy.domain.profile.entity.Profile;
import stdy.springstudy.global.common.BaseEntity;

import java.util.List;

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

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User() {
    }
    public User(String userEmail, String userPassword, String userName) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
        setProfile();
    }

    public void updateName(String name) {
        this.userName = name;
    }

    private void setProfile() {
        this.profile = new Profile();
        this.profile.setNickName(this.userName);
        this.profile.setProfileImg("starter");
    }
}
