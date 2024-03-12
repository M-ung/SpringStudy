package stdy.springstudy.global.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import stdy.springstudy.global.common.response.ApiResponse;

// 서버 에러
@Getter
public class Exception500 extends RuntimeException {
    public Exception500(String message) {
        super(message);
    }

    public ApiResponse<?> body(){
        return ApiResponse.ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Exception500 : ServerError");
    }

    public HttpStatus status(){
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}