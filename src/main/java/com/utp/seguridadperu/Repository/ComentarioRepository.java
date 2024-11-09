package com.utp.seguridadperu.Repository;

import com.utp.seguridadperu.modelo.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    List<Comentario> findByIncidenciaId(Long idIncidencia);

    List<Comentario> findByUsuarioId(Long idUsuario);

    List<Comentario> findByIncidenciaIdOrderByFechaAsc(Long idIncidencia);

    List<Comentario> findByIncidenciaIdOrderByFechaDesc(Long idIncidencia);

    Long countByIncidenciaId(Long idIncidencia);

    void deleteByIncidenciaId(Long idIncidencia);

}
