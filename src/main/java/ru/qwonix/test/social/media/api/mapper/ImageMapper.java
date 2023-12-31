package ru.qwonix.test.social.media.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.core.io.Resource;
import ru.qwonix.test.social.media.api.dto.ImageResourceResponse;
import ru.qwonix.test.social.media.api.dto.ImageResponse;
import ru.qwonix.test.social.media.api.entity.Image;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ImageMapper {

    ImageResponse map(Image image);

    ImageResourceResponse map(String name, Resource resource);

}
