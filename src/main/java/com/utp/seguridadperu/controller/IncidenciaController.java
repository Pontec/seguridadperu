package com.utp.seguridadperu.controller;

import com.utp.seguridadperu.agregates.dto.IncidenciaDTO;
import com.utp.seguridadperu.agregates.dto.IncidenciaHeatmapData;
import com.utp.seguridadperu.modelo.Incidencia;
import com.utp.seguridadperu.service.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping("/crear")
    public ResponseEntity<Incidencia> crearIncidencia(
            @RequestParam String tipo,
            @RequestParam String descripcion,
            @RequestParam double latitud,
            @RequestParam double longitud,
            @RequestParam (value = "imagenes", required = false) List<MultipartFile> imagenes) throws IOException {
        Incidencia incidencia = incidenciaService.saveIncidenciaConImagenes(tipo, descripcion, latitud, longitud, imagenes);
        return ResponseEntity.ok(incidencia);
    }

//    @PostMapping("/save")
//    public ResponseEntity<?> saveIncidencia(@RequestBody Incidencia incidencia) {
//        return new ResponseEntity<>(incidenciaService.saveIncidencia(incidencia), HttpStatus.CREATED);
//    }

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

    //Solo las 10 primeras incidencias
    @GetMapping("/ultimos")
    public ResponseEntity<Page<IncidenciaDTO>> getUltimos() {
        Page<IncidenciaDTO> ultimasIncidencias = incidenciaService.getAllIncidencias(PageRequest.of(0, 10));
        return new ResponseEntity<>(ultimasIncidencias, HttpStatus.OK);
    }

}

