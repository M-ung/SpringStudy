package stdy.springstudy.service.like;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import stdy.springstudy.dto.post.PostRequestDTO;
import stdy.springstudy.dto.user.UserRequestDTO;
import stdy.springstudy.entitiy.category.Category;
import stdy.springstudy.entitiy.user.User;
import stdy.springstudy.repository.post.PostRepository;
import stdy.springstudy.repository.user.UserRepository;
import stdy.springstudy.service.post.PostServiceImpl;
import stdy.springstudy.service.user.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static stdy.springstudy.entitiy.category.Category.HUMOR;
import static stdy.springstudy.entitiy.category.Category.INFORMATION;

@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class LikeServiceImplTest {
    @Autowired private UserServiceImpl userService;
    @Autowired private UserRepository userRepository;
    @Autowired private PostServiceImpl postService;
    @Autowired private PostRepository postRepository;
    @Autowired private LikeServiceImpl likeService;

    @Test
    @Transactional
    @DisplayName("좋아요 토글 테스트")
    void toggle() {
        // given
        insertUserJoinDTO();
        User user = userRepository.findById(1L).get();
        insertPost(user);

        // when, then
        String toggleMessage1 = likeService.toggle(1L, user.getUserEmail());
        Assertions.assertThat(toggleMessage1).isEqualTo("좋아요를 누름");

        String toggleMessage2 = likeService.toggle(1L, user.getUserEmail());
        Assertions.assertThat(toggleMessage2).isEqualTo("좋아요를 취소함");
    }

    @Test
    @Transactional
    @DisplayName("좋아요 조회 테스트")
    void count() {
        // given
        insertUserJoinDTO();
        User user = userRepository.findById(1L).get();
        insertPost(user);

        // when, then
        likeService.toggle(1L, user.getUserEmail());
        String likeCount1 = likeService.count(1L);
        Assertions.assertThat(likeCount1).isEqualTo("1 이다");

        likeService.toggle(1L, user.getUserEmail());
        String likeCount2 = likeService.count(1L);
        Assertions.assertThat(likeCount2).isEqualTo("0 이다");
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