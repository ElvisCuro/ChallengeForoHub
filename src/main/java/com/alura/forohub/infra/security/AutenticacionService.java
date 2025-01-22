package com.alura.forohub.infra.security;

import com.alura.forohub.domain.usuarios.DatosAutenticacionUsuario;
import com.alura.forohub.domain.usuarios.Usuario;
import com.alura.forohub.domain.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username);
    }


    public Usuario registrarUsuario(DatosAutenticacionUsuario datosAutenticacionUsuario) {
        Usuario nuevoUsuario = new Usuario(datosAutenticacionUsuario);
        String claveCodificada = passwordEncoder.encode(datosAutenticacionUsuario.clave());
        nuevoUsuario.setClave(claveCodificada);
        return usuarioRepository.save(nuevoUsuario);
    }

    public String autenticarUsuario(DatosAutenticacionUsuario datosAutenticacionUsuario) {
        Usuario usuario = (Usuario) usuarioRepository.findByEmail(datosAutenticacionUsuario.email());
        return tokenService.generarToken(usuario);
    }

    public String generarToken(Usuario usuario) {
        return tokenService.generarToken(usuario);
    }

}