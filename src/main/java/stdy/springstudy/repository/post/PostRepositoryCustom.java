package stdy.springstudy.repository.post;

import stdy.springstudy.dto.post.PostResponseDTO;

public interface PostRepositoryCustom {
    PostResponseDTO.PostFindOneDTO findPostWithUserByPostId(Long postId);
}
