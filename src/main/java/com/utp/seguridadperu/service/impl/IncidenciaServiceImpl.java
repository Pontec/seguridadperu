package com.utp.seguridadperu.service.impl;

import com.utp.seguridadperu.Repository.ImagenRepository;
import com.utp.seguridadperu.Repository.IncideciaRepository;
import com.utp.seguridadperu.Repository.UsuarioRepository;
import com.utp.seguridadperu.agregates.dto.IncidenciaDTO;
import com.utp.seguridadperu.agregates.dto.IncidenciaHeatmapData;
import com.utp.seguridadperu.modelo.Imagen;
import com.utp.seguridadperu.modelo.Incidencia;
import com.utp.seguridadperu.modelo.UsuarioModelo;
import com.utp.seguridadperu.service.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncidenciaServiceImpl implements IncidenciaService {

    @Autowired
    private IncideciaRepository incideciaRepository;

    @Autowired
    private ImagenRepository imagenRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Incidencia saveIncidencia(Incidencia incidencia, Long idUsuario) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new IllegalArgumentException("No se ha iniciado sesión");
        }

        UsuarioModelo usuarioModelo = usuarioRepository.findById(idUsuario).
        orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + idUsuario));

        incidencia.setUsuario(usuarioModelo);
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
    public Incidencia saveIncidenciaConImagenes(String tipo, String descripcion, double latitud, double longitud, List<MultipartFile> imagenes) throws IOException {
        Incidencia incidencia = new Incidencia();
        incidencia.setTipo(tipo);
        incidencia.setDescripcion(descripcion);
        incidencia.setLatitud(latitud);
        incidencia.setLongitud(longitud);
        incidencia.setFechaHora(LocalDateTime.now());

        for (MultipartFile archivo : imagenes) {
            Imagen imagen = new Imagen();
            imagen.setData(archivo.getBytes()); // Guarda el contenido binario de la imagen
            imagen.setIncidencia(incidencia);
            incidencia.getImagenes().add(imagen);
        }

        return incideciaRepository.save(incidencia);
    }

    public Page<IncidenciaDTO> getAllIncidencias(Pageable pageable) {
        // Obtén la página de Incidencia desde el repositorio
        Page<Incidencia> incidencias = incideciaRepository.findAll(pageable);

        // Convierte el contenido de Incidencia a IncidenciaDto
        List<IncidenciaDTO> incidenciaDtos = incidencias.getContent().stream().map(incidencia -> {
            IncidenciaDTO dto = new IncidenciaDTO();
            dto.setId(incidencia.getId());
            dto.setTipo(incidencia.getTipo());
            dto.setDescripcion(incidencia.getDescripcion());
            dto.setLatitud(incidencia.getLatitud());
            dto.setLongitud(incidencia.getLongitud());
            dto.setFechaHora(incidencia.getFechaHora());

            // Convertir cada imagen binaria a Base64 y agregarla a la lista de imágenes en el DTO
            List<String> imagenesBase64 = incidencia.getImagenes().stream()
                    .map(imagen -> "data:image/jpeg;base64," + Base64Utils.encodeToString(imagen.getData()))
                    .collect(Collectors.toList());
            dto.setImagenes(imagenesBase64);

            return dto;
        }).collect(Collectors.toList());

        // Crea una nueva instancia de PageImpl usando la lista de DTOs y los datos de paginación
        return new PageImpl<>(incidenciaDtos, pageable, incidencias.getTotalElements());
    }


}
