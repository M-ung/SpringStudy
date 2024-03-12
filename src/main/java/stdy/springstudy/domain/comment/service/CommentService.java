package stdy.springstudy.domain.comment.service;

import stdy.springstudy.domain.comment.dto.CommentRequestDTO;
import stdy.springstudy.domain.comment.dto.CommentResponseDTO;

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
