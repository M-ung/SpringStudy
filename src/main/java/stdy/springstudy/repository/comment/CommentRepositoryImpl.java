package stdy.springstudy.repository.comment;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import stdy.springstudy.dto.comment.CommentResponseDTO;
import stdy.springstudy.entitiy.comment.QComment;

import java.util.List;

import static stdy.springstudy.entitiy.post.QPost.post;
import static stdy.springstudy.entitiy.comment.QComment.comment;

public class CommentRepositoryImpl implements CommentRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    public CommentRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<CommentResponseDTO> findCommentWithUserByPostId(Long postId) {
        return queryFactory
                .select(Projections.constructor(CommentResponseDTO.class,
                        comment.user.profile.nickName,
                        comment.user.profile.profileImg,
                        comment.user.userEmail,
                        comment.user.userName,
                        comment.id,
                        comment.content,
                        comment.commentDate,
                        post.id
                        ))
                .from(comment)
                .leftJoin(comment.post, post)
                .where(post.id.eq(postId))
                .fetch();
    }
}