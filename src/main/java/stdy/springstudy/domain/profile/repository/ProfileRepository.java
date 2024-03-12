package stdy.springstudy.domain.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stdy.springstudy.domain.profile.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
