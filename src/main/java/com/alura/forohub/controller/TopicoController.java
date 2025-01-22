package com.alura.forohub.controller;


import com.alura.forohub.domain.topico.*;
import com.alura.forohub.domain.usuarios.Usuario;
import com.alura.forohub.domain.usuarios.UsuarioRepository;
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

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping()
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopicos(@PageableDefault(size = 2) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findByStatusTrue(paginacion).map(DatosListadoTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> mostrarUnTopicoPorId(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosRespuestaTopico(
                topico.getId(),
                topico.getAutor().getNombre(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getCurso().toString(),
                topico.getFechaDeCreacion()
        );

        return ResponseEntity.ok(datosTopico);
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> crearNuevoTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                                                 UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = usuarioRepository.findById(datosRegistroTopico.idAutor())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico, usuario));

        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(
                topico.getId(),
                topico.getAutor().getNombre(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getCurso().toString(),
                topico.getFechaDeCreacion()
        );
        URI url = uriComponentsBuilder.path("topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
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

            Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
            topico.actualizarTopico(datosActualizarTopico);

            return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(),
                    topico.getAutor().getNombre(),
                    topico.getTitulo(),
                    topico.getMensaje(),
                    topico.getCurso().toString(),
                    topico.getFechaDeCreacion()));
    }

}
