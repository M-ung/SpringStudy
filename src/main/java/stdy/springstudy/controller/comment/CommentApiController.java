package stdy.springstudy.controller.comment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stdy.springstudy.core.annotation.MyLog;
import stdy.springstudy.dto.comment.CommentRequestDTO;
import stdy.springstudy.dto.comment.CommentResponseDTO;
import stdy.springstudy.dto.response.ResponseDTO;
import stdy.springstudy.entitiy.user.User;
import stdy.springstudy.service.auth.AuthenticationService;
import stdy.springstudy.service.comment.CommentServiceImpl;

import java.util.List;

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
        CommentResponseDTO commentOnComment = commentService.commentOn(commentUploadDTO, userEmail, postId);
        ResponseDTO<?> responseDTO = new ResponseDTO<>(commentOnComment);
        return ResponseEntity.ok(responseDTO);
    }

    // 댓글 삭제
    @PostMapping("/delete/{commentId}")
    @MyLog
    public ResponseEntity<?> delete(@PathVariable Long commentId) {
        String userEmail = getUserEmail();
        commentService.delete(commentId, userEmail);
        return ResponseEntity.ok("댓글 삭제 성공");
    }

    // 댓글 수정
    @PostMapping("/update/{commentId}")
    @MyLog
    public ResponseEntity<?> update(@RequestBody CommentRequestDTO.CommentUpdateDTO commentUpdateDTO, @PathVariable Long commentId) {
        String userEmail = getUserEmail();
        CommentResponseDTO updateComment = commentService.update(commentUpdateDTO, userEmail, commentId);
        ResponseDTO<?> responseDTO = new ResponseDTO<>(updateComment);
        return ResponseEntity.ok(responseDTO);
    }

    // 댓글 조회
    @GetMapping("/findAll/{postId}")
    @MyLog
    public ResponseEntity<?> findAll(@PathVariable Long postId) {
        List<CommentResponseDTO> comments = commentService.findAll(postId);
        ResponseDTO<?> responseDTO = new ResponseDTO<>(comments);
        return ResponseEntity.ok(responseDTO);
    }


    private String getUserEmail() {
        User user = authenticationService.getCurrentAuthenticatedUser();
        String userEmail = user.getUserEmail();
        return userEmail;
    }
}
