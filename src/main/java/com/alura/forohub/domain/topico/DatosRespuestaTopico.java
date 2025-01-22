package com.alura.forohub.domain.topico;

import java.time.LocalDate;

public record DatosRespuestaTopico(
        Long id,
        String nombreAutor,
        String titulo,
        String mensaje,
        String nombreCurso,
        LocalDate fechaDeCreacion
) {
}
