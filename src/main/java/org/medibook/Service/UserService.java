package org.medibook.Service;

import jakarta.servlet.http.HttpServletResponse;
import org.medibook.Dto.UserLoginRequestDto;
import org.medibook.Dto.UserLoginResponseDto;
import org.medibook.Dto.UserRegisterRequestDto;
import org.medibook.Dto.UserRegisterResponseDto;
import org.medibook.Exception.BadRequestException;
import org.medibook.Exception.NotFoundException;

public interface UserService {

    public UserRegisterResponseDto userRegister(UserRegisterRequestDto userRegisterRequestDto) throws BadRequestException;

    public UserLoginResponseDto userLogin(HttpServletResponse response, UserLoginRequestDto userLoginRequestDto) throws NotFoundException;

}
