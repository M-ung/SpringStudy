package stdy.springstudy.service.post;


import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import stdy.springstudy.dto.post.PostResponseDTO;
import stdy.springstudy.dto.user.UserRequestDTO;
import stdy.springstudy.entitiy.category.Category;
import stdy.springstudy.entitiy.post.Post;
import stdy.springstudy.entitiy.user.User;
import stdy.springstudy.repository.post.PostRepository;
import stdy.springstudy.repository.post.PostRepositoryImpl;
import stdy.springstudy.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static stdy.springstudy.entitiy.category.Category.HUMOR;
import static stdy.springstudy.entitiy.category.Category.INFORMATION;

@SpringBootTest
@RunWith(SpringRunner.class)
class PostServiceImplTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostRepositoryImpl postRepositoryImpl;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    @Transactional
    @DisplayName("게시물 업로드 테스트")
    void upload() throws Exception {
        // given
        UserRequestDTO.UserJoinDTO userJoinDTO = new UserRequestDTO.UserJoinDTO();
        userJoinDTO.setUserEmail("test@example.com");
        userJoinDTO.setUserPassword(bCryptPasswordEncoder.encode("password"));
        userJoinDTO.setUserName("test");
        User user = userJoinDTO.toEntity();
        userRepository.save(user);

        List<Category> categories = new ArrayList<>();
        categories.add(HUMOR);
        categories.add(INFORMATION);
        Post post = new Post("title", "content", categories);
        postRepository.save(post);

        // when
        Optional<Post> findPost = postRepository.findById(1L);

        // then
        Assertions.assertThat(findPost.get().getTitle()).isEqualTo("title");
        Assertions.assertThat(findPost.get().getContent()).isEqualTo("content");
        Assertions.assertThat(findPost.get().getCategories()).isEqualTo(categories);
    }

    @Test
    @Transactional
    @DisplayName("게시물 삭제 테스트")
    void delete() throws Exception {
        // given
        UserRequestDTO.UserJoinDTO userJoinDTO = new UserRequestDTO.UserJoinDTO();
        userJoinDTO.setUserEmail("test@example.com");
        userJoinDTO.setUserPassword(bCryptPasswordEncoder.encode("password"));
        userJoinDTO.setUserName("test");
        User user = userJoinDTO.toEntity();
        userRepository.save(user);

        List<Category> categories = new ArrayList<>();
        categories.add(HUMOR);
        categories.add(INFORMATION);
        Post post = new Post("title", "content", categories);
        postRepository.save(post);

        // when
        postRepository.delete(post);
        Optional<Post> findPost = postRepository.findById(post.getId());

        // then
        assertThatThrownBy(() -> findPost.get())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @Transactional
    @DisplayName("게시물 수정 테스트")
    void update() throws Exception {
        // given
        UserRequestDTO.UserJoinDTO userJoinDTO = new UserRequestDTO.UserJoinDTO();
        userJoinDTO.setUserEmail("test@example.com");
        userJoinDTO.setUserPassword(bCryptPasswordEncoder.encode("password"));
        userJoinDTO.setUserName("test");
        User user = userJoinDTO.toEntity();
        userRepository.save(user);

        List<Category> categories = new ArrayList<>();
        categories.add(HUMOR);
        categories.add(INFORMATION);
        Post post = new Post("title", "content", categories);
        postRepository.save(post);

        // when
        post.updatePost("updateTitle", "updateContent", categories);

        // then
        Assertions.assertThat(post.getTitle()).isEqualTo("updateTitle");
        Assertions.assertThat(post.getContent()).isEqualTo("updateContent");
        Assertions.assertThat(post.getCategories()).isEqualTo(categories);
    }

    /** 수정해야 할 부분 **/
//    @Test
//    @Transactional
//    @DisplayName("게시물 조회 테스트")
//    void findOne() throws Exception {
//        // given
//        UserRequestDTO.UserJoinDTO userJoinDTO = new UserRequestDTO.UserJoinDTO();
//        userJoinDTO.setUserEmail("test@example.com");
//        userJoinDTO.setUserPassword(bCryptPasswordEncoder.encode("password"));
//        userJoinDTO.setUserName("test");
//        User user = userJoinDTO.toEntity();
//        userRepository.save(user);
//
//        List<Category> categories = new ArrayList<>();
//        categories.add(HUMOR);
//        categories.add(INFORMATION);
//        Post post = new Post(1L, "title", "content", categories);
//        System.out.println("===================================1");
//        System.out.println("post.getId() = " + post.getId());
//        postRepository.save(post);
//
//        System.out.println("===================================2");
//        System.out.println("post.getId() = " + post.getId());
//
//        // when
//        PostResponseDTO.PostFindOneDTO findPost = postRepositoryImpl.findPostWithUserByPostId(1L);
//        System.out.println("===================================3");
//        System.out.println("post.getId() = " + findPost);
//
//        // then
//        Assertions.assertThat(post.getTitle()).isEqualTo(findPost.getTitle());
//    }
}