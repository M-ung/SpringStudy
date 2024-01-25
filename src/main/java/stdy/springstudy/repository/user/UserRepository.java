package stdy.springstudy.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import stdy.springstudy.entitiy.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
