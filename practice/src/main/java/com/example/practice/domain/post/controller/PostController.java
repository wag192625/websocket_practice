package com.example.practice.domain.post.controller;

import com.example.practice.domain.post.dto.request.PostCreateRequestDto;
import com.example.practice.domain.post.dto.response.PostCreateResponseDto;
import com.example.practice.domain.post.service.PostService;
import com.example.practice.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create-post")
    public ResponseEntity<ApiResponse<PostCreateResponseDto>> createPost(@RequestBody PostCreateRequestDto requestDto) {
        return ResponseEntity.ok(
            ApiResponse.ok(
                "게시글 등록 완료.",
                "CREATED",
                postService.createPost(requestDto)
            )
        );
    }

}
