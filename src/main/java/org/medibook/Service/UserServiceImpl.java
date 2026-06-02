package org.medibook.Service;

import org.medibook.Dto.UserRegisterRequestDto;
import org.medibook.Dto.UserRegisterResponseDto;
import org.medibook.Exception.BadRequestException;
import org.medibook.Model.Rol;
import org.medibook.Model.User;
import org.medibook.Repository.RolRepository;
import org.medibook.Repository.UserRepository;
import org.medibook.mapper.UserMapper;
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

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
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
}