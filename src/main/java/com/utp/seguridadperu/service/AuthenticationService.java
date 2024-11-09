package com.utp.seguridadperu.service;

import com.utp.seguridadperu.agregates.request.SignInRequest;
import com.utp.seguridadperu.agregates.request.SignUpRequest;
import com.utp.seguridadperu.agregates.response.AuthenticationResponse;
import com.utp.seguridadperu.modelo.UsuarioModelo;

import java.util.List;

public interface AuthenticationService {
    UsuarioModelo signUpAdmin(SignUpRequest signUpRequest);
    List<UsuarioModelo> todos();

    AuthenticationResponse signin(SignInRequest signInRequest);


}
