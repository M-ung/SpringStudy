package stdy.springstudy.dto.comment;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class CommentRequestDTO {
    @Setter
    @Getter
    public static class CommentUploadDTO {
        private String content;
    }

    @Setter
    @Getter
    public static class CommentUpdateDTO {
        private String content;
    }
}
