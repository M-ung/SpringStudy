package stdy.springstudy.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import stdy.springstudy.entitiy.user.User;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    User findByUserName(String userName);
    User findByUserEmail(String userEmail);
    boolean existsByUserEmail(String userEmail);
}
