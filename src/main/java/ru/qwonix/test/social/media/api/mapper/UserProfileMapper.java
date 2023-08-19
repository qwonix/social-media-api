package ru.qwonix.test.social.media.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.qwonix.test.social.media.api.dto.FullUserProfileResponseDto;
import ru.qwonix.test.social.media.api.dto.PublicUserProfileResponseDto;
import ru.qwonix.test.social.media.api.dto.UserRegistrationDto;
import ru.qwonix.test.social.media.api.entity.UserProfile;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    @Mapping(target = "username", source = "registrationDto.username")
    @Mapping(target = "email", source = "registrationDto.email")
    @Mapping(target = "passwordHash", source = "registrationDto.password")
    UserProfile map(UserRegistrationDto registrationDto);

    @Mapping(target = "id", source = "userProfile.id")
    @Mapping(target = "username", source = "userProfile.username")
    @Mapping(target = "email", source = "userProfile.email")
    FullUserProfileResponseDto mapToFull(UserProfile userProfile);

    @Mapping(target = "username", source = "userProfile.username")
    PublicUserProfileResponseDto mapToPublic(UserProfile userProfile);
}



