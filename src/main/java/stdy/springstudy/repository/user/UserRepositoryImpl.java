package stdy.springstudy.repository.user;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import stdy.springstudy.dto.post.PostResponseDTO;
import stdy.springstudy.dto.user.UserResponseDTO;

import static stdy.springstudy.entitiy.user.QUser.user;
import static stdy.springstudy.entitiy.post.QPost.post;
import static stdy.springstudy.entitiy.profile.QProfile.profile;
public class UserRepositoryImpl implements UserRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public UserResponseDTO.UserFindDTO findUserWithProfileByEmail(String userEmail) {
//        return queryFactory
//                .selectFrom(user)
//                .leftJoin(user.profile).fetchJoin()
//                .where(user.userEmail.eq(userEmail))
//                .fetchOne();
        return queryFactory
                .select(Projections.constructor(UserResponseDTO.UserFindDTO.class,
                        user.id,
                        user.userEmail,
                        user.userName,
                        user.role,
                        profile.nickName,
                        profile.profileImg,
                        Projections.list(Projections.constructor(PostResponseDTO.PostFindOneDTO.class,
                                post.id,
                                post.title,
                                post.content,
                                post.uploadDate,
                                post.updateDate,
                                post.categories
                        ))
                ))
                .from(user)
                .leftJoin(user.profile, profile)
                .leftJoin(user.posts, post)
                .where(user.userEmail.eq(userEmail))
                .fetchOne();
    }
}
