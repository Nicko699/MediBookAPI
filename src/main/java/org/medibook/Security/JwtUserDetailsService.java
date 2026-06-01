package org.medibook.Security;

import jakarta.annotation.Nonnull;
import org.medibook.Model.Rol;
import org.medibook.Model.User;
import org.medibook.Repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Creamos un metodo para pasar los roles a una autoridad
    private Collection<GrantedAuthority>authorities(List<Rol>listRol) {

        List<GrantedAuthority> authorityList = new ArrayList<>();

        for (Rol rol : listRol) {

            GrantedAuthority authority = new SimpleGrantedAuthority(rol.getName());

            authorityList.add(authority);
        }

        return authorityList;

    }

    //Metodo para validar que el usuario exista en la bd y además armar un Objeto user con la información del usuario
    @Transactional(readOnly = true)
    @Override
    public @Nonnull UserDetails loadUserByUsername(@Nonnull  String email) throws UsernameNotFoundException {

        User foundUser=userRepository.findUserByEmail(email).orElseThrow(()->
                new UsernameNotFoundException("El usuario no existe en el sistema"));

        return new CustomUserDetails(foundUser.getEmail(),foundUser.getName(),foundUser.getPassword(),
                foundUser.getActive(),authorities(foundUser.getListRoles()));
    }
}
