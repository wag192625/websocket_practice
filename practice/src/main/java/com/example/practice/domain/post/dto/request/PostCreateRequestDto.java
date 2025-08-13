package com.example.practice.domain.post.dto.request;

import com.example.practice.domain.post.entity.Post;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;

@Slf4j
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateRequestDto {

    @NotBlank()
    @Length(min = 2, max = 20)
    private String title;

    @NotBlank()
    @Length(min = 2, max = 1000)
    private String content;

    public Post toEntity() {
        return Post.builder()
            .title(this.title)
            .content(this.content)
            .build();
    }
}
