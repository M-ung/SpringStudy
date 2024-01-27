package stdy.springstudy.dto.post;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import stdy.springstudy.entitiy.category.Category;
import stdy.springstudy.entitiy.post.Post;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostResponseDTO {
    @Setter
    @Getter
    public static class PostUploadDTO {
        private Long id;
        private String title;
        private String content;
        private LocalDateTime uploadDate;
        private LocalDateTime updateDate;
        private List<Category> categories;

        public PostUploadDTO(Post post) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.uploadDate = post.getUploadDate();
            this.updateDate = post.getUpdateDate();
            this.categories = post.getCategories();
        }
    }
    @Setter
    @Getter
    public static class PostUpdateDTO {
        private Long id;
        private String title;
        private String content;
        private LocalDateTime uploadDate;
        private LocalDateTime updateDate;
        private List<Category> categories;
        public PostUpdateDTO(Post post) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.uploadDate = post.getUploadDate();
            this.updateDate = post.getUpdateDate();
            this.categories = post.getCategories();
        }
    }
    @Setter
    @Getter
    public class PostFindDTO {
        private Long id;
        private String title;
        private String content;
        private LocalDateTime uploadDate;
        private LocalDateTime updateDate;
        private List<Category> categories;
        public PostFindDTO(Post post) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.uploadDate = post.getUploadDate();
            this.updateDate = post.getUpdateDate();
            this.categories = post.getCategories();
        }
    }
    @Setter
    @Getter
    public static class PostFindOneDTO {
        private Long id;
        private String title;
        private String content;
        private LocalDateTime uploadDate;
        private LocalDateTime updateDate;
        private List<Category> categories;
        private String userEmail;
        private String userName;
        private String nickName;
        private String profileImg;

        public PostFindOneDTO(Post post) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.uploadDate = post.getUploadDate();
            this.updateDate = post.getUpdateDate();
            this.categories = post.getCategories();
            this.userEmail = post.getUser().getUserEmail();
            this.userName = post.getUser().getUserName();
            this.nickName = post.getUser().getProfile().getNickName();
            this.profileImg = post.getUser().getProfile().getProfileImg();
        }

        public PostFindOneDTO(Long id, String title, String content, LocalDateTime uploadDate, LocalDateTime updateDate, List<Category> categories) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.uploadDate = uploadDate;
            this.updateDate = updateDate;
            this.categories = categories;
        }
    }
    @Setter
    @Getter
    public class PostFindAllDTO {
    }
}