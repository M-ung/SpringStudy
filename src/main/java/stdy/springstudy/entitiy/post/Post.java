package stdy.springstudy.entitiy.post;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import stdy.springstudy.entitiy.category.Category;
import stdy.springstudy.entitiy.user.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class Post {
    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;
    private String title;
    private String content;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime uploadDate;

    @LastModifiedDate
    private LocalDateTime updateDate;


    @Enumerated(EnumType.STRING)
    private List<Category> categories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Post(Long id, String title, String content, List<Category> categories, User user) {
    }

    public Post(String title, String content, List<Category> categories) {
        this.title = title;
        this.content = content;
        this.categories = categories;
    }

    public void updatePost(String title, String content, List<Category> categories) {

        this.title = title;
        this.content = content;
        this.categories = categories;
    }
}
