package com.utp.seguridadperu.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter @Setter @Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comentario")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comentario")
    private Long idComentario;
    @Column(nullable = false)
    private String comentario;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", insertable = false, updatable = false)
    private UsuarioModelo usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_incidencia", insertable = false, updatable = false)
    @JsonBackReference
    private Incidencia incidencia;

}
