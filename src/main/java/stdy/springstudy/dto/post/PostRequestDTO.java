package stdy.springstudy.dto.post;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import stdy.springstudy.entitiy.category.Category;
import stdy.springstudy.entitiy.post.Post;
import stdy.springstudy.entitiy.user.User;

import java.util.List;

@Data
public class PostRequestDTO {
    @Setter
    @Getter
    public static class PostUploadDTO {
        private Long id;
        private String title;
        private String content;
        private List<Category> categories;
        private User user;

        public PostUploadDTO(Post post) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.categories = post.getCategories();
            this.user = post.getUser();
        }

        public Post toEntity() {
            return new Post(this.id, this.title, this.content, this.categories, this.user);
        }
    }

    @Getter
    @Setter
    public class PostUpdateDTO {
        private String title;
        private String content;
        private List<Category> categories;

        public PostUpdateDTO(String title, String content, List<Category> categories) {
            this.title = title;
            this.content = content;
            this.categories = categories;
        }
    }
}
