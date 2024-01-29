package stdy.springstudy.repository.user;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import stdy.springstudy.dto.post.PostResponseDTO;
import stdy.springstudy.dto.user.UserResponseDTO;
import stdy.springstudy.entitiy.post.QPost;

import java.util.List;

import static stdy.springstudy.entitiy.user.QUser.user;
import static stdy.springstudy.entitiy.profile.QProfile.profile;

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
