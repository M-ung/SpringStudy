package stdy.springstudy.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stdy.springstudy.domain.post.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
}
