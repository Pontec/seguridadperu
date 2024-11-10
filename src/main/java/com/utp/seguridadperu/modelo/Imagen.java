package com.utp.seguridadperu.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Base64;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "imagenes")
@Entity
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne
    @JoinColumn(name = "incidencia_id")
    private Incidencia incidencia;

//    // Método para obtener la imagen en Base64
//    public String getDatosBase64() {
//        return Base64.getEncoder().encodeToString(this.data);
//    }

    @ManyToOne
    @JoinColumn(name = "denuncia_id")
    private Denuncia denuncia;

}
