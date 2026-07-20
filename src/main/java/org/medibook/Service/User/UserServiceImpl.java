package org.medibook.Service.User;

import jakarta.servlet.http.HttpServletResponse;
import org.medibook.Dto.PatientDto.PatientRegisterResponseDto;
import org.medibook.Dto.RefreshTokenDto.RefreshTokenDto;
import org.medibook.Dto.UserDto.*;
import org.medibook.Exception.BadRequestException;
import org.medibook.Exception.NotFoundException;
import org.medibook.Model.Rol;
import org.medibook.Model.User;
import org.medibook.Repository.RolRepository;
import org.medibook.Repository.UserRepository;
import org.medibook.Service.Doctor.DoctorService;
import org.medibook.Service.Patient.PatientService;
import org.medibook.Specifications.UserSpecification;
import org.medibook.Utils.CookieUtils.CookieUtils;
import org.medibook.Utils.TokenUtils.JwtTokenUtils;
import org.medibook.Utils.TokenUtils.RefreshTokenUtils;
import org.medibook.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Value("${spring.security.user.role}")
    private String publicRole;
    @Value("${spring.security.user.doctor.role}")
    private String doctorRole;

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtils;
    private final RefreshTokenUtils refreshTokenUtils;
    private final CookieUtils cookie;
    private final PatientService patientService;
    private final DoctorService doctorService;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenUtils jwtTokenUtils, RefreshTokenUtils refreshTokenUtils, CookieUtils cookie, PatientService patientService, DoctorService doctorService) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtils = jwtTokenUtils;
        this.refreshTokenUtils = refreshTokenUtils;
        this.cookie = cookie;
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    //Metodo para crear una cuenta de usuario
    @Transactional
    @Override
    public UserRegisterResponseDto userRegisterAdmin(UserRegisterRequestDto userRegisterRequestDto) throws BadRequestException, NotFoundException {

        //Mapeamos al usuario que llega en el Dto
        User user = userMapper.userRegisterRequestDtoToUser(userRegisterRequestDto);

        //Comprobamos que no se cree una nueva cuenta con el mismo correo electronico, a menos que se haya eliminado
        if (userRepository.existsByEmailAndSoftDelete(user.getEmail(), false)) {

        throw new BadRequestException("El correo " + user.getEmail() + " ya está registrado. Intente con otro correo electrónico.");

        }

         String doctorRoleObtained = "";

        List<Rol>listaRoles=new ArrayList<>();

        for (Long rolId:userRegisterRequestDto.getRoleIds()){

            Rol rol=rolRepository.findById(rolId).orElseThrow(
                    ()->new NotFoundException("El rol con id " + rolId + " no existe en el sistema"));

            if (rol.getName().equals(doctorRole)) {

                doctorRoleObtained=rol.getName();

            }

            listaRoles.add(rol);
        }

        String passwordEncript=passwordEncoder.encode(user.getPassword());

        user.setPassword(passwordEncript);
        user.setActive(true);
        user.setSoftDelete(false);
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(user.getCreatedAt());
        user.setListRoles(listaRoles);

        if (!doctorRoleObtained.isEmpty()) {

           doctorService.registerDoctor(userRegisterRequestDto.getDoctor(), user);

        }

       User userCreated=userRepository.save(user);

        return userMapper.userToUserRegisterResponseDto(userCreated);
    }

    @Transactional
    @Override
    public UserRegisterPublicResponseDto userRegisterPublic(UserRegisterPublicRequestDto userRegister) throws BadRequestException,NotFoundException {

        if (userRepository.existsByEmailAndSoftDelete(userRegister.getEmail(),false)){

            throw new BadRequestException("El correo "+userRegister.getEmail()+" ya está registrado. Intente con otro correo electrónico.");

        }

        Rol rol=rolRepository.findByName(publicRole)
                .orElseThrow(()->new NotFoundException("El rol "+publicRole+" no se encuentra registrado en el sistema"));

        User user=new User();

        user.setName(userRegister.getName());
        user.setEmail(userRegister.getEmail());
        user.setListRoles(List.of(rol));
        user.setPassword(passwordEncoder.encode(userRegister.getPassword()));
        user.setActive(true);
        user.setSoftDelete(false);
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(user.getCreatedAt());

        userRepository.save(user);

        PatientRegisterResponseDto patientResponseDto= patientService.registerPatient(userRegister.getPatient(), user);

     UserRegisterPublicResponseDto userRegisteResponse= userMapper.userToUserRegisterPublicResponseDto(user);

     userRegisteResponse.setPatient(patientResponseDto);

     return userRegisteResponse;

    }

    @Override
    public UserLoginResponseDto userLogin(HttpServletResponse response, UserLoginRequestDto userLoginRequestDto) throws NotFoundException {

        Authentication authentication=authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userLoginRequestDto.getEmail(),userLoginRequestDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken=jwtTokenUtils.token(authentication);
        RefreshTokenDto refreshToken=refreshTokenUtils.createRefreshToken(authentication.getName());

        cookie.createCookieOnly(response,refreshToken.getRefreshTokenId(),refreshToken.getRefreshToken());

        return new UserLoginResponseDto(accessToken,"Bearer ");
    }

    @Transactional(readOnly = true)
    @Override
    public Page<UserListResponseDto> getAllUsers(String name, Boolean active, String rol, Pageable pageable) throws NotFoundException {


        Specification<User> specification = Specification.allOf(UserSpecification.noEliminados()
                .and(UserSpecification.nombreLike(name)
                        .and(UserSpecification.activoEqual(active)
                                .and(UserSpecification.rolEqual(rol)))));

        Page<User>userList =userRepository.findAll(specification,pageable);

        if (userList.isEmpty()){
            throw new NotFoundException("No se encontraron usuarios con los filtros proporcionados");
        }

        return userList.map(userMapper::userToUserListResponseDto);
    }

    @Transactional
    @Override
    public void editUser(Long id, UserEditRequestDto userEditRequestDto) throws NotFoundException, BadRequestException {
     // Validamos que el usuario exista en la base de datos
        User user=userRepository.findById(id).orElseThrow(
                ()->new NotFoundException("El usuario con id: "+id+" no se encuentra registrado en el sistema"));

      // buscamos que el usuario tenga el rol de administrador
        boolean hasAdminRole=user.getListRoles().stream().anyMatch(rol->rol.getName().equals("ROLE_ADMIN"));

        // Contamos la cantidad de usuarios con el rol de administrador que están activos y no eliminados
        long countAdminRole =userRepository.countByListRoles_NameAndActiveAndSoftDelete("ROLE_ADMIN",true,false);

        List<Rol>listRoles=new ArrayList<>();

        boolean isAdminRole = false;

        for (Long idRol : userEditRequestDto.getRolId()) {

            Rol rol = rolRepository.findById(idRol).orElseThrow(
                    () -> new BadRequestException("El rol con id: " + idRol + " no se encuentra registrado en el sistema"));

            isAdminRole = rol.getName().equals("ROLE_ADMIN");

            listRoles.add(rol);
        }

        // Validamos que si el usuario tiene el rol de administrador y es el único activo, no se pueda desactivar
        if (hasAdminRole) {

            if (countAdminRole <= 1 && !isAdminRole && userEditRequestDto.getActive().equals(false)) {

                throw new BadRequestException("No se puede desactivar el usuario con id: " + id + " ya que es el único administrador activo en el sistema");


            }

        }

        userMapper.updateUserFromDto(userEditRequestDto, user);

        user.setListRoles(listRoles);
        user.setUpdatedAt(Instant.now());

        userRepository.save(user);


    }

    @Transactional
    @Override
    public void deleteUser(Long id) throws NotFoundException, BadRequestException {

        User user=userRepository.findById(id).orElseThrow(
                ()->new NotFoundException("El usuario con id: "+id+" no se encuentra registrado en el sistema"));

        boolean hasAdminRole=user.getListRoles().stream().anyMatch(rol->rol.getName().equals("ROLE_ADMIN"));

        long countAdminRole=userRepository.countByListRoles_NameAndActiveAndSoftDelete("ROLE_ADMIN",true,false);

        if (countAdminRole<=1 && hasAdminRole && user.getActive().equals(true)){
            throw new BadRequestException("No se puede eliminar el usuario con id: "+id+" ya que es el único administrador activo en el sistema");

        }

        user.setSoftDelete(true);

        userRepository.save(user);

    }


}