package com.alura.forohub.domain.topico;

import com.alura.forohub.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "topicos")
@Entity(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;

    public Topico(DatosRegistroTopico datosRegistroTopico, Usuario usuario) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.curso = datosRegistroTopico.curso();
        this.fechaDeCreacion = LocalDate.now();
        this.status = true;
        this.autor = usuario;
    }
    public void actualizarTopico(DatosActualizarTopico datosActualizarTopico) {
        if (datosActualizarTopico.titulo() != null) {
            this.titulo = datosActualizarTopico.titulo();
        }
        if (datosActualizarTopico.mensaje() != null) {
            this.mensaje = datosActualizarTopico.mensaje();
        }
        if (datosActualizarTopico.curso() != null) {
            this.curso = datosActualizarTopico.curso();
        }
        this.fechaDeCreacion = LocalDate.now();
    }
    public void eliminarTopico() {
        this.status = false;
    }

}
