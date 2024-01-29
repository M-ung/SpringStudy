package stdy.springstudy.service.post;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import stdy.springstudy.repository.post.PostRepository;
import stdy.springstudy.repository.post.PostRepositoryImpl;
import stdy.springstudy.repository.user.UserRepository;
import stdy.springstudy.repository.user.UserRepositoryImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
class PostServiceImplTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostRepositoryImpl postRepositoryImpl;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
}