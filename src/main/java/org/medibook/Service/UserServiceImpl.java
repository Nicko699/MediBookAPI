package org.medibook.Service;

import jakarta.servlet.http.HttpServletResponse;
import org.medibook.Dto.*;
import org.medibook.Exception.BadRequestException;
import org.medibook.Exception.NotFoundException;
import org.medibook.Model.Rol;
import org.medibook.Model.User;
import org.medibook.Repository.RolRepository;
import org.medibook.Repository.UserRepository;
import org.medibook.Security.CookieUtils.CookieRefreshTokenUtils;
import org.medibook.Security.TokenUtils.JwtTokenUtils;
import org.medibook.Security.TokenUtils.RefreshTokenUtils;
import org.medibook.mapper.UserMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtils;
    private final RefreshTokenUtils refreshTokenUtils;
    private final CookieRefreshTokenUtils cookie;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenUtils jwtTokenUtils, RefreshTokenUtils refreshTokenUtils, CookieRefreshTokenUtils cookie) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtils = jwtTokenUtils;
        this.refreshTokenUtils = refreshTokenUtils;
        this.cookie = cookie;
    }

    //Metodo para crear una cuenta de usuario
    @Transactional
    @Override
    public UserRegisterResponseDto userRegister(UserRegisterRequestDto userRegisterRequestDto) throws BadRequestException {

        //Mapeamos al usuario que llega en el dto
        User user = userMapper.userRegisterRequestDtoToUser(userRegisterRequestDto);

        //Comprobamos que no se cree una nueva cuenta con el mismo correo electronico, a menos que se haya eliminado
        if (userRepository.existsByEmailAndSoftDelete(user.getEmail(), false)) {

        throw new BadRequestException("El correo " + user.getEmail() + " ya está registrado. Intente con otro correo electrónico.");

        }

        List<Rol>listaRoles=new ArrayList<>();

        for (Long rolId:userRegisterRequestDto.getRoleIds()){

            Rol rol=rolRepository.findById(rolId).orElseThrow(
                    ()->new BadRequestException("El rol con id " + rolId + " no existe en el sistema"));

            listaRoles.add(rol);
        }

        String passwordEncript=passwordEncoder.encode(user.getPassword());

        user.setPassword(passwordEncript);
        user.setActive(true);
        user.setSoftDelete(false);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(user.getCreatedAt());
        user.setListRoles(listaRoles);

       User userCreated=userRepository.save(user);

        return userMapper.userToUserRegisterResponseDto(userCreated);
    }

    @Override
    public UserLoginResponseDto userLogin(HttpServletResponse response, UserLoginRequestDto userLoginRequestDto) throws NotFoundException {

        Authentication authentication=authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userLoginRequestDto.getEmail(),userLoginRequestDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken=jwtTokenUtils.token(authentication);
        RefreshTokenDto refreshToken=refreshTokenUtils.crearRefreshToken(authentication.getName());

        cookie.createCookieOnly(response,refreshToken.getRefreshTokenId(),refreshToken.getRefreshToken());

        return new UserLoginResponseDto(accessToken,"Bearer ");
    }
}