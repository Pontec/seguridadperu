package com.utp.seguridadperu.service;

import com.utp.seguridadperu.agregates.dto.IncidenciaHeatmapData;
import com.utp.seguridadperu.modelo.Incidencia;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IncidenciaService {
    Incidencia saveIncidencia(Incidencia incidencia);
    Incidencia getIncidencia(Long id);
    void deleteIncidencia(Long id);
    List<Incidencia> getAllIncidencias();

    List<IncidenciaHeatmapData> findGroupedIncidencias();
    Incidencia saveIncidenciaConImagenes(String tipo, String descripcion, double latitud, double longitud, List<MultipartFile> imagenes) throws IOException;

}
