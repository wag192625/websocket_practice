package com.example.practice.domain.post.dto.response;

import com.example.practice.domain.post.entity.Post;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostCreateResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;// from BaseTimeEntity
    private final LocalDateTime updatedAt;

    public static PostCreateResponseDto from(Post entity) {
        return PostCreateResponseDto.builder()
            .id(entity.getId())
            .title(entity.getTitle())
            .content(entity.getContent())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())
            .build();
    }

}
