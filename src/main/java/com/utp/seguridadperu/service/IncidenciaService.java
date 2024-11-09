package com.utp.seguridadperu.service;

import com.utp.seguridadperu.agregates.dto.IncidenciaHeatmapData;
import com.utp.seguridadperu.modelo.Incidencia;

import java.util.List;

public interface IncidenciaService {
    Incidencia saveIncidencia(Incidencia incidencia);
    Incidencia getIncidencia(Long id);
    void deleteIncidencia(Long id);
    List<Incidencia> getAllIncidencias();

    List<IncidenciaHeatmapData> findGroupedIncidencias();
}
