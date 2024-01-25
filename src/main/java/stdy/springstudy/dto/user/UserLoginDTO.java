package stdy.springstudy.dto.user;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String userEmail;
    private String userPassword;
}
