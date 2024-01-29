package stdy.springstudy.service.comment;

import stdy.springstudy.dto.comment.CommentRequestDTO;
import stdy.springstudy.dto.comment.CommentResponseDTO;

public interface CommentService {
    // 댓글 달기
    CommentResponseDTO.CommentUploadDTO commentOn(CommentRequestDTO.CommentUploadDTO CommentUploadDTO, String userEmail, Long postId);

    // 댓글 삭제

    // 댓글 수정

    // 댓글 조회
}
