package stdy.springstudy.domain.post.repository;

import stdy.springstudy.domain.post.dto.PostResponseDTO;

public interface PostRepositoryCustom {
    PostResponseDTO.PostFindOneDTO findPostWithUserByPostId(Long postId);
}
