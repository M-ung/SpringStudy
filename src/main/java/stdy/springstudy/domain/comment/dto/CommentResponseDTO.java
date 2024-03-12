package stdy.springstudy.domain.comment.dto;

import lombok.Data;
import stdy.springstudy.domain.comment.entity.Comment;

import java.time.LocalDateTime;

@Data
public class CommentResponseDTO {
    private String nickName;
    private String profileImg;
    private String userEmail;
    private String userName;
    private Long commentId;
    private String content;
    private LocalDateTime commentDate;
    private Long postId;

    public CommentResponseDTO(Comment comment) {
        this.nickName = comment.getUser().getProfile().getNickName();
        this.profileImg = comment.getUser().getProfile().getProfileImg();
        this.userEmail = comment.getUser().getUserEmail();
        this.userName = comment.getUser().getUserName();
        this.commentId = comment.getId();
        this.content = comment.getContent();
        this.commentDate = comment.getCommentDate();
        this.postId = comment.getPost().getId();
    }

    public CommentResponseDTO(String nickName, String profileImg, String userEmail, String userName, Long commentId, String content, LocalDateTime commentDate, Long postId) {
        this.nickName = nickName;
        this.profileImg = profileImg;
        this.userEmail = userEmail;
        this.userName = userName;
        this.commentId = commentId;
        this.content = content;
        this.commentDate = commentDate;
        this.postId = postId;
    }
}
