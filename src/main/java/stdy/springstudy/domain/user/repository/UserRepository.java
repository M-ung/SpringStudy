package stdy.springstudy.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stdy.springstudy.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    User findByUserEmail(String userEmail);
    boolean existsByUserEmail(String userEmail);
}
