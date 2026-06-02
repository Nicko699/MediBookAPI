package org.medibook.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.medibook.Dto.UserRegisterRequestDto;
import org.medibook.Dto.UserRegisterResponseDto;
import org.medibook.Model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roleIds", ignore = true)
    User userRegisterRequestDtoToUser(UserRegisterRequestDto userRegisterRequestDto);

    UserRegisterResponseDto userToUserRegisterResponseDto(User user);
}
