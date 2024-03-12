package stdy.springstudy.domain.token.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RefreshTokenResponseDTO {
    @Setter
    @Getter
    public static class RefreshTokenGetAccessTokenDTO {
        private String accessToken;
        private String refreshToken;

        public RefreshTokenGetAccessTokenDTO(String accessToken, String findRefreshToken) {
            this.accessToken = accessToken;
            this.refreshToken = findRefreshToken;
        }
    }
}
