package com.utp.seguridadperu.service.impl;

import com.utp.seguridadperu.Repository.ComentarioRepository;
import com.utp.seguridadperu.modelo.Comentario;
import com.utp.seguridadperu.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Override
    public Comentario save(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    @Override
    public List<Comentario> obtenerComentarios(Long idIncidencia) {
        return comentarioRepository.findByIncidenciaId(idIncidencia);
    }
}
