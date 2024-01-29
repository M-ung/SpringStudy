package stdy.springstudy.repository.comment;

import stdy.springstudy.dto.comment.CommentResponseDTO;

import java.util.List;

public interface CommentRepositoryCustom {
    List<CommentResponseDTO> findCommentWithUserByPostId(Long postId);
}
