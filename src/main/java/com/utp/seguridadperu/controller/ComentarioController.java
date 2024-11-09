package com.utp.seguridadperu.controller;

import com.utp.seguridadperu.modelo.Comentario;
import com.utp.seguridadperu.service.ComentarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/comentarios")
public class ComentarioController {

    private final ComentarioService comentarioService;

    @GetMapping("/getAll")
    private ResponseEntity<List<Comentario>> obtenerComentarios(){
        return ResponseEntity.ok(comentarioService.findAll());
    }

    @GetMapping("/get/{id}")
    private ResponseEntity<Comentario> obtenerComentario(Long id){
        return ResponseEntity.ok(comentarioService.findById(id).orElseThrow(()-> new RuntimeException("Comentario no encontrado")));
    }

    @GetMapping("/getByIncidencia/{idIncidencia}")
    private ResponseEntity<List<Comentario>> obtenerComentariosPorIncidencia(Long idIncidencia){
        return ResponseEntity.ok(comentarioService.findByIncidenciaId(idIncidencia));
    }

    @GetMapping("/getByUsuario/{idUsuario}")
    private ResponseEntity<List<Comentario>> obtenerComentariosPorUsuario(Long idUsuario){
        return ResponseEntity.ok(comentarioService.findByUsuarioId(idUsuario));
    }

    @PostMapping("/save")
    private ResponseEntity<Comentario> guardarComentario(@RequestBody Comentario comentario){
        return ResponseEntity.ok(comentarioService.save(comentario));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> eliminarComentario(Long id){
        comentarioService.deleteById(id);
        return ResponseEntity.ok("Comentario eliminado");
    }

    @GetMapping("/countByIncidencia/{idIncidencia}")
    private ResponseEntity<Long> contarComentariosPorIncidencia(Long idIncidencia){
        return ResponseEntity.ok(comentarioService.countByIncidenciaId(idIncidencia));
    }

}
