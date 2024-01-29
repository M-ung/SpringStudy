package stdy.springstudy.service.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stdy.springstudy.core.annotation.MyLog;
import stdy.springstudy.core.exception.Exception404;
import stdy.springstudy.dto.comment.CommentRequestDTO;
import stdy.springstudy.dto.comment.CommentResponseDTO;
import stdy.springstudy.dto.post.PostResponseDTO;
import stdy.springstudy.entitiy.comment.Comment;
import stdy.springstudy.entitiy.post.Post;
import stdy.springstudy.entitiy.user.User;
import stdy.springstudy.repository.comment.CommentRepository;
import stdy.springstudy.repository.post.PostRepository;
import stdy.springstudy.repository.user.UserRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    final private PostRepository postRepository;
    final private CommentRepository commentRepository;
    final private UserRepository userRepository;

    // 댓글 달기
    @Override
    @Transactional
    @MyLog
    public CommentResponseDTO.CommentUploadDTO commentOn(CommentRequestDTO.CommentUploadDTO commentUploadDTO, String userEmail, Long postId) {
        User findUser = getUser(userEmail);
        Post findPost = getPost(postId);
        Comment comment = commentRepository.save(new Comment(findUser, findPost, commentUploadDTO.getContent()));
        return new CommentResponseDTO.CommentUploadDTO(comment);
    }

    // 댓글 삭제

    // 댓글 수정

    // 댓글 조회

    private Post getPost(Long id) {
        Optional<Post> findPost = postRepository.findById(id);
        if(!findPost.isPresent()) {
            throw new Exception404("해당 게시물을 찾을 수 없습니다. id: " + id);
        }
        else {
            Post post = findPost.get();
            return post;
        }
    }

    private User getUser(String userEmail) {
        User findUser = userRepository.findByUserEmail(userEmail);
        if(findUser == null) {
            throw new Exception404("해당 유저를 찾을 수 없습니다. User: " + findUser);
        }
        return findUser;
    }
}
