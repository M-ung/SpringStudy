package stdy.springstudy.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import stdy.springstudy.dto.user.UserJoinDTO;
import stdy.springstudy.service.auth.AuthenticationService;
import stdy.springstudy.service.user.UserServiceImpl;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserApiController {
    final private UserServiceImpl userService;
    private final AuthenticationService authenticationService;

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserJoinDTO userJoinDTO) {
        try {
            userService.join(userJoinDTO);
            return ResponseEntity.ok("회원가입이 완료되었습니다.");
        } catch (DataIntegrityViolationException e) {
            // 이메일 중복 등 데이터 무결성 위반의 경우
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입 실패: 이메일이 이미 사용 중입니다.");
        } catch (Exception e) {
            // 그 외의 예외 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원가입 중 오류가 발생했습니다.");
        }
    }

    // 회원탈퇴
    @PostMapping("/delete")
    public ResponseEntity<String> delete() {
        try {
            System.out.println("[회원탈퇴]");
            String currentUserEmail = authenticationService.getCurrentAuthenticatedUserEmail();
            System.out.println("currentUserEmail = " + currentUserEmail);
            userService.delete(currentUserEmail);
            return ResponseEntity.ok("회원탈퇴가 완료되었습니다.");
        } catch (NoSuchElementException e) {
            // 해당 이메일 주소를 가진 사용자가 존재하지 않을 경우
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("회원을 찾을 수 없습니다.");
        } catch (Exception e) {
            // 기타 실패 사유에 대한 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원탈퇴 중 오류가 발생했습니다.");
        }
    }
}
