package com.utp.seguridadperu.service.impl;

import com.utp.seguridadperu.Repository.DenunciaRepository;
import com.utp.seguridadperu.Repository.ImagenRepository;
import com.utp.seguridadperu.Repository.UsuarioRepository;
import com.utp.seguridadperu.agregates.response.Res;
import com.utp.seguridadperu.configuracion.GoogleDrive;
import com.utp.seguridadperu.modelo.Denuncia;
import com.utp.seguridadperu.modelo.Imagen;
import com.utp.seguridadperu.modelo.UsuarioModelo;
import com.utp.seguridadperu.service.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DenunciaServiceImpl implements DenunciaService {

    @Autowired
    private DenunciaRepository denunciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ImagenRepository imagenRepository;


    @Autowired
    private GoogleDrive googleDriveService;

    public Denuncia saveDenunciaConImagenes(Long usuarioId, String asunto, String descripcion, double latitud, double longitud, List<MultipartFile> imagenes) throws IOException, GeneralSecurityException {
        // Buscar el usuario por ID
        UsuarioModelo usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Crear una nueva denuncia
        Denuncia denuncia = new Denuncia();

        denuncia.setUsuario(usuario);

        denuncia.setAsunto(asunto);

        denuncia.setDescripcion(descripcion);

        denuncia.setLatitud(latitud);
        denuncia.setLongitud(longitud);

        denuncia.setFechaHora(LocalDateTime.now());



        for (MultipartFile archivo : imagenes) {
            // Crear un archivo temporal para subir a Google Drive
            File tempFile = File.createTempFile("temp", null);
            archivo.transferTo(tempFile);

            // Subir el archivo a Google Drive y obtener la URL
            Res res = googleDriveService.uploadImageToDrive(tempFile);

            if (res.getStatus() == 200) {
                Imagen imagen = new Imagen();

                imagen.setUrl(res.getUrl()); // Guarda la URL de la imagen
                imagen.setDenuncia(denuncia);
                denuncia.getImagenes().add(imagen);
            }

            // Eliminar el archivo temporal
            tempFile.delete();
        }

        // Guardar la denuncia para obtener su ID
        return denunciaRepository.save(denuncia);


    }

    @Override
    public List<Denuncia> getAllDenuncias() {
        return denunciaRepository.findAll();
    }
}
