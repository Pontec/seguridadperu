package com.utp.seguridadperu.controller;

import com.utp.seguridadperu.agregates.dto.MessageDTO;
import com.utp.seguridadperu.service.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
@RequestMapping("/api/denuncias")
public class DenunciaController {

    @Autowired
    private DenunciaService denunciaService;

    @GetMapping
    public ResponseEntity<?> getAllDenuncias() {
        return new ResponseEntity<>(denunciaService.getAllDenuncias(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveDenunciaConImagenes(  @RequestParam("usuarioId") Long usuarioId,
                                                       @RequestParam("asunto") String asunto,
                                                       @RequestParam("descripcion") String descripcion,
                                                       @RequestParam("latitud") double latitud,
                                                       @RequestParam("longitud") double longitud,
                                                       @RequestParam("imagenes") List<MultipartFile> imagenes) throws GeneralSecurityException, IOException {


        System.out.println("holaaaa");
        denunciaService.saveDenunciaConImagenes(usuarioId, asunto, descripcion, latitud, longitud, imagenes);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
