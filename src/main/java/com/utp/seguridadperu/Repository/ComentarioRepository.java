package com.utp.seguridadperu.Repository;

import com.utp.seguridadperu.modelo.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByIncidenciaId(Long idIncidencia);
}
