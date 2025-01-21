package com.alura.forohub.controller;


import com.alura.forohub.domain.topico.DatosListadoTopico;
import com.alura.forohub.domain.topico.DatosRegistroTopico;
import com.alura.forohub.domain.topico.Topico;
import com.alura.forohub.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<DatosListadoTopico> listadoTopicos() {
        return topicoRepository.findAll().stream().map(DatosListadoTopico :: new).toList();
    }



}
