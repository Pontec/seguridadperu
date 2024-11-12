package com.utp.seguridadperu.controller.Advice;


import com.utp.seguridadperu.Repository.UsuarioRepository;
import com.utp.seguridadperu.agregates.dto.UsuarioDTO;
import com.utp.seguridadperu.modelo.UsuarioModelo;
import com.utp.seguridadperu.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/usuario/{dni}")
  public   ResponseEntity<UsuarioDTO> busccarPorDni(@PathVariable("dni") String dni){
       Optional<UsuarioModelo> usuarioDTO = usuarioRepository.findByNumDoc(dni);

       UsuarioDTO otroUser = new UsuarioDTO();
       if(usuarioDTO.isPresent()){
        UsuarioModelo userExist =   usuarioDTO.get();
           otroUser.setNumDoc(userExist.getNumDoc());
           otroUser.setNombres(userExist.getNombres());
           otroUser.setApellidos(userExist.getApellidos());
           otroUser.setTelefono("973413882");

       }


    return    ResponseEntity.ok(otroUser);
    }
}
