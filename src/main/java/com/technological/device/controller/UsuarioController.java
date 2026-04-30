package com.technological.device.controller;

import com.technological.device.entity.Usuario;
import com.technological.device.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService service;
    public UsuarioController(UsuarioService service){
        this.service = service;
    }
    @PostMapping
    public Usuario crear(@RequestBody Usuario u){
        return service.guardar(u);
    }
    @GetMapping
    public List<Usuario> listar(){
        return service.listar();
    }
}

