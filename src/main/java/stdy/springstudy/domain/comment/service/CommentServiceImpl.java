package stdy.springstudy.domain.comment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stdy.springstudy.global.common.annotation.MyLog;
import stdy.springstudy.global.common.exception.Exception400;
import stdy.springstudy.global.common.exception.Exception404;
import stdy.springstudy.global.common.exception.Exception500;
import stdy.springstudy.domain.comment.dto.CommentRequestDTO;
import stdy.springstudy.domain.comment.dto.CommentResponseDTO;
import stdy.springstudy.domain.comment.entity.Comment;
import stdy.springstudy.domain.post.entity.Post;
import stdy.springstudy.domain.user.entity.User;
import stdy.springstudy.domain.comment.repository.CommentRepository;
import stdy.springstudy.domain.comment.repository.CommentRepositoryImpl;
import stdy.springstudy.domain.post.repository.PostRepository;
import stdy.springstudy.domain.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    final private PostRepository postRepository;
    final private CommentRepository commentRepository;
    final private CommentRepositoryImpl commentRepositoryImpl;
    final private UserRepository userRepository;

    // 댓글 달기
    @Override
    @Transactional
    @MyLog
    public CommentResponseDTO commentOn(CommentRequestDTO.CommentUploadDTO commentUploadDTO, String userEmail, Long postId) {
        User findUser = getUser(userEmail);
        Post findPost = getPost(postId);

        try {
            Comment comment = commentRepository.save(new Comment(findUser, findPost, commentUploadDTO.getContent()));
            return new CommentResponseDTO(comment);
        } catch (Exception e){
            throw new Exception500("댓글 달기 실패 : "+e.getMessage());
        }
    }

    // 댓글 삭제
    @Override
    @MyLog
    @Transactional
    public void delete(Long commentId, String userEmail) {
        Comment findComment = getComment(commentId);
        User findUser = getUser(userEmail);

        try {
            if(findUser == findComment.getUser()) {
                commentRepository.delete(findComment);
            }
            else {
                throw new Exception400("user", "회원이 맞지 않습니다.");
            }
        }
        catch (Exception e) {
            throw new Exception500("댓글 삭제 실패 : "+e.getMessage());
        }
    }

    // 댓글 수정
    @Override
    @MyLog
    @Transactional
    public CommentResponseDTO update(CommentRequestDTO.CommentUpdateDTO commentUpdateDTO, String userEmail, Long commentId) {
        User findUser = getUser(userEmail);
        Comment findComment = getComment(commentId);

        try {
            if(findUser == findComment.getUser()) {
                findComment.updateComment(commentUpdateDTO.getContent());
            }
            else {
                throw new Exception400("user", "회원이 맞지 않습니다.");
            }

            return new CommentResponseDTO(findComment);
        } catch (Exception e){
            throw new Exception500("댓글 수정 실패 : "+e.getMessage());
        }
    }

    // 댓글 조회
    @Override
    @MyLog
    public List<CommentResponseDTO> findAll(Long postId) {
        try {
            List<CommentResponseDTO> comments = commentRepositoryImpl.findCommentWithUserByPostId(postId);
            return comments;
        } catch (Exception e) {
            throw new Exception404("댓글 조회 실패 : "+e.getMessage());
        }
    }


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

    private Comment getComment(Long id) {
        Optional<Comment> findComment = commentRepository.findById(id);
        if(!findComment.isPresent()) {
            throw new Exception404("해당 댓글을 찾을 수 없습니다. id: " + id);
        }
        else {
            Comment comment = findComment.get();
            return comment;
        }
    }
}
