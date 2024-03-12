package stdy.springstudy.domain.post.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stdy.springstudy.global.common.annotation.MyLog;
import stdy.springstudy.domain.post.dto.PostRequestDTO;
import stdy.springstudy.domain.post.dto.PostResponseDTO;
import stdy.springstudy.global.common.exception.Exception500;
import stdy.springstudy.global.common.response.ApiResponse;
import stdy.springstudy.domain.user.entity.User;
import stdy.springstudy.global.auth.service.AuthenticationService;
import stdy.springstudy.domain.post.service.PostServiceImpl;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
@Slf4j
public class PostApiController {
    final private PostServiceImpl postService;
    private final AuthenticationService authenticationService;

    // 게시물 업로드
    @PostMapping("/upload")
    @MyLog
    public ResponseEntity<?> upload(@RequestBody PostRequestDTO.PostUploadDTO postUploadDTO) {
        try {
            String userEmail = getUserEmail();
            PostResponseDTO.PostUploadDTO result = postService.upload(postUploadDTO, userEmail);
            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "post upload success", result));
        } catch (Exception500 e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(e.status().value(), e.getMessage()));
        }
    }

    // 게시물 삭제
    @PostMapping("/delete/{postId}")
    @MyLog
    public ResponseEntity<?> delete(@PathVariable Long postId) {
        try {
            String userEmail = getUserEmail();
            postService.delete(postId, userEmail);
            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "post delete success"));
        } catch (Exception500 e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(e.status().value(), e.getMessage()));
        }
    }

    // 게시물 수정
    @PostMapping("/update/{postId}")
    @MyLog
    public ResponseEntity<?> update(@RequestBody PostRequestDTO.PostUpdateDTO postUpdateDTO, @PathVariable Long postId) {
        try {
            String userEmail = getUserEmail();
            PostResponseDTO.PostUpdateDTO result = postService.update(postUpdateDTO, userEmail, postId);
            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "post update success", result));
        } catch (Exception500 e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(e.status().value(), e.getMessage()));
        }
    }

    // 게시물 하나 조회
    @GetMapping("/find/{postId}")
    @MyLog
    public ResponseEntity<?> findOne(@PathVariable Long postId) {
        try {
            PostResponseDTO.PostFindOneDTO result = postService.findOne(postId);
            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "post findOne success", result));
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
