package org.medibook.Controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.medibook.Dto.UserDto.UserLoginRequestDto;
import org.medibook.Dto.UserDto.UserLoginResponseDto;
import org.medibook.Dto.UserDto.UserRegisterRequestDto;
import org.medibook.Dto.UserDto.UserRegisterResponseDto;
import org.medibook.Exception.BadRequestException;
import org.medibook.Exception.NotFoundException;
import org.medibook.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> userRegisterAdmin(@RequestBody @Valid UserRegisterRequestDto userRegisterRequestDto) throws BadRequestException,NotFoundException {

        UserRegisterResponseDto userRegisterResponseDto=userService.userRegisterAdmin(userRegisterRequestDto);

        URI location= ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userRegisterResponseDto.getId())
                .toUri();

        return ResponseEntity.created(location).body(userRegisterResponseDto);

    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> userLogin(HttpServletResponse response, @RequestBody @Valid UserLoginRequestDto userLoginRequestDto) throws NotFoundException {

        UserLoginResponseDto userLoginResponseDto = userService.userLogin(response, userLoginRequestDto);

        return ResponseEntity.ok(userLoginResponseDto);
        }

}



