package com.utp.seguridadperu.agregates.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class IncidenciaDTO {

    private Long id;
    private String tipo;
    private String descripcion;
    private double latitud;
    private double longitud;
    private LocalDateTime fechaHora;
    private List<String> imagenes;
    private String usuario;

}
