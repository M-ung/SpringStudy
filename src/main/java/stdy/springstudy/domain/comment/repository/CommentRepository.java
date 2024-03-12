package stdy.springstudy.domain.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stdy.springstudy.domain.comment.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {
}
