package com.utp.seguridadperu.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @JsonIgnoreProperties("incidencias")
    private UsuarioModelo usuario;

    @OneToMany(mappedBy = "incidencia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("incidencia")
    private List<Imagen> imagenes = new ArrayList<>();


    public Incidencia(Long id) {
        this.id = id;
    }
}
