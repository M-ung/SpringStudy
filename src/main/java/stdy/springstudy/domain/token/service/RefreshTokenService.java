package stdy.springstudy.domain.token.service;


import stdy.springstudy.domain.token.dto.RefreshTokenRequestDTO;
import stdy.springstudy.domain.token.dto.RefreshTokenResponseDTO;

public interface RefreshTokenService {
    RefreshTokenResponseDTO.RefreshTokenGetAccessTokenDTO getAccessToken(RefreshTokenRequestDTO.RefreshTokenGetAccessTokenDTO tokenGetAccessTokenDTO);
}
