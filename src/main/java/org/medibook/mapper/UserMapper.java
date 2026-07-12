package org.medibook.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.medibook.Dto.UserDto.UserEditRequestDto;
import org.medibook.Dto.UserDto.UserListResponseDto;
import org.medibook.Dto.UserDto.UserRegisterRequestDto;
import org.medibook.Dto.UserDto.UserRegisterResponseDto;
import org.medibook.Model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {


    User userRegisterRequestDtoToUser(UserRegisterRequestDto userRegisterRequestDto);

    UserRegisterResponseDto userToUserRegisterResponseDto(User user);

    UserListResponseDto userToUserListResponseDto(User user);

    void updateUserFromDto(UserEditRequestDto userEditRequestDto, @MappingTarget User user);
}
