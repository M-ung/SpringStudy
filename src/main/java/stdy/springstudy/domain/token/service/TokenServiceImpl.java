package stdy.springstudy.domain.token.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stdy.springstudy.global.common.exception.Exception404;
import stdy.springstudy.global.common.exception.Exception500;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TokenServiceImpl implements TokenService {
    private Set<String> blacklistedTokens = ConcurrentHashMap.newKeySet();
    @Override
    public void logout(String authHeader) {
        try {
            Optional<String> optionalToken = extractToken(authHeader);
            if (optionalToken.isPresent()) {
                String token = optionalToken.get();
                blacklistToken(token);
                log.error("Logout 성공");
            } else {
                throw new Exception404("해당 토큰을 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            throw new Exception500("token logout fail : " + e.getMessage());
        }
    }
    public void blacklistToken(String token) {
        blacklistedTokens.add(token);
    }
    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }

    public Optional<String> extractToken(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return Optional.of(authHeader.substring(7));
        }
        return Optional.empty();
    }
}
