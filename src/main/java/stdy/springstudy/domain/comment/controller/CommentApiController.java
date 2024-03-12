package stdy.springstudy.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stdy.springstudy.global.common.annotation.MyLog;
import stdy.springstudy.domain.comment.dto.CommentRequestDTO;
import stdy.springstudy.domain.comment.dto.CommentResponseDTO;
import stdy.springstudy.global.common.exception.Exception500;
import stdy.springstudy.global.common.response.ApiResponse;
import stdy.springstudy.domain.user.entity.User;
import stdy.springstudy.global.auth.service.AuthenticationService;
import stdy.springstudy.domain.comment.service.CommentServiceImpl;

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
        try {
            String userEmail = getUserEmail();
            CommentResponseDTO result = commentService.commentOn(commentUploadDTO, userEmail, postId);
            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "comment commentOn success", result));
        } catch (Exception500 e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(e.status().value(), e.getMessage()));
        }
    }

    // 댓글 삭제
    @PostMapping("/delete/{commentId}")
    @MyLog
    public ResponseEntity<?> delete(@PathVariable Long commentId) {
        try {
            String userEmail = getUserEmail();
            commentService.delete(commentId, userEmail);
            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "comment delete success"));
        } catch (Exception500 e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(e.status().value(), e.getMessage()));
        }
    }

    // 댓글 수정
    @PostMapping("/update/{commentId}")
    @MyLog
    public ResponseEntity<?> update(@RequestBody CommentRequestDTO.CommentUpdateDTO commentUpdateDTO, @PathVariable Long commentId) {
        try {
            String userEmail = getUserEmail();
            CommentResponseDTO result = commentService.update(commentUpdateDTO, userEmail, commentId);
            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "comment update success", result));
        } catch (Exception500 e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(e.status().value(), e.getMessage()));
        }
    }

    // 댓글 조회
    @GetMapping("/findAll/{postId}")
    @MyLog
    public ResponseEntity<?> findAll(@PathVariable Long postId) {
        try {
            List<CommentResponseDTO> result = commentService.findAll(postId);
            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "comment findAll success", result));
        } catch (Exception500 e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(e.status().value(), e.getMessage()));
        }
    }


    private String getUserEmail() {
        User user = authenticationService.getCurrentAuthenticatedUser();
        String userEmail = user.getUserEmail();
        return userEmail;
    }
}
