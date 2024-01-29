package stdy.springstudy.service.comment;

import stdy.springstudy.dto.comment.CommentRequestDTO;
import stdy.springstudy.dto.comment.CommentResponseDTO;
import stdy.springstudy.dto.post.PostRequestDTO;
import stdy.springstudy.dto.post.PostResponseDTO;

import java.util.List;

public interface CommentService {
    // 댓글 달기
    CommentResponseDTO commentOn(CommentRequestDTO.CommentUploadDTO CommentUploadDTO, String userEmail, Long postId);

    // 댓글 삭제
    void delete(Long commentId, String userEmail);

    // 댓글 수정
    CommentResponseDTO update(CommentRequestDTO.CommentUpdateDTO commentUpdateDTO, String userEmail, Long commentId);

    // 댓글 조회
    List<CommentResponseDTO> findAll(Long postId);
}
