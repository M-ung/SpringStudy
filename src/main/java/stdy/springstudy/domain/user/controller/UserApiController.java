package stdy.springstudy.domain.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stdy.springstudy.global.common.annotation.MyLog;
import stdy.springstudy.global.common.exception.Exception500;
import stdy.springstudy.global.common.response.ApiResponse;
import stdy.springstudy.domain.user.dto.UserRequestDTO;
import stdy.springstudy.domain.user.dto.UserResponseDTO;
import stdy.springstudy.domain.user.entity.User;
import stdy.springstudy.global.auth.service.AuthenticationService;
import stdy.springstudy.domain.user.service.UserServiceImpl;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserApiController {
    final private UserServiceImpl userService;
    private final AuthenticationService authenticationService;

    // 회원 가입
    @PostMapping("/join")
    @MyLog
    public ResponseEntity<?> join(@RequestBody UserRequestDTO.UserJoinDTO userJoinDTO) {
        try {
            UserResponseDTO.UserJoinDTO result = userService.join(userJoinDTO);
            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "user join success", result));
        } catch (Exception500 e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(e.status().value(), e.getMessage()));
        }
    }

    // 회원 탈퇴
    @PostMapping("/delete")
    @MyLog
    public ResponseEntity<?> delete() {
        try {
            String userEmail = getUserEmail();
            userService.delete(userEmail);
            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "user delete success"));
        } catch (Exception500 e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(e.status().value(), e.getMessage()));
        }
    }

    // 회원 수정
    @PostMapping("/update")
    @MyLog
    public ResponseEntity<?> update(@RequestBody UserRequestDTO.UserUpdateDTO userUpdateDTO) {
        try {
            String userEmail = getUserEmail();
            UserResponseDTO.UserUpdateDTO result = userService.update(userUpdateDTO, userEmail);
            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "user update success", result));
        } catch (Exception500 e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(e.status().value(), e.getMessage()));
        }
    }

    // 회원 조회
    @GetMapping("/find")
    @MyLog
    public ResponseEntity<?> find() {
        try {
            String userEmail = getUserEmail();
            UserResponseDTO.UserFindDTO result = userService.find(userEmail);
            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "user find success", result));
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
