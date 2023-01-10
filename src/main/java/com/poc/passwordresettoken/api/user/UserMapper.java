package com.poc.passwordresettoken.api.user;

import com.poc.passwordresettoken.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface UserMapper {

    User toEntity(UserInsertDto userInsert);
    User mergeToUpdate(@MappingTarget User user, UserUpdateDto userUpdate);
    UserOutDto toDto(User user);
}
