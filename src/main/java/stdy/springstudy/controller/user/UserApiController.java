package stdy.springstudy.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import stdy.springstudy.dto.token.TokenDTO;
import stdy.springstudy.dto.user.UserJoinDTO;
import stdy.springstudy.dto.user.UserLoginDTO;
import stdy.springstudy.service.user.UserServiceImpl;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserApiController {
    final private UserServiceImpl userService;

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserJoinDTO userJoinDTO) {
        userService.join(userJoinDTO);
        return ResponseEntity.ok("회원가입이 완료되었습니다.");
    }

}
