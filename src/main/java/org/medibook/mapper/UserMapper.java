package org.medibook.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.medibook.Dto.UserDto.UserRegisterRequestDto;
import org.medibook.Dto.UserDto.UserRegisterResponseDto;
import org.medibook.Model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {


    User userRegisterRequestDtoToUser(UserRegisterRequestDto userRegisterRequestDto);

    UserRegisterResponseDto userToUserRegisterResponseDto(User user);
}
