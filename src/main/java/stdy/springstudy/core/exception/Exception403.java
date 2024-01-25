package stdy.springstudy.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import stdy.springstudy.dto.response.ResponseDTO;

// 권한 없음
@Getter
public class Exception403 extends RuntimeException {
    public Exception403(String message) {
        super(message);
    }

    public ResponseDTO<?> body(){
        return new ResponseDTO<>(HttpStatus.FORBIDDEN, "forbidden", getMessage());
    }

    public HttpStatus status(){
        return HttpStatus.FORBIDDEN;
    }
}