package stdy.springstudy.service.like;

public interface LikeService {
    // 좋아요 토글
    String toggle(Long postId, String userEmail);

    // 좋아요 갯수 조회
    String count(Long postId);
}
