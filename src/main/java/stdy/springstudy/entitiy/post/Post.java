package stdy.springstudy.entitiy.post;

import jakarta.persistence.*;
import lombok.Getter;
import stdy.springstudy.entitiy.category.Category;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class Post {
    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;
    private String content;
    private LocalDateTime postDate;

    @Enumerated(EnumType.STRING)
    private List<Category> categories;
}
