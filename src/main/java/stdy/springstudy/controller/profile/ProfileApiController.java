package stdy.springstudy.controller.profile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stdy.springstudy.core.annotation.MyLog;
import stdy.springstudy.dto.profile.ProfileRequestDTO;
import stdy.springstudy.dto.profile.ProfileResponseDTO;
import stdy.springstudy.dto.response.ResponseDTO;
import stdy.springstudy.entitiy.user.User;
import stdy.springstudy.service.auth.AuthenticationService;
import stdy.springstudy.service.profile.ProfileServiceImpl;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
@Slf4j
public class ProfileApiController {
    final private ProfileServiceImpl profileService;
    private final AuthenticationService authenticationService;

    // 프로필 수정
    @PostMapping("/update")
    @MyLog
    public ResponseEntity<?> update(@RequestBody ProfileRequestDTO.ProfileUpdateDTO profileUpdateDTO) {
        Long profileId = getProfileId();
        ProfileResponseDTO.ProfileUpdateDTO profileUpdate = profileService.update(profileUpdateDTO, profileId);
        ResponseDTO<?> responseDTO = new ResponseDTO<>(profileUpdate);
        return ResponseEntity.ok(responseDTO);
    }

    // 프로필 조회
    @GetMapping("/find")
    @MyLog
    public ResponseEntity<?> find() {
        Long profileId = getProfileId();
        ProfileResponseDTO.ProfileFindDTO profileFind = profileService.find(profileId);
        ResponseDTO<?> responseDTO = new ResponseDTO<>(profileFind);
        return ResponseEntity.ok(responseDTO);
    }

    private Long getProfileId() {
        User user = authenticationService.getCurrentAuthenticatedUser();
        Long profileId = user.getProfile().getId();
        return profileId;
    }
}
