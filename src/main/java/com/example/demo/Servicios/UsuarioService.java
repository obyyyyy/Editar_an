package com.example.demo.Servicios;

import com.example.demo.Entidades.Usuarios;
import com.example.demo.Repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    /**
     * Autenticar usuario con credenciales
     */
    public Usuarios autenticar(String nombreUsuario, String password) {
        Optional<Usuarios> usuario = usuarioRepository.findByNombreUsuarioAndPassword(nombreUsuario, password);
        return usuario.orElse(null);
    }
    
    /**
     * Buscar usuario por ID
     */
    public Usuarios buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }
}