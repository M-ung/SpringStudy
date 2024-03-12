package stdy.springstudy.domain.post.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import stdy.springstudy.domain.post.dto.PostResponseDTO;

import static stdy.springstudy.domain.post.entity.QPost.post;
import static stdy.springstudy.domain.user.entity.QUser.user;

public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    public PostRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public PostResponseDTO.PostFindOneDTO findPostWithUserByPostId(Long postId) {
                return queryFactory
                    .select(Projections.constructor(PostResponseDTO.PostFindOneDTO.class,
                            post.id,
                            post.title,
                            post.content,
                            post.uploadDate,
                            post.updateDate,
                            post.categories,
                            user.userEmail,
                            user.userName,
                            user.profile.nickName,
                            user.profile.profileImg
                            ))
                    .from(post)
                    .leftJoin(post.user, user)
                    .where(post.id.eq(postId))
                    .fetchOne();
    }
}
