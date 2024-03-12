package stdy.springstudy.domain.comment.dto;

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
