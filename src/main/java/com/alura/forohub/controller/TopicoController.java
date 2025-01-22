package com.alura.forohub.controller;


import com.alura.forohub.domain.topico.*;
import com.alura.forohub.domain.usuarios.Usuario;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public void registrarTopicos(@RequestBody DatosRegistroTopico datosRegistroTopico){
        System.out.println(datosRegistroTopico);
    }

    @GetMapping
    public Page<DatosListadoTopico> listadoMedicos(@PageableDefault(size = 10) Pageable paginacion) {
      return topicoRepository.findByStatusTrue(paginacion).map(DatosListadoTopico::new);
    }

    //DELETE LOGICO
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        topico.eliminarTopico();
        return ResponseEntity.noContent().build();

    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {

            // Verifica si el tópico existe y está activo
            Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());

            // Actualiza el tópico
            topico.actualizarTopico(datosActualizarTopico);

            return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(),
                    topico.getAutor().getNombre(),
                    topico.getTitulo(),
                    topico.getMensaje(),
                    topico.getCurso(),
                    topico.getFechaDeCreacion()));
    }
}
