package stdy.springstudy.service.post;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import stdy.springstudy.core.exception.Exception404;
import stdy.springstudy.dto.post.PostRequestDTO;
import stdy.springstudy.dto.post.PostResponseDTO;
import stdy.springstudy.dto.user.UserRequestDTO;
import stdy.springstudy.entitiy.category.Category;
import stdy.springstudy.entitiy.post.Post;
import stdy.springstudy.entitiy.user.User;
import stdy.springstudy.repository.post.PostRepository;
import stdy.springstudy.repository.user.UserRepository;
import stdy.springstudy.service.user.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static stdy.springstudy.entitiy.category.Category.HUMOR;
import static stdy.springstudy.entitiy.category.Category.INFORMATION;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PostServiceImplTest {
    @Autowired private UserServiceImpl userService;
    @Autowired private UserRepository userRepository;
    @Autowired private PostServiceImpl postService;
    @Autowired private PostRepository postRepository;

    @Test
    @Transactional
    @DisplayName("게시물 업로드 테스트")
    void upload() {
        // given
        insertUserJoinDTO();
        User user = userRepository.findById(1L).get();
        insertPost(user);

        // when
        Optional<Post> findPost = postRepository.findById(1L);

        // then
        Assertions.assertThat(findPost.get().getTitle()).isEqualTo("title");
        Assertions.assertThat(findPost.get().getContent()).isEqualTo("content");
        Assertions.assertThat(findPost.get().getUser().getUserName()).isEqualTo("test");
        Assertions.assertThat(findPost.get().getUser().getUserEmail()).isEqualTo("test@test.com");
    }

    @Test
    @Transactional
    @DisplayName("게시물 삭제 테스트")
    void delete() {
        // given
        insertUserJoinDTO();
        User user = userRepository.findById(1L).get();
        insertPost(user);

        // when
        postService.delete(1L, user.getUserEmail());
        Optional<Post> findPost = postRepository.findById(1L);

        // then
        assertThatThrownBy(() -> findPost.get()).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @Transactional
    @DisplayName("게시물 수정 테스트")
    void update() {
        // given
        insertUserJoinDTO();
        User user = userRepository.findById(1L).get();
        insertPost(user);

        PostRequestDTO.PostUpdateDTO postUpdateDTO = new PostRequestDTO.PostUpdateDTO();
        postUpdateDTO.setTitle("updateTitle");
        postUpdateDTO.setContent("updateContent");

        // when
        postService.update(postUpdateDTO, user.getUserEmail(), 1L);
        Optional<Post> findPost = postRepository.findById(1L);

        // then
        Assertions.assertThat(findPost.get().getTitle()).isEqualTo("updateTitle");
        Assertions.assertThat(findPost.get().getContent()).isEqualTo("updateContent");
    }

    @Test
    @Transactional
    @DisplayName("게시물 조회 테스트")
    void findOne() {
        // given
        insertUserJoinDTO();
        User user = userRepository.findById(1L).get();
        insertPost(user);

        // when
        PostResponseDTO.PostFindOneDTO findOnePost = postService.findOne(1L);

        // then
        Assertions.assertThat(findOnePost.getTitle()).isEqualTo("title");
        Assertions.assertThat(findOnePost.getContent()).isEqualTo("content");
    }

    @Test
    @Transactional
    @DisplayName("게시물 소유주가 아닐 때 테스트")
    void notUser() {
        // given
        insertUserJoinDTO();
        User user = userRepository.findById(1L).get();
        insertPost(user);

        PostRequestDTO.PostUpdateDTO postUpdateDTO = new PostRequestDTO.PostUpdateDTO();
        postUpdateDTO.setTitle("updateTitle");
        postUpdateDTO.setContent("updateContent");

        // when, then
        assertThrows(Exception404.class, () -> postService.update(postUpdateDTO, "error@error.com", 1L));
        assertThrows(Exception404.class, () -> postService.delete(1L, "error@error.com"));
    }

    private void insertUserJoinDTO() {
        UserRequestDTO.UserJoinDTO userJoinDTO = new UserRequestDTO.UserJoinDTO();
        userJoinDTO.setUserName("test");
        userJoinDTO.setUserEmail("test@test.com");
        userJoinDTO.setUserPassword("password");

        userService.join(userJoinDTO);
    }
    private void insertPost(User user) {
        PostRequestDTO.PostUploadDTO postUploadDTO = new PostRequestDTO.PostUploadDTO();
        List<Category> categories = new ArrayList<>();
        categories.add(HUMOR);
        categories.add(INFORMATION);
        postUploadDTO.setCategories(categories);
        postUploadDTO.setTitle("title");
        postUploadDTO.setContent("content");
        postService.upload(postUploadDTO, user.getUserEmail());
    }
}