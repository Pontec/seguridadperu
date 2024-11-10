package com.utp.seguridadperu.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "denuncias")
public class Denuncia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;
    private String descripcion;
    private LocalDateTime fechaHora;
    private double latitud;
    private double longitud;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioModelo usuario;

    @OneToMany(mappedBy = "denuncia", cascade = CascadeType.ALL)
    private List<Imagen> imagenes;
}
