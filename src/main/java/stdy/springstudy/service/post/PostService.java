package stdy.springstudy.service.post;

import stdy.springstudy.dto.post.PostRequestDTO;
import stdy.springstudy.dto.post.PostResponseDTO;
import stdy.springstudy.dto.user.UserRequestDTO;
import stdy.springstudy.dto.user.UserResponseDTO;

public interface PostService {
    PostResponseDTO.PostUploadDTO upload(PostRequestDTO.PostUploadDTO postUploadDTO, String userEmail);

    // 게시물 탈퇴
    void delete(Long postId, String userEmail);

    // 게시물 수정
    PostResponseDTO.PostUpdateDTO update(PostRequestDTO.PostUpdateDTO postUpdateDTO, String userEmail, Long postId);

    // 게시물 하나 조회
    PostResponseDTO.PostFindOneDTO findOne(Long postId);

    // 게시물 전체 조회
    PostResponseDTO.PostFindAllDTO findAll(Long postId);
}
