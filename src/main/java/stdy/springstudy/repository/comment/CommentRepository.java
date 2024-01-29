package stdy.springstudy.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import stdy.springstudy.entitiy.comment.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {
}
