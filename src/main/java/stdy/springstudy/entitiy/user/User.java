package stdy.springstudy.entitiy.user;

import jakarta.persistence.*;
import lombok.Getter;
import stdy.springstudy.entitiy.profile.Profile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
public class User {
    @Id @GeneratedValue @Column(name = "user_id")
    private Long id;
    private String userEmail;
    private String userPassword;
    private String userName;

    private String roles;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Profile profile;
//    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
//    private Like like;

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    public List<String> getRoleList() {
        if (this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }
}
