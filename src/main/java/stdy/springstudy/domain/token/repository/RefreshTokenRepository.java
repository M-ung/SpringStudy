package stdy.springstudy.domain.token.repository;


import org.springframework.data.repository.CrudRepository;
import stdy.springstudy.domain.token.entity.RefreshToken;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
