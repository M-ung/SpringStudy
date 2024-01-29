package stdy.springstudy.entitiy.comment;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import stdy.springstudy.entitiy.post.Post;
import stdy.springstudy.entitiy.user.User;

import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime commentDate;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment() {

    }

    public Comment(User findUser, Post findPost, String content) {
        this.user = findUser;
        this.post = findPost;
        this.content = content;
    }
}
