package stdy.springstudy.repository.comment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import stdy.springstudy.domain.comment.repository.CommentRepository;
import stdy.springstudy.domain.comment.repository.CommentRepositoryImpl;
import stdy.springstudy.domain.user.dto.UserRequestDTO;
import stdy.springstudy.domain.category.Category;
import stdy.springstudy.domain.comment.entity.Comment;
import stdy.springstudy.domain.post.entity.Post;
import stdy.springstudy.domain.user.entity.User;
import stdy.springstudy.domain.post.repository.PostRepository;
import stdy.springstudy.domain.post.repository.PostRepositoryImpl;
import stdy.springstudy.domain.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static stdy.springstudy.domain.category.Category.HUMOR;
import static stdy.springstudy.domain.category.Category.INFORMATION;

@SpringBootTest
@RunWith(SpringRunner.class)
class CommentRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostRepositoryImpl postRepositoryImpl;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentRepositoryImpl commentRepositoryImpl;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Test
    @Transactional
    @DisplayName("댓글 달기 테스트")
    void commentOn() {
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

        Comment comment = commentRepository.save(new Comment(user, post, "content"));

        // when
        Optional<Comment> findComment = commentRepository.findById(comment.getId());

        // then
        Assertions.assertThat(findComment.get().getUser()).isEqualTo(user);
        Assertions.assertThat(findComment.get().getPost()).isEqualTo(post);
        Assertions.assertThat(findComment.get().getContent()).isEqualTo("content");
    }

    @Test
    void delete() {
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
//        Post post = new Post("title", "content", categories);
//        postRepository.save(post);
//
//        Comment comment = commentRepository.save(new Comment(user, post, "content"));
//
//        // when
//        Optional<Comment> findComment = commentRepository.findById(comment.getId());
//        commentRepository.delete(findComment.get());
//
//        // then
//        assertThatThrownBy(() -> findComment.get())
//                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void update() {
    }

    @Test
    void findAll() {
    }
}