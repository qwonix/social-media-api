package ru.qwonix.test.social.media.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.qwonix.test.social.media.api.entity.Image;

import java.util.Optional;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, String> {

    Boolean existsByImageNameAndUserUsername(String imageName, String username);

    Optional<Image> findByImageName(String name);
    Boolean existsByImageName(String name);
}
