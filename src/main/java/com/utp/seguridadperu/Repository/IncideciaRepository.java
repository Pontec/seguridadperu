package com.utp.seguridadperu.Repository;

import com.utp.seguridadperu.agregates.dto.IncidenciaHeatmapData;
import com.utp.seguridadperu.modelo.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface IncideciaRepository extends JpaRepository<Incidencia, Long> {

    @Query("SELECT new com.utp.seguridadperu.agregates.dto.IncidenciaHeatmapData(ROUND(i.latitud, 2), ROUND(i.longitud, 2), COUNT(i)) " +
            "FROM Incidencia i " +
            "GROUP BY ROUND(i.latitud, 2), ROUND(i.longitud, 2)")
    List<IncidenciaHeatmapData> findGroupedIncidencias();

}
