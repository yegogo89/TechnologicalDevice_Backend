package com.technological.device.service;

import com.technological.device.entity.Usuario;
import com.technological.device.repository.UsuarioRepository;
import com.technological.device.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UsuarioService {


    @Autowired
    private final UsuarioRepository repo;
    @Autowired
    private JwtUtil jwtUtil;

    public UsuarioService(UsuarioRepository repo) {
        this.repo= repo;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Usuario registrar(Usuario usuario){
        //encriptar password antes de guardar
        String passwordEncriptado = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passwordEncriptado);

        // asignar roles
        usuario.setRol("USER");

        //guardar en la base de datos
        return repo.save(usuario);
    }

    public String login(String correo, String password){

        Usuario usuario = repo
                .findByCorreoElectronico(correo)
                .orElseThrow(() -> new RuntimeException("usuario no encontrado"));

        if (!passwordEncoder.matches(password, usuario.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Contraseña incorrecta");
        }
        return  jwtUtil.generateToken(usuario.getCorreoElectronico());
    }

    public Usuario guardar(Usuario u){
        return  repo.save(u);
    }

    public List<Usuario> listar(){
        return repo.findAll();
     }






}
