package ru.qwonix.test.social.media.api.facade.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.qwonix.test.social.media.api.facade.AuthorizationFacade;
import ru.qwonix.test.social.media.api.serivce.ImageService;
import ru.qwonix.test.social.media.api.serivce.PostService;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class AuthorizationFacadeImpl implements AuthorizationFacade {
    private final PostService postService;
    private final ImageService imageService;

    /**
     * @param postId   verifiable post id
     * @param username user profile name
     * @return {@code true} if the post belongs to the user or if the post does not exist, {@code false} otherwise.
     */
    @Override
    public boolean isPostOwnerOrIsPostNotFound(UUID postId, String username) {
        return postService.isPostOwner(postId, username) || !postService.existsById(postId);
    }

    /**
     * @param imageName verifiable image id
     * @param username  user profile name
     * @return {@code true} if the image belongs to the user or if the image does not exist, {@code false} otherwise.
     */
    @Override
    public boolean isImageOwnerOrIsImageNotFound(String imageName, String username) {
        return imageService.isImageOwner(imageName, username) || !imageService.existsByName(imageName);
    }
}
