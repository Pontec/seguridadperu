package com.utp.seguridadperu.service.impl;

import com.utp.seguridadperu.Repository.RolRepository;
import com.utp.seguridadperu.Repository.UsuarioRepository;
import com.utp.seguridadperu.agregates.request.SignInRequest;
import com.utp.seguridadperu.agregates.request.SignUpRequest;
import com.utp.seguridadperu.agregates.response.AuthenticationResponse;
import com.utp.seguridadperu.modelo.Rol;
import com.utp.seguridadperu.modelo.Role;
import com.utp.seguridadperu.modelo.UsuarioModelo;
import com.utp.seguridadperu.service.AuthenticationService;
import com.utp.seguridadperu.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public UsuarioModelo signUpAdmin(SignUpRequest signUpRequest) {
        UsuarioModelo empleadosEntity = new UsuarioModelo();
        empleadosEntity.setNombres(signUpRequest.getNombres());
        empleadosEntity.setApellidos(signUpRequest.getApellidos());
        empleadosEntity.setCorreo(signUpRequest.getEmail());
        empleadosEntity.setNumDoc(signUpRequest.getNumDoc());
        empleadosEntity.setTelefono(signUpRequest.getTelefono());
        empleadosEntity.setDireccion(signUpRequest.getDireccion());
        Set<Rol> assginedRoles = new HashSet<>();
        Rol userRol = rolRepository.findByNombreRol(Role.ADMIN.name()).orElseThrow(() -> new RuntimeException("EL ROL NO EXISTE, REVISA TU BD"));
        assginedRoles.add(userRol);
        empleadosEntity.setRoles(assginedRoles);
        empleadosEntity.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        return usuarioRepository.save(empleadosEntity);
    }




    @Override
    public List<UsuarioModelo> todos() {
        return usuarioRepository.findAll();
    }

    @Override
    public AuthenticationResponse signin(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getEmail(), signInRequest.getPassword()));
        var user = usuarioRepository.findByCorreo(signInRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email no valido"));

        var token = jwtService.generateToken(user);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(token);
        return authenticationResponse;
    }
}
