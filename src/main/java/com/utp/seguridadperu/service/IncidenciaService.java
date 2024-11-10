package com.utp.seguridadperu.service;

import com.utp.seguridadperu.agregates.dto.IncidenciaDTO;
import com.utp.seguridadperu.agregates.dto.IncidenciaHeatmapData;
import com.utp.seguridadperu.modelo.Incidencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IncidenciaService {
    Incidencia saveIncidencia(Incidencia incidencia, Long idUsuario);
    Incidencia getIncidencia(Long id);
    void deleteIncidencia(Long id);
    List<Incidencia> getAllIncidencias();

    List<IncidenciaHeatmapData> findGroupedIncidencias();
    Incidencia saveIncidenciaConImagenes(String tipo, String descripcion, double latitud, double longitud, List<MultipartFile> imagenes) throws IOException;

    Page<IncidenciaDTO> getAllIncidencias(Pageable pageable);



}
