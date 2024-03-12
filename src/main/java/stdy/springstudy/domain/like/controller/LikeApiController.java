package stdy.springstudy.domain.like.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stdy.springstudy.global.common.annotation.MyLog;
import stdy.springstudy.domain.user.entity.User;
import stdy.springstudy.global.auth.service.AuthenticationService;
import stdy.springstudy.domain.like.service.LikeServiceImpl;
import stdy.springstudy.global.common.exception.Exception500;
import stdy.springstudy.global.common.response.ApiResponse;

@RestController
@RequestMapping("/api/like")
@RequiredArgsConstructor
@Slf4j
public class LikeApiController {
    private final AuthenticationService authenticationService;
    private final LikeServiceImpl likeService;

    @PostMapping("toggle/{postId}")
    @MyLog
    public ResponseEntity<?> toggle(@PathVariable Long postId) {
        try {
            String userEmail = getUserEmail();
            String result = likeService.toggle(postId, userEmail);
            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "like toggle success", result));
        } catch (Exception500 e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(e.status().value(), e.getMessage()));
        }
    }

    @GetMapping("count/{postId}")
    @MyLog
    public ResponseEntity<?> count(@PathVariable Long postId) {
        try {
            String result = likeService.count(postId);
            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "like toggle success", result));
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
