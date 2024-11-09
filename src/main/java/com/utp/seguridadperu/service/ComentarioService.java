package com.utp.seguridadperu.service;

import com.utp.seguridadperu.modelo.Comentario;

import java.util.List;
import java.util.Optional;

public interface ComentarioService {

    List<Comentario> findAll();

    Optional<Comentario> findById(Long id);

    Comentario save(Comentario comentario);

    void deleteById(Long id);

    List<Comentario> findByIncidenciaId(Long idIncidencia);

    List<Comentario> findByUsuarioId(Long idUsuario);

    Long countByIncidenciaId(Long idIncidencia);
}
