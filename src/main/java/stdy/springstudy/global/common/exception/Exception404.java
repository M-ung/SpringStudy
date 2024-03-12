package stdy.springstudy.global.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import stdy.springstudy.global.common.response.ApiResponse;

// 권한 없음
@Getter
public class Exception404 extends RuntimeException {
    public Exception404(String message) {
        super(message);
    }

    public ApiResponse<?> body(){
        return ApiResponse.FAILURE(HttpStatus.NOT_FOUND.value(), "Exception404 : NotFound");
    }

    public HttpStatus status(){
        return HttpStatus.NOT_FOUND;
    }
}