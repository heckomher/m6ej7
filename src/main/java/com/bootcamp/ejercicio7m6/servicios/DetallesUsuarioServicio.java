package com.bootcamp.ejercicio7m6.servicios;

import com.bootcamp.ejercicio7m6.entidades.Usuario;
import com.bootcamp.ejercicio7m6.repos.IAdministrativoRepositorio;
import com.bootcamp.ejercicio7m6.repos.IClienteRepositorio;
import com.bootcamp.ejercicio7m6.repos.IProfesionalRepositorio;
import com.bootcamp.ejercicio7m6.repos.IUsuarioRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetallesUsuarioServicio implements UserDetailsService {
    private final IUsuarioRepositorio IUsuarioRepositorio;

    public DetallesUsuarioServicio(final IUsuarioRepositorio IUsuarioRepositorio) {
        this.IUsuarioRepositorio = IUsuarioRepositorio;

    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = IUsuarioRepositorio.findByNombreUsuario(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        return new User(
                usuario.getNombreUsuario(),
                usuario.getContrasena(),
                true, true, true, true,
                mapRolesToAuthorities(usuario.getTipoUsuario())
                );

    }

    private Collection<?extends GrantedAuthority> mapRolesToAuthorities(String tipoUsuario){
        List<SimpleGrantedAuthority> authorities = Arrays.stream(tipoUsuario.split(","))
                .map( role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                .collect(Collectors.toList());


        return authorities;
    }


    /*public UserDetails getUsuarioPorNombre(String username) throws UsernameNotFoundException {
        Usuario user = IUsuarioRepositorio.findUsuarioByNombreUsuario(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getNombreUsuario(),
                user.getContrasena(),
                user.getTipoUsuario()

        );
    }*/

}

