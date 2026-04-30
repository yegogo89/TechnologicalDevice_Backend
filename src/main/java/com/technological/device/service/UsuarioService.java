package com.technological.device.service;

import com.technological.device.entity.Usuario;
import com.technological.device.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repo;
    public UsuarioService(UsuarioRepository repo) {
        this.repo= repo;
    }
    public Usuario guardar(Usuario u){
        return  repo.save(u);
    }
     public List<Usuario> listar(){
        return repo.findAll();
     }
}
