package org.medibook.Controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.medibook.Dto.PatientDto.PatientRegisterRequestDto;
import org.medibook.Dto.PatientDto.PatientRegisterResponseDto;
import org.medibook.Dto.UserDto.*;
import org.medibook.Exception.BadRequestException;
import org.medibook.Exception.NotFoundException;
import org.medibook.Service.User.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/register/public")
    public ResponseEntity<UserRegisterPublicResponseDto>userRegisterPublic(@RequestBody @Valid  UserRegisterPublicRequestDto userRegisterPublicRequestDto) throws BadRequestException,NotFoundException{

        UserRegisterPublicResponseDto userRegisterPublic=userService.userRegisterPublic(userRegisterPublicRequestDto);

        URI location= ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userRegisterPublic.getPatient().getId())
                .toUri();

        return ResponseEntity.created(location).body(userRegisterPublic);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> userLogin(HttpServletResponse response, @RequestBody @Valid UserLoginRequestDto userLoginRequestDto) throws NotFoundException {

        UserLoginResponseDto userLoginResponseDto = userService.userLogin(response, userLoginRequestDto);

        return ResponseEntity.ok(userLoginResponseDto);
        }

    @GetMapping("/filter")
    public ResponseEntity<Page<UserListResponseDto>> getAllUsers(@RequestParam(required = false)  String name, @RequestParam(required = false) Boolean active,@RequestParam(required = false) String rol, Pageable pageable) throws NotFoundException{

        Page<UserListResponseDto>userListResponse=userService.getAllUsers(name,active,rol,pageable);

        return ResponseEntity.ok(userListResponse);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Void> editUser(@PathVariable  Long id, @RequestBody @Valid UserEditRequestDto userEditRequestDto) throws NotFoundException, BadRequestException{

        userService.editUser(id,userEditRequestDto);

        return ResponseEntity.noContent().build();

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser( @PathVariable Long id) throws NotFoundException, BadRequestException{

        userService.deleteUser(id);

        return ResponseEntity.noContent().build();

    }

}



