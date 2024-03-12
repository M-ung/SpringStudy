package stdy.springstudy.domain.user.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import stdy.springstudy.domain.post.dto.PostResponseDTO;
import stdy.springstudy.domain.post.entity.QPost;
import stdy.springstudy.domain.user.dto.UserResponseDTO;
import java.util.List;

import static stdy.springstudy.domain.profile.entity.QProfile.profile;
import static stdy.springstudy.domain.user.entity.QUser.user;


public class UserRepositoryImpl implements UserRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public UserResponseDTO.UserFindDTO findUserWithProfileByEmail(String userEmail) {
        QPost qPost = QPost.post;

        UserResponseDTO.UserFindDTO userFindDTO = queryFactory
                .select(Projections.constructor(UserResponseDTO.UserFindDTO.class,
                        user.id,
                        user.userEmail,
                        user.userName,
                        user.role,
                        profile.nickName,
                        profile.profileImg
                ))
                .from(user)
                .leftJoin(user.profile, profile)
                .where(user.userEmail.eq(userEmail))
                .fetchOne();

        if (userFindDTO == null) {
            return null;
        }
        List<PostResponseDTO.PostFindDTO> posts = queryFactory
                .select(Projections.constructor(PostResponseDTO.PostFindDTO.class,
                        qPost.id,
                        qPost.title,
                        qPost.content,
                        qPost.uploadDate,
                        qPost.updateDate,
                        qPost.categories
                ))
                .from(qPost)
                .where(qPost.user.id.eq(userFindDTO.getId()))
                .fetch();

        userFindDTO.setPosts(posts);

        return userFindDTO;
    }
}
