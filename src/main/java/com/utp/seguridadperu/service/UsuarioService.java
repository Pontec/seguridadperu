package com.utp.seguridadperu.service;

import com.utp.seguridadperu.agregates.dto.UsuarioDTO;
import com.utp.seguridadperu.modelo.UsuarioModelo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsuarioService {
    UserDetailsService userDetailService();
    List<UsuarioModelo> getUsuarios();
    UsuarioDTO getEmpleadoReniec(String dni);
}
