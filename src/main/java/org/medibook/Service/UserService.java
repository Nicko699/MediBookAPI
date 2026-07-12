package org.medibook.Service;

import jakarta.servlet.http.HttpServletResponse;
import org.medibook.Dto.UserDto.*;
import org.medibook.Exception.BadRequestException;
import org.medibook.Exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    public UserRegisterResponseDto userRegisterAdmin(UserRegisterRequestDto userRegisterRequestDto) throws BadRequestException, NotFoundException;

    public UserRegisterResponseDto userRegisterUser(UserRegisterUserRequestDto userRegisterUserRequestDto) throws BadRequestException,NotFoundException;

    public UserLoginResponseDto userLogin(HttpServletResponse response, UserLoginRequestDto userLoginRequestDto) throws NotFoundException;

    public Page<UserListResponseDto>getAllUsers(String name, Boolean active, String rol, Pageable pageable) throws NotFoundException;

    public void editUser(Long id, UserEditRequestDto userEditRequestDto) throws NotFoundException, BadRequestException;

    public void deleteUser(Long id) throws NotFoundException, BadRequestException;


}
