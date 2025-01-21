package com.alura.forohub.controller;


import com.alura.forohub.domain.topico.DatosRegistroTopico;
import com.alura.forohub.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public void registrarTopicos(@RequestBody DatosRegistroTopico datosRegistroTopico){
        System.out.println(datosRegistroTopico);
    }



}
