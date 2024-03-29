package stdy.springstudy.domain.like.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import stdy.springstudy.domain.post.entity.Post;
import stdy.springstudy.domain.user.entity.User;

import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "heart")
public class Like {
    @Id @GeneratedValue
    @Column(name = "like_id")
    private Long id;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime likeDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public Like() {
    }

    public Like(User user, Post post) {
        this.user = user;
        this.post = post;
    }
}
