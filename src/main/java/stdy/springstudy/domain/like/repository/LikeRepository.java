package stdy.springstudy.domain.like.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stdy.springstudy.domain.like.entity.Like;
import stdy.springstudy.domain.post.entity.Post;
import stdy.springstudy.domain.user.entity.User;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserAndPost(User user, Post post);
    long countByPostId(Long postId);
}
