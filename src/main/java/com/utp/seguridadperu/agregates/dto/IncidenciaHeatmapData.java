package com.utp.seguridadperu.agregates.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IncidenciaHeatmapData {
    private double latitud;
    private double longitud;
    private long cantidad;
}
