package stdy.springstudy.entitiy.comment;

import jakarta.persistence.*;
import lombok.Getter;
import stdy.springstudy.entitiy.post.Post;
import stdy.springstudy.entitiy.user.User;

import java.time.LocalDateTime;

@Entity
@Getter
public class Comment {
    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;
    private LocalDateTime commentDate;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
}
