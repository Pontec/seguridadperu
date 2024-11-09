package com.utp.seguridadperu.agregates.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBase<T> {
    //La estructura que nosotros vamos a utilizar para responder
    private int code;
    private String message;
    private Optional<T> data;

}
