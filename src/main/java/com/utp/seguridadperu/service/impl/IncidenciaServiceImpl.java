package com.utp.seguridadperu.service.impl;

import com.utp.seguridadperu.Repository.IncideciaRepository;
import com.utp.seguridadperu.agregates.dto.IncidenciaHeatmapData;
import com.utp.seguridadperu.modelo.Incidencia;
import com.utp.seguridadperu.service.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IncidenciaServiceImpl implements IncidenciaService {

    @Autowired
    private IncideciaRepository incideciaRepository;

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
}
