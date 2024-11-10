package com.utp.seguridadperu.service.impl;

import com.utp.seguridadperu.Repository.DenunciaRepository;
import com.utp.seguridadperu.Repository.ImagenRepository;
import com.utp.seguridadperu.Repository.UsuarioRepository;
import com.utp.seguridadperu.modelo.Denuncia;
import com.utp.seguridadperu.modelo.Imagen;
import com.utp.seguridadperu.modelo.UsuarioModelo;
import com.utp.seguridadperu.service.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public Denuncia saveDenunciaConImagenes(
            Long usuarioId,
            String tipo,
            String descripcion,
            double latitud,
            double longitud,
            List<MultipartFile> imagenes
    ) throws IOException {
        // Buscar el usuario por ID
        UsuarioModelo usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Crear una nueva denuncia
        Denuncia denuncia = new Denuncia();
        denuncia.setUsuario(usuario);
        denuncia.setTipo(tipo);
        denuncia.setDescripcion(descripcion);
        denuncia.setLatitud(latitud);
        denuncia.setLongitud(longitud);
        denuncia.setFechaHora(LocalDateTime.now());

        // Guardar la denuncia para obtener su ID
        Denuncia denunciaGuardada = denunciaRepository.save(denuncia);

        // Guardar las imágenes
        if (imagenes != null && !imagenes.isEmpty()) {
            for (MultipartFile file : imagenes) {
                Imagen imagen = new Imagen();
                //imagen.setUrl(file.getBytes());
                imagen.setDenuncia(denunciaGuardada);
                imagenRepository.save(imagen);
            }
        }

        return denunciaGuardada;
    }
}
