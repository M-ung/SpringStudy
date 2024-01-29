package stdy.springstudy.controller.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stdy.springstudy.core.annotation.MyLog;
import stdy.springstudy.dto.comment.CommentRequestDTO;
import stdy.springstudy.dto.comment.CommentResponseDTO;
import stdy.springstudy.dto.post.PostRequestDTO;
import stdy.springstudy.dto.post.PostResponseDTO;
import stdy.springstudy.dto.response.ResponseDTO;
import stdy.springstudy.entitiy.user.User;
import stdy.springstudy.service.auth.AuthenticationService;
import stdy.springstudy.service.comment.CommentServiceImpl;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
@Slf4j
public class CommentApiController {
    final private CommentServiceImpl commentService;
    private final AuthenticationService authenticationService;

    // 댓글 달기
    @PostMapping("/commentOn/{postId}")
    @MyLog
    public ResponseEntity<?> commentOn(@RequestBody CommentRequestDTO.CommentUploadDTO commentUploadDTO, @PathVariable Long postId) {
        String userEmail = getUserEmail();
        CommentResponseDTO.CommentUploadDTO commentOnComment = commentService.commentOn(commentUploadDTO, userEmail, postId);
        ResponseDTO<?> responseDTO = new ResponseDTO<>(commentOnComment);
        return ResponseEntity.ok(responseDTO);
    }

    // 댓글 삭제

    // 댓글 수정

    // 댓글 조회


    private String getUserEmail() {
        User user = authenticationService.getCurrentAuthenticatedUser();
        String userEmail = user.getUserEmail();
        return userEmail;
    }
}
