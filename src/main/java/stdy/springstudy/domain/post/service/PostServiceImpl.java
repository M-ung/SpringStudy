package stdy.springstudy.domain.post.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stdy.springstudy.global.common.annotation.MyLog;
import stdy.springstudy.global.common.exception.Exception400;
import stdy.springstudy.global.common.exception.Exception404;
import stdy.springstudy.global.common.exception.Exception500;
import stdy.springstudy.domain.post.dto.PostRequestDTO;
import stdy.springstudy.domain.post.dto.PostResponseDTO;
import stdy.springstudy.domain.post.entity.Post;
import stdy.springstudy.domain.user.entity.User;
import stdy.springstudy.domain.post.repository.PostRepository;
import stdy.springstudy.domain.post.repository.PostRepositoryImpl;
import stdy.springstudy.domain.user.repository.UserRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {
    final private PostRepository postRepository;
    final private PostRepositoryImpl postRepositoryImpl;
    final private UserRepository userRepository;

    @Override
    @Transactional
    @MyLog
    public PostResponseDTO.PostUploadDTO upload(PostRequestDTO.PostUploadDTO postUploadDTO, String userEmail) {
        User findUser = getUser(userEmail);
        try {
            Post post = postUploadDTO.toEntity();
            post.setPostUser(findUser);
            postRepository.save(post);
            return new PostResponseDTO.PostUploadDTO(post);
        } catch (Exception e){
            throw new Exception500("게시물 업로드 실패 : "+e.getMessage());
        }
    }

    @Override
    @MyLog
    @Transactional
    public void delete(Long postId, String userEmail) {
        User findUser = getUser(userEmail);
        Post findPost = getPost(postId);
        try {
            if(findUser == findPost.getUser()) {
                postRepository.delete(getPost(postId));
            }
            else {
                throw new Exception400("user", "회원이 맞지 않습니다.");
            }
        } catch (Exception e){
            throw new Exception500("게시물 삭제 실패 : "+e.getMessage());
        }
    }

    @Override
    @Transactional
    @MyLog
    public PostResponseDTO.PostUpdateDTO update(PostRequestDTO.PostUpdateDTO postUpdateDTO, String userEmail, Long postId) {
        User findUser = getUser(userEmail);
        Post findPost = getPost(postId);
        try {
            if(findUser == findPost.getUser()) {
                findPost.updatePost(postUpdateDTO.getTitle(), postUpdateDTO.getContent(), postUpdateDTO.getCategories());
            }
            else {
                throw new Exception400("user", "회원이 맞지 않습니다.");
            }

            return new PostResponseDTO.PostUpdateDTO(findPost);
        } catch (Exception e){
            throw new Exception500("게시물 수정 실패 : "+e.getMessage());
        }
    }

    @Override
    @MyLog
    public PostResponseDTO.PostFindOneDTO findOne(Long postId) {
        try {
            PostResponseDTO.PostFindOneDTO findPost = postRepositoryImpl.findPostWithUserByPostId(postId);
            return findPost;
        } catch (Exception e) {
            throw new Exception404("게시물 조회 실패 : "+e.getMessage());
        }
    }

    @Override
    @MyLog
    public PostResponseDTO.PostFindAllDTO findAll(Long postId) {
        return null;
    } // 일단 보류


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
