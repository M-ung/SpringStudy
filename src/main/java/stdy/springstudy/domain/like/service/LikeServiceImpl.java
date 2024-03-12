package stdy.springstudy.domain.like.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stdy.springstudy.global.common.exception.Exception404;
import stdy.springstudy.global.common.exception.Exception500;
import stdy.springstudy.domain.like.entity.Like;
import stdy.springstudy.domain.post.entity.Post;
import stdy.springstudy.domain.user.entity.User;
import stdy.springstudy.domain.like.repository.LikeRepository;
import stdy.springstudy.domain.post.repository.PostRepository;
import stdy.springstudy.domain.user.repository.UserRepository;

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
        try {
            String result = likeRepository.findByUserAndPost(findUser, findPost).map(
                    // 좋아요가 이미 존재할 경우
                    like -> {
                        likeRepository.delete(like);
                        return "좋아요를 취소함";
                    }
            ).orElseGet(() -> {
                // 좋아요가 없을 경우
                likeRepository.save(new Like(findUser, findPost));
                return "좋아요를 누름";
            });

            return result;
        } catch (Exception e){
            throw new Exception500("좋아요 토글 실패 : "+e.getMessage());
        }
    }

    @Override
    public String count(Long postId) {
        try {
            long countLike = likeRepository.countByPostId(postId);
            Post findPost = getPost(postId);
            String result = countLike+" 이다";
            return result;
        } catch (Exception e){
            throw new Exception500("좋아요 조회 실패 : "+e.getMessage());
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
}
