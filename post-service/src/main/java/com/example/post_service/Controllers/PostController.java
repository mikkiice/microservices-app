package com.example.post_service.Controllers;



import com.example.post_service.Model.DTO.PostRequest;
import com.example.post_service.Model.DTO.PostResponse;
import com.example.post_service.Services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest postRequest) {
        PostResponse postResponse = postService.createPost(postRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(postResponse);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostResponse>> getPostsByUserId(@PathVariable Long userId) {
        List<PostResponse> postResponse = postService.getPostsByUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(postResponse);
    }


}

// Сервис для проверки,публикации и хранения постов :
// Получает пост от определенного пользователя, проверяет его на наличие запрещенных слов,
// если запрещенные слова есть-отклоняет пост, если нет - публикует пост.
// Дополнительно проверяет на длину поста, длину поста задавать в проперти
// При отклонении или публикации - передавать данные в сервис аналитики.
// Иметь возможность смотреть все посты пользователя
