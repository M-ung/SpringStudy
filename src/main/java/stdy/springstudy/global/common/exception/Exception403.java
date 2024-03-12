package stdy.springstudy.global.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import stdy.springstudy.global.common.response.ApiResponse;

// 권한 없음
@Getter
public class Exception403 extends RuntimeException {
    public Exception403(String message) {
        super(message);
    }

    public ApiResponse<?> body(){
        return ApiResponse.FAILURE(HttpStatus.FORBIDDEN.value(), "Exception403 : Forbidden");
    }

    public HttpStatus status(){
        return HttpStatus.FORBIDDEN;
    }
}
