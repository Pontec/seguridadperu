package com.utp.seguridadperu.service;

import com.utp.seguridadperu.modelo.Comentario;

import java.util.List;

public interface ComentarioService {

    Comentario save(Comentario comentario);
    List<Comentario> obtenerComentarios(Long idIncidencia);
}
