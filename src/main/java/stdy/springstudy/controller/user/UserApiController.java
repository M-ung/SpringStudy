package stdy.springstudy.controller.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stdy.springstudy.core.annotation.MyLog;
import stdy.springstudy.dto.response.ResponseDTO;
import stdy.springstudy.dto.user.UserRequestDTO;
import stdy.springstudy.dto.user.UserResponseDTO;
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
    @MyLog
    public ResponseEntity<?> join(@RequestBody UserRequestDTO.UserJoinDTO userJoinDTO) {
        UserResponseDTO.UserJoinDTO userJoin = userService.join(userJoinDTO);
        ResponseDTO<?> responseDTO = new ResponseDTO<>(userJoin);
        return ResponseEntity.ok(responseDTO);
    }

    // 회원탈퇴
    @PostMapping("/delete")
    @MyLog
    public ResponseEntity<?> delete() {
        String userEmail = authenticationService.getCurrentAuthenticatedUserEmail();
        userService.delete(userEmail);
        return ResponseEntity.ok("회원탈퇴 성공");
    }

    // 회원수정
    @PostMapping("/update")
    @MyLog
    public ResponseEntity<?> update(@RequestBody UserRequestDTO.UserUpdateDTO userUpdateDTO) {
        String userEmail = authenticationService.getCurrentAuthenticatedUserEmail();
        UserResponseDTO.UserUpdateDTO userUpdate = userService.update(userUpdateDTO, userEmail);
        ResponseDTO<?> responseDTO = new ResponseDTO<>(userUpdate);
        return ResponseEntity.ok(responseDTO);
    }

    // 회원조회
    @GetMapping("/find")
    @MyLog
    public ResponseEntity<?> find() {
        String userEmail = authenticationService.getCurrentAuthenticatedUserEmail();
        UserResponseDTO.UserFindDTO userFind = userService.find(userEmail);
        ResponseDTO<?> responseDTO = new ResponseDTO<>(userFind);
        return ResponseEntity.ok(responseDTO);
    }
}
