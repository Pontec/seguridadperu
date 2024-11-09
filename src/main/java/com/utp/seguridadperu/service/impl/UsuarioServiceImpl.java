package com.utp.seguridadperu.service.impl;

import com.utp.seguridadperu.Repository.UsuarioRepository;
import com.utp.seguridadperu.agregates.clients.ClientReniec;
import com.utp.seguridadperu.agregates.dto.UsuarioDTO;
import com.utp.seguridadperu.agregates.response.ResponseReniec;
import com.utp.seguridadperu.modelo.UsuarioModelo;
import com.utp.seguridadperu.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final ClientReniec clientReniec;
    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetailsService userDetailService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return usuarioRepository.findByCorreo(username).orElseThrow(
                        () -> new UsernameNotFoundException("Usuario no encontrado"));
            }
        };
    }

    @Override
    public List<UsuarioModelo> getUsuarios() {
        return usuarioRepository.findAll();
    }

    //Se recuera el token que estan esta en properties
    @Value("${token.api}")
    private String tokenApi;

    @Override
    public UsuarioDTO getEmpleadoReniec(String dni) {

        ResponseReniec responseReniec = getExecutionReniec(dni);
        UsuarioDTO empleado = new UsuarioDTO();
        if(responseReniec != null){
            empleado.setNombres(responseReniec.getNombres());
            empleado.setApellidos(responseReniec.getApellidoPaterno() + " " + responseReniec.getApellidoMaterno());
            empleado.setNumDoc(responseReniec.getNumeroDocumento());
            return empleado;
        }
        return null;
    }



    //Metodos de apoyo siempre privado
    private ResponseReniec getExecutionReniec(String dni){
        String auth = "Bearer "+tokenApi;
        ResponseReniec reniec = clientReniec.getInfoReniec(dni,auth);
        return reniec;
    }
}

