package com.utp.seguridadperu.modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "incidencias")
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;
    private String descripcion;
    private double latitud;
    private double longitud;
    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    @OneToMany(mappedBy = "incidencia", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Imagen> imagenes = new ArrayList<>();

    @OneToMany(mappedBy = "incidencia", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Comentario> comentarios = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private UsuarioModelo usuario;

}
