package stdy.springstudy.service.comment;

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
import stdy.springstudy.dto.comment.CommentRequestDTO;
import stdy.springstudy.dto.comment.CommentResponseDTO;
import stdy.springstudy.dto.post.PostRequestDTO;
import stdy.springstudy.dto.user.UserRequestDTO;
import stdy.springstudy.entitiy.category.Category;
import stdy.springstudy.entitiy.comment.Comment;
import stdy.springstudy.entitiy.user.User;
import stdy.springstudy.repository.comment.CommentRepository;
import stdy.springstudy.repository.post.PostRepository;
import stdy.springstudy.repository.user.UserRepository;
import stdy.springstudy.service.post.PostServiceImpl;
import stdy.springstudy.service.user.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static stdy.springstudy.entitiy.category.Category.HUMOR;
import static stdy.springstudy.entitiy.category.Category.INFORMATION;

@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CommentServiceImplTest {
    @Autowired private UserServiceImpl userService;
    @Autowired private UserRepository userRepository;
    @Autowired private PostServiceImpl postService;
    @Autowired private CommentServiceImpl commentService;
    @Autowired private CommentRepository commentRepository;

    @Test
    @Transactional
    @DisplayName("댓글 달기 테스트")
    void commentOn() {
        // given
        insertUserJoinDTO();
        User user = userRepository.findById(1L).get();
        insertPost(user);

        CommentRequestDTO.CommentUploadDTO commentUploadDTO = new CommentRequestDTO.CommentUploadDTO();
        commentUploadDTO.setContent("content");

        commentService.commentOn(commentUploadDTO, user.getUserEmail(), 1L);

        // when
        Optional<Comment> findComment = commentRepository.findById(1L);

        // then
        Assertions.assertThat(findComment.get().getContent()).isEqualTo("content");
    }

    @Test
    @Transactional
    @DisplayName("댓글 삭제 테스트")
    void delete() {
        // given
        insertUserJoinDTO();
        User user = userRepository.findById(1L).get();
        insertPost(user);

        CommentRequestDTO.CommentUploadDTO commentUploadDTO = new CommentRequestDTO.CommentUploadDTO();
        commentUploadDTO.setContent("content");
        commentService.commentOn(commentUploadDTO, user.getUserEmail(), 1L);

        // when
        commentService.delete(1L, user.getUserEmail());
        Optional<Comment> findComment = commentRepository.findById(1L);

        // then
        assertThatThrownBy(() -> findComment.get()).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @Transactional
    @DisplayName("댓글 수정 테스트")
    void update() {
        // given
        insertUserJoinDTO();
        User user = userRepository.findById(1L).get();
        insertPost(user);

        CommentRequestDTO.CommentUploadDTO commentUploadDTO = new CommentRequestDTO.CommentUploadDTO();
        commentUploadDTO.setContent("content");
        commentService.commentOn(commentUploadDTO, user.getUserEmail(), 1L);

        CommentRequestDTO.CommentUpdateDTO commentUpdateDTO = new CommentRequestDTO.CommentUpdateDTO();
        commentUpdateDTO.setContent("updateContent");

        commentService.update(commentUpdateDTO, user.getUserEmail(), 1L);

        // when
        Optional<Comment> findComment = commentRepository.findById(1L);

        // then
        Assertions.assertThat(findComment.get().getContent()).isEqualTo("updateContent");
    }

    @Test
    @Transactional
    @DisplayName("댓글 조회 테스트")
    void findAll() {
        // given
        insertUserJoinDTO();
        User user = userRepository.findById(1L).get();
        insertPost(user);

        CommentRequestDTO.CommentUploadDTO commentUploadDTO1 = new CommentRequestDTO.CommentUploadDTO();
        commentUploadDTO1.setContent("content");
        commentService.commentOn(commentUploadDTO1, user.getUserEmail(), 1L);

        CommentRequestDTO.CommentUploadDTO commentUploadDTO2 = new CommentRequestDTO.CommentUploadDTO();
        commentUploadDTO2.setContent("content");
        commentService.commentOn(commentUploadDTO2, user.getUserEmail(), 1L);

        CommentRequestDTO.CommentUploadDTO commentUploadDTO3 = new CommentRequestDTO.CommentUploadDTO();
        commentUploadDTO3.setContent("content");
        commentService.commentOn(commentUploadDTO3, user.getUserEmail(), 1L);

        // when
        List<CommentResponseDTO> commentList = commentService.findAll(1L);
        int count = commentList.size();

        // then
        Assertions.assertThat(count).isEqualTo(3);
    }

    @Test
    @Transactional
    @DisplayName("댓글 소유주가 아닐 때 테스트")
    void notUser() {
        // given
        insertUserJoinDTO();
        User user = userRepository.findById(1L).get();
        insertPost(user);

        CommentRequestDTO.CommentUploadDTO commentUploadDTO = new CommentRequestDTO.CommentUploadDTO();
        commentUploadDTO.setContent("content");
        commentService.commentOn(commentUploadDTO, user.getUserEmail(), 1L);

        CommentRequestDTO.CommentUpdateDTO commentUpdateDTO = new CommentRequestDTO.CommentUpdateDTO();
        commentUpdateDTO.setContent("updateContent");

        commentService.update(commentUpdateDTO, user.getUserEmail(), 1L);

        // when, then
        assertThrows(Exception404.class, () -> commentService.update(commentUpdateDTO, "error@error.com", 1L));
        assertThrows(Exception404.class, () -> commentService.delete(1L, "error@error.com"));
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