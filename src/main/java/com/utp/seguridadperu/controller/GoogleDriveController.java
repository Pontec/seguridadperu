package com.utp.seguridadperu.controller;

import com.utp.seguridadperu.agregates.response.Res;
import com.utp.seguridadperu.configuracion.GoogleDrive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping("/api/googleDrive")
public class GoogleDriveController {

    @Autowired
    private GoogleDrive service;

    @PostMapping("/uploadToGoogleDrive")
    public Object handleFileUpload(@RequestParam("image") MultipartFile file) throws IOException, GeneralSecurityException {
        if (file.isEmpty()) {
            return "File is empty";
        }

        File tempFile = File.createTempFile("temp", null);
        file.transferTo(tempFile);
        Res res = service.uploadImageToDrive(tempFile);
        System.out.println(res);
        return res;
    }
}
