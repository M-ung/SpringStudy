package stdy.springstudy.controller.like;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stdy.springstudy.core.annotation.MyLog;
import stdy.springstudy.dto.response.ResponseDTO;
import stdy.springstudy.entitiy.user.User;
import stdy.springstudy.service.auth.AuthenticationService;
import stdy.springstudy.service.like.LikeServiceImpl;

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
        String userEmail = getUserEmail();
        String messageLike = likeService.toggle(postId, userEmail);
        return ResponseEntity.ok(messageLike);
    }

    @GetMapping("count/{postId}")
    @MyLog
    public ResponseEntity<?> count(@PathVariable Long postId) {
        String messageLike = likeService.count(postId);
        return ResponseEntity.ok(messageLike);
    }

    private String getUserEmail() {
        User user = authenticationService.getCurrentAuthenticatedUser();
        String userEmail = user.getUserEmail();
        return userEmail;
    }
}
