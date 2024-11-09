package com.utp.seguridadperu.agregates.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO {
    private Long idEmpleado;
    private String nombres;
    private String apellidos;
    private String correo;
    private String password;
    private String numDoc;
    private String direccion;
    private String telefono;
}
