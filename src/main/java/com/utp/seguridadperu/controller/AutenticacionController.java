package com.utp.seguridadperu.controller;


import com.utp.seguridadperu.agregates.request.SignInRequest;
import com.utp.seguridadperu.agregates.request.SignUpRequest;
import com.utp.seguridadperu.agregates.response.AuthenticationResponse;
import com.utp.seguridadperu.modelo.UsuarioModelo;
import com.utp.seguridadperu.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/autenticacion")
@RequiredArgsConstructor
public class AutenticacionController {
    private final AuthenticationService authenticationService;


    //Creacion de perfil administrador
    @PostMapping("/signupadmin")
    public ResponseEntity<UsuarioModelo> signUpAdmin(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signUpAdmin(signUpRequest));
    }

    //Iniciar secion
    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> signin(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.signin(signInRequest));
    }

    @GetMapping("/todos")
    public ResponseEntity<?> todos(){
        return ResponseEntity.ok(authenticationService.todos());
    }
}
