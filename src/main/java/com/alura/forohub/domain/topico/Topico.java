package com.alura.forohub.domain.topico;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name = "topicos")
@Entity(name = "topicos")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDate fechaDeCreacion;
    private Boolean status;
    @Enumerated(EnumType.STRING)
    private Curso curso;

}
