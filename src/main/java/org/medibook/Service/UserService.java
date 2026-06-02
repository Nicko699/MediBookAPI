package org.medibook.Service;

import org.medibook.Dto.UserRegisterRequestDto;
import org.medibook.Dto.UserRegisterResponseDto;
import org.medibook.Exception.BadRequestException;

public interface UserService {

    public UserRegisterResponseDto userRegister(UserRegisterRequestDto userRegisterRequestDto) throws BadRequestException;

}
