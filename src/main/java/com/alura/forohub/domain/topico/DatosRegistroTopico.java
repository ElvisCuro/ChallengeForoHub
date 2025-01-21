package com.alura.forohub.domain.topico;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//Validation
public record DatosRegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        Curso curso,
        @NotNull
        Long idAutor
) {
}
