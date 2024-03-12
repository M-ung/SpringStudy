package stdy.springstudy.domain.comment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import stdy.springstudy.domain.post.entity.Post;
import stdy.springstudy.domain.user.entity.User;

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

    @LastModifiedDate
    private LocalDateTime updateDate;

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

    public void updateComment(String content) {
        this.content = content;
    }
}
