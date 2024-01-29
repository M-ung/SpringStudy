package stdy.springstudy.service.like;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stdy.springstudy.core.exception.Exception404;
import stdy.springstudy.entitiy.like.Like;
import stdy.springstudy.entitiy.post.Post;
import stdy.springstudy.entitiy.user.User;
import stdy.springstudy.repository.like.LikeRepository;
import stdy.springstudy.repository.post.PostRepository;
import stdy.springstudy.repository.user.UserRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class LikeServiceImpl implements LikeService {
    final private LikeRepository likeRepository;
    final private PostRepository postRepository;
    final private UserRepository userRepository;
    @Override
    @Transactional
    public String toggle(Long postId, String userEmail) {
        User findUser = getUser(userEmail);
        Post findPost = getPost(postId);

        String result = likeRepository.findByUserAndPost(findUser, findPost).map(
                // 좋아요가 이미 존재할 경우
                like -> {
                    likeRepository.delete(like);
                    return findUser + "가 " + findPost + "에 좋아요를 취소함.";
                }
        ).orElseGet(() -> {
            // 좋아요가 없을 경우
            likeRepository.save(new Like(findUser, findPost));
            return findUser + "가 " + findPost + "에 좋아요를 누름.";
        });

        return result;
    }

    @Override
    public String count(Long postId) {
        long countLike = likeRepository.countByPostId(postId);
        Post findPost = getPost(postId);
        String result = findPost + "의 좋아요 갯수는 " + countLike + " 이다.";
        return result;
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
}