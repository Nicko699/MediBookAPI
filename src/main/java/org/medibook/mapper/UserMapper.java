package org.medibook.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.medibook.Dto.UserDto.*;
import org.medibook.Model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {


    User userRegisterRequestDtoToUser(UserRegisterRequestDto userRegisterRequestDto);

    UserRegisterResponseDto userToUserRegisterResponseDto(User user);

    User userRegisterUserRequestDtoToUser(UserRegisterUserRequestDto userRegisterUserRequestDto);

    UserListResponseDto userToUserListResponseDto(User user);

    void updateUserFromDto(UserEditRequestDto userEditRequestDto, @MappingTarget User user);
}
