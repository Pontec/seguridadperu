package com.utp.seguridadperu.service.impl;

import com.utp.seguridadperu.Repository.ImagenRepository;
import com.utp.seguridadperu.Repository.IncideciaRepository;
import com.utp.seguridadperu.Repository.UsuarioRepository;
import com.utp.seguridadperu.agregates.dto.IncidenciaHeatmapData;
import com.utp.seguridadperu.modelo.Imagen;
import com.utp.seguridadperu.modelo.Incidencia;
import com.utp.seguridadperu.modelo.UsuarioModelo;
import com.utp.seguridadperu.service.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class IncidenciaServiceImpl implements IncidenciaService {

    @Autowired
    private IncideciaRepository incideciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ImagenRepository imagenRepository;

    @Override
    public Incidencia saveIncidencia(Incidencia incidencia) {
        //guardando la fecha y hora actual
        incidencia.setFechaHora(LocalDateTime.now());
        return incideciaRepository.save(incidencia);
    }

    @Override
    public Incidencia getIncidencia(Long id) {
        return incideciaRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteIncidencia(Long id) {
        incideciaRepository.deleteById(id);
    }

    @Override
    public List<Incidencia> getAllIncidencias() {
        return incideciaRepository.findAll();
    }

    @Override
    public List<IncidenciaHeatmapData> findGroupedIncidencias() {
        return incideciaRepository.findGroupedIncidencias();
    }

    @Override
    public Incidencia saveIncidenciaConImagenes(String tipo, String descripcion, double latitud, double longitud, Long usuarioId, List<MultipartFile> imagenes) throws IOException {
        Incidencia incidencia = new Incidencia();
        incidencia.setTipo(tipo);
        incidencia.setDescripcion(descripcion);
        incidencia.setLatitud(latitud);
        incidencia.setLongitud(longitud);
        incidencia.setFechaHora(LocalDateTime.now());
        UsuarioModelo usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        incidencia.setUsuario(usuario);

        for (MultipartFile archivo : imagenes) {
            Imagen imagen = new Imagen();
            imagen.setData(archivo.getBytes()); // Guarda el contenido binario de la imagen
            imagen.setIncidencia(incidencia);
            incidencia.getImagenes().add(imagen);
        }
        return incideciaRepository.save(incidencia);
    }

}
