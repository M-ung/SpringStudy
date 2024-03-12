package stdy.springstudy.domain.profile.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stdy.springstudy.global.common.annotation.MyLog;
import stdy.springstudy.domain.profile.dto.ProfileRequestDTO;
import stdy.springstudy.domain.profile.dto.ProfileResponseDTO;
import stdy.springstudy.global.common.exception.Exception500;
import stdy.springstudy.global.common.response.ApiResponse;
import stdy.springstudy.domain.user.entity.User;
import stdy.springstudy.global.auth.service.AuthenticationService;
import stdy.springstudy.domain.profile.service.ProfileServiceImpl;

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
        try {
            Long profileId = getProfileId();
            ProfileResponseDTO.ProfileUpdateDTO result = profileService.update(profileUpdateDTO, profileId);
            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "profile update success", result));
        } catch (Exception500 e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(e.status().value(), e.getMessage()));
        }
    }

    // 프로필 조회
    @GetMapping("/find")
    @MyLog
    public ResponseEntity<?> find() {
        try {
            Long profileId = getProfileId();
            ProfileResponseDTO.ProfileFindDTO result = profileService.find(profileId);
            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "profile find success", result));
        } catch (Exception500 e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(e.status().value(), e.getMessage()));
        }
    }

    private Long getProfileId() {
        User user = authenticationService.getCurrentAuthenticatedUser();
        Long profileId = user.getProfile().getId();
        return profileId;
    }
}
