package stdy.springstudy.controller.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stdy.springstudy.core.annotation.MyLog;
import stdy.springstudy.dto.post.PostRequestDTO;
import stdy.springstudy.dto.post.PostResponseDTO;
import stdy.springstudy.dto.response.ResponseDTO;
import stdy.springstudy.entitiy.user.User;
import stdy.springstudy.service.auth.AuthenticationService;
import stdy.springstudy.service.post.PostServiceImpl;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
@Slf4j
public class PostApiController {
    final private PostServiceImpl postService;
    private final AuthenticationService authenticationService;

    // 게시물 업로드
    @PostMapping("/upload")
    @MyLog
    public ResponseEntity<?> upload(@RequestBody PostRequestDTO.PostUploadDTO postUploadDTO) {
        System.out.println("=========================");
        System.out.println("postUploadDTO = " + postUploadDTO);
        String userEmail = getUserEmail();
        PostResponseDTO.PostUploadDTO uploadPost = postService.upload(postUploadDTO, userEmail);
        ResponseDTO<?> responseDTO = new ResponseDTO<>(uploadPost);
        return ResponseEntity.ok(responseDTO);
    }

    // 게시물 삭제
    @PostMapping("/delete/{postId}")
    @MyLog
    public ResponseEntity<?> delete(@PathVariable Long postId) {
        String userEmail = getUserEmail();
        postService.delete(postId, userEmail);
        return ResponseEntity.ok("게시물 삭제 성공");
    }

    // 게시물 수정
    @PostMapping("/update/{postId}")
    @MyLog
    public ResponseEntity<?> update(@RequestBody PostRequestDTO.PostUpdateDTO postUpdateDTO, @PathVariable Long postId) {
        String userEmail = getUserEmail();
        PostResponseDTO.PostUpdateDTO updatePost = postService.update(postUpdateDTO, userEmail, postId);
        ResponseDTO<?> responseDTO = new ResponseDTO<>(updatePost);
        return ResponseEntity.ok(responseDTO);
    }

    // 게시물 하나 조회
    @GetMapping("/find/{postId}")
    @MyLog
    public ResponseEntity<?> findOne(@PathVariable Long postId) {
        PostResponseDTO.PostFindOneDTO findOnePost = postService.findOne(postId);
        ResponseDTO<?> responseDTO = new ResponseDTO<>(findOnePost);
        return ResponseEntity.ok(responseDTO);
    }

    // 게시물 전체 조회


    private String getUserEmail() {
        User user = authenticationService.getCurrentAuthenticatedUser();
        String userEmail = user.getUserEmail();
        return userEmail;
    }
}