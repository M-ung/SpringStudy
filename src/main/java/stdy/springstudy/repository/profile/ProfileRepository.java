package stdy.springstudy.repository.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import stdy.springstudy.entitiy.profile.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
