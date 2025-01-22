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
    public ResponseEntity<DatosRespuestaTopico> crearNuevoTopico(
            @RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
            UriComponentsBuilder uriComponentsBuilder) {
        // Busca al autor por su id
        Usuario usuario = usuarioRepository.findById(datosRegistroTopico.idAutor())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe un autor con el id especificado"));

        // Crea el tópico con el autor encontrado
        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico, usuario));

        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(
                topico.getId(),
                topico.getAutor().getNombre(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getCurso(),
                topico.getFechaDeCreacion()
        );

        URI url = uriComponentsBuilder.path("topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping()
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopicos(@PageableDefault(size = 2) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findByStatusTrue(paginacion).map(DatosListadoTopico::new));
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

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> mostrarUnTopicoPorId(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosRespuestaTopico(
                topico.getId(),
                topico.getAutor().getNombre(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getCurso(),
                topico.getFechaDeCreacion()
        );

        return ResponseEntity.ok(datosTopico);
    }
}
