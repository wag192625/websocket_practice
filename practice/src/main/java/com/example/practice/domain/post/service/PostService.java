package com.example.practice.domain.post.service;

import com.example.practice.domain.post.dto.request.PostCreateRequestDto;
import com.example.practice.domain.post.dto.response.PostCreateResponseDto;
import com.example.practice.domain.post.entity.Post;
import com.example.practice.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public PostCreateResponseDto createPost(PostCreateRequestDto requestDto) {
        Post post = requestDto.toEntity();
        return PostCreateResponseDto.from(postRepository.save(post));
    }

}
