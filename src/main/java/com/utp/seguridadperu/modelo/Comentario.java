package com.utp.seguridadperu.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comentario")
    private Long id;
    @Column(nullable = false)
    private String comentario;
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "id_usuario, nullable = false")
    private UsuarioModelo usuario;

    @ManyToOne
    @JoinColumn(name = "id_incidencia, nullable = false")
    private Incidencia incidencia;
}
