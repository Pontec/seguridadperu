package com.utp.seguridadperu.controller;

import com.utp.seguridadperu.agregates.dto.IncidenciaHeatmapData;
import com.utp.seguridadperu.modelo.Incidencia;
import com.utp.seguridadperu.service.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidentes")
public class IncidenciaController {

    @Autowired
    private IncidenciaService incidenciaService;

    @GetMapping
    public ResponseEntity<?> getAllIncidencias() {
        return new ResponseEntity<>(incidenciaService.getAllIncidencias(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveIncidencia(@RequestBody Incidencia incidencia) {
        return new ResponseEntity<>(incidenciaService.saveIncidencia(incidencia), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getIncidencia(Long id) {
        return new ResponseEntity<>(incidenciaService.getIncidencia(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIncidencia(Long id) {
        incidenciaService.deleteIncidencia(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/heatmap")
    public List<IncidenciaHeatmapData> getHeatmapData() {
        return incidenciaService.findGroupedIncidencias();
    }
}
