package com.utp.seguridadperu.controller;

import com.utp.seguridadperu.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    public final UsuarioService usuarioService;

    @GetMapping("/hola")
    public ResponseEntity<String> saludoAdmin(){
        return ResponseEntity.ok("Hola admin");
    }

    @PostMapping("/empleado/{dni}")
    public ResponseEntity<?> empleado(@PathVariable String dni){
        return ResponseEntity.ok(usuarioService.getEmpleadoReniec(dni));
    }
}
