package com.alura.forohub.domain.usuarios;

public record DatosRespuestaUsuario(
        Long idUsuario,
        String nombre,
        String email,
        String nuevoJwTtoken
) {
}
