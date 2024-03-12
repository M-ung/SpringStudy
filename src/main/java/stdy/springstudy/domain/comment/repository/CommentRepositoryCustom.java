package stdy.springstudy.domain.comment.repository;

import stdy.springstudy.domain.comment.dto.CommentResponseDTO;

import java.util.List;

public interface CommentRepositoryCustom {
    List<CommentResponseDTO> findCommentWithUserByPostId(Long postId);
}
