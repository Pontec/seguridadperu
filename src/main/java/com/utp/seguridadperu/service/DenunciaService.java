package com.utp.seguridadperu.service;

import com.utp.seguridadperu.modelo.Denuncia;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DenunciaService {

    Denuncia saveDenunciaConImagenes(
            Long usuarioId,
            String tipo,
            String descripcion,
            double latitud,
            double longitud,
            List<MultipartFile> imagenes
    ) throws IOException;
}
