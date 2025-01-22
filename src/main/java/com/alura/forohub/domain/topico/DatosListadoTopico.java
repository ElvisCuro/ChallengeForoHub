package com.alura.forohub.domain.topico;

import java.time.LocalDate;

public record DatosListadoTopico(Long id,
                                 String titulo,
                                String mensaje,
                                LocalDate fechaDeCreacion,
                                Boolean status,
                                 String autor,
                                 String curso) {

    public DatosListadoTopico(Topico topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaDeCreacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getCurso().toString());
    }


}
