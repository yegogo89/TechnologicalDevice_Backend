package com.technological.device.controller;

import com.technological.device.entity.Usuario;
import com.technological.device.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:3000/")
public class UsuarioController {
    private final UsuarioService service;
    public UsuarioController(UsuarioService service){
        this.service = service;
    }

    // control del registro
    @PostMapping("/register")
    public Usuario registrar(@RequestBody Usuario usuario){
        return  service.registrar(usuario);
    }

    // inicio de sesion login
    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario){

        return  service.login(
                usuario.getCorreoElectronico(),
                usuario.getPassword()
        );
    }


    /**@PostMapping
    public Usuario crear(@RequestBody Usuario u){
         return service.guardar(u);
    }**/


    @GetMapping
    public List<Usuario> listar(){
        return service.listar();
    }
}

