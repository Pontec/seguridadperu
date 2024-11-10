package com.utp.seguridadperu.controller;

import com.utp.seguridadperu.modelo.Comentario;
import com.utp.seguridadperu.modelo.Incidencia;
import com.utp.seguridadperu.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentario")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @PostMapping("/{idIncidencia}/comentarios")
    public ResponseEntity<Comentario> agregarComentario(@PathVariable Long idIncidencia,
                                                        @RequestBody Comentario comentario) {
        // Asigna la incidencia al comentario
        comentario.setIncidencia(new Incidencia(idIncidencia));
        Comentario nuevoComentario = comentarioService.save(comentario);
        return ResponseEntity.ok(nuevoComentario);
    }

    @GetMapping("/{idIncidencia}/comentarios")
    public ResponseEntity<List<Comentario>> obtenerComentarios(
            @PathVariable Long idIncidencia) {

        List<Comentario> comentarios = comentarioService.obtenerComentarios(idIncidencia);
        return ResponseEntity.ok(comentarios);
    }


}
