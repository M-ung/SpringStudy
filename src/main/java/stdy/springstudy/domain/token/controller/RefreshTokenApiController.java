package stdy.springstudy.domain.token.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stdy.springstudy.domain.token.dto.RefreshTokenRequestDTO;
import stdy.springstudy.domain.token.dto.RefreshTokenResponseDTO;
import stdy.springstudy.domain.token.service.RefreshTokenServiceImpl;
import stdy.springstudy.global.common.exception.Exception500;
import stdy.springstudy.global.common.response.ApiResponse;

@RestController
@RequestMapping("/api/token")
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenApiController {
    private final RefreshTokenServiceImpl refreshTokenService;

    @PostMapping("/getAccessToken")
    public ResponseEntity<?> getAccessToken(@RequestBody RefreshTokenRequestDTO.RefreshTokenGetAccessTokenDTO tokenGetAccessTokenDTO) {
        try {
            RefreshTokenResponseDTO.RefreshTokenGetAccessTokenDTO result = refreshTokenService.getAccessToken(tokenGetAccessTokenDTO);
            return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "refreshToken get success", result));
        }  catch (Exception500 e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.ERROR(e.status().value(), e.getMessage()));
        }
    }
}
