package com.technological.device.security;

import com.technological.device.entity.Usuario;
import com.technological.device.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.ServletException;
import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {


    private final JwtUtil jwtUtil;
    private final UsuarioRepository repo;

    //constructor
    public JwtFilter(JwtUtil jwtUtil, UsuarioRepository repo){
        this.jwtUtil = jwtUtil;
        this.repo = repo;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws IOException, ServletException {

        String path = request.getRequestURI();

        if (path.equals("/api/usuarios/login") ||
                path.equals("/api/usuarios/register")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        System.out.println("HEADER: " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        System.out.println("TOKEN: " + token);

        if (!jwtUtil.isTokenValid(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String email = jwtUtil.extraerEmail(token);
        System.out.println("EMAIL: " + email);

        Usuario usuario = repo.findByCorreoElectronico(email).orElse(null);
        System.out.println("USUARIO: " + usuario);

        if (usuario != null) {
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()))
                    );
            System.out.println("ROL: " + usuario.getRol());

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}
