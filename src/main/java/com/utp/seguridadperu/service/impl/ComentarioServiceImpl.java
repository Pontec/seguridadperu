package com.utp.seguridadperu.service.impl;

import com.utp.seguridadperu.Repository.ComentarioRepository;
import com.utp.seguridadperu.modelo.Comentario;
import com.utp.seguridadperu.service.ComentarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ComentarioServiceImpl implements ComentarioService {

    private final ComentarioRepository comentarioRepository;

    @Override
    public List<Comentario> findAll() {
        return comentarioRepository.findAll();
    }

    @Override
    public Optional<Comentario> findById(Long id) {
        return comentarioRepository.findById(id);
    }

    @Override
    public Comentario save(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    @Override
    public void deleteById(Long id) {
        Comentario comentario = comentarioRepository.findById(id).orElseThrow(()-> new RuntimeException("Comentario no encontrado"));
        comentarioRepository.delete(comentario);
    }

    @Override
    public List<Comentario> findByIncidenciaId(Long idIncidencia) {
        return comentarioRepository.findByIncidenciaId(idIncidencia);
    }

    @Override
    public List<Comentario> findByUsuarioId(Long idUsuario) {
        return comentarioRepository.findByUsuarioId(idUsuario);
    }

    @Override
    public Long countByIncidenciaId(Long idIncidencia) {
        return comentarioRepository.countByIncidenciaId(idIncidencia);
    }
}
