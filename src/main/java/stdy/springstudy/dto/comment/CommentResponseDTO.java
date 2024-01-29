package stdy.springstudy.dto.comment;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import stdy.springstudy.entitiy.comment.Comment;

@Data
public class CommentResponseDTO {
    @Setter
    @Getter
    public static class CommentUploadDTO {
        private String nickName;
        private String profileImg;
        private String userEmail;
        private String userName;
        private Long commentId;
        private String content;
        private Long postId;

        public CommentUploadDTO(Comment comment) {
            this.nickName = comment.getUser().getProfile().getNickName();
            this.profileImg = comment.getUser().getProfile().getProfileImg();
            this.userEmail = comment.getUser().getUserEmail();
            this.userName = comment.getUser().getUserName();
            this.commentId = comment.getId();
            this.content = comment.getContent();
            this.postId = comment.getPost().getId();
        }
    }

    @Setter
    @Getter
    public static class CommentUpdateDTO {
        private String nickName;
        private String profileImg;
        private String userEmail;
        private String userName;
        private Long commentId;
        private String content;
        private Long postId;

        public CommentUpdateDTO(Comment comment) {
            this.nickName = comment.getUser().getProfile().getNickName();
            this.profileImg = comment.getUser().getProfile().getProfileImg();
            this.userEmail = comment.getUser().getUserEmail();
            this.userName = comment.getUser().getUserName();
            this.commentId = comment.getId();
            this.content = comment.getContent();
            this.postId = comment.getPost().getId();
        }
    }
}
