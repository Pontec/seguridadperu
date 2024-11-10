package com.utp.seguridadperu.controller;

import com.utp.seguridadperu.service.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/denuncias")
public class DenunciaController {

    @Autowired
    private DenunciaService denunciaService;

    @GetMapping
    public ResponseEntity<?> getAllDenuncias() {
        return new ResponseEntity<>(denunciaService.getAllDenuncias(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveDenunciaConImagenes() {

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
