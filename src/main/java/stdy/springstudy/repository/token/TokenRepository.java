package stdy.springstudy.repository.token;

import org.springframework.data.jpa.repository.JpaRepository;
import stdy.springstudy.entitiy.jwt.RefreshToken;

public interface TokenRepository extends JpaRepository<RefreshToken, Long> {

    boolean existsByRefreshToken(String token);
}
