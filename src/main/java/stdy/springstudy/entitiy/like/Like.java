package stdy.springstudy.entitiy.like;

import jakarta.persistence.*;
import lombok.Getter;
import stdy.springstudy.entitiy.post.Post;
import stdy.springstudy.entitiy.user.User;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "heart")
public class Like {
    @Id @GeneratedValue
    @Column(name = "like_id")
    private Long id;
    private LocalDateTime likeDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
}
