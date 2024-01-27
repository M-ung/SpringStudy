package stdy.springstudy.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import stdy.springstudy.entitiy.post.Post;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
}
