package ru.qwonix.test.social.media.api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CreatePostRequest(
        @NotEmpty
        @Size(max = 100, message = "title must be shorter than 100 characters")
        String title,

        String text
) {
}
