package com.example.demo.Repositorios;

import com.example.demo.Entidades.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {
    /**
     * Buscar usuario por nombre de usuario y contraseña (para login)
     */
    Optional<Usuarios> findByNombreUsuarioAndPassword(String nombreUsuario, String password);
    
    /**
     * Buscar usuario por nombre de usuario
     */
    Optional<Usuarios> findByNombreUsuario(String nombreUsuario);
    
    /**
     * Verificar si existe un usuario con ese nombre
     */
    boolean existsByNombreUsuario(String nombreUsuario);
}
