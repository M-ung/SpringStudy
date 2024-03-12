package stdy.springstudy.global.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import stdy.springstudy.global.common.response.ApiResponse;
import stdy.springstudy.global.common.valid.Valid;

// 유효성 검사 실패, 잘못된 파라메터 요청
@Getter
public class Exception400 extends RuntimeException {
    private String key;
    private String value;

    public Exception400(String key, String value) {
        super(value);
        this.key = key;
        this.value = value;
    }

    public Exception400(String key) {
        this.key = key;
    }

    public ApiResponse<?> body(){
        Valid valid = new Valid(key, value);
        return ApiResponse.FAILURE(HttpStatus.BAD_REQUEST.value(), "Exception400 : BadRequest");
    }

    public HttpStatus status(){
        return HttpStatus.BAD_REQUEST;
    }
}