package stdy.springstudy.domain.post.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import stdy.springstudy.domain.category.Category;
import stdy.springstudy.domain.post.entity.Post;

import java.util.List;

@Data
public class PostRequestDTO {
    @Setter
    @Getter
    public static class PostUploadDTO {
        private String title;
        private String content;
        private List<Category> categories;

        public Post toEntity() {
            return new Post(this.title, this.content, this.categories);
        }
    }

    @Getter
    @Setter
    public static class PostUpdateDTO {
        private String title;
        private String content;
        private List<Category> categories;
    }
}
