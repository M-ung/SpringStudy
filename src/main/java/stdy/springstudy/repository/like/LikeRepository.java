package stdy.springstudy.repository.like;

import org.springframework.data.jpa.repository.JpaRepository;
import stdy.springstudy.entitiy.like.Like;
import stdy.springstudy.entitiy.post.Post;
import stdy.springstudy.entitiy.user.User;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserAndPost(User user, Post post);
    long countByPostId(Long postId);
}
