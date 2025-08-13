package com.example.demo.Repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Entidades.Documento;
import com.example.demo.Entidades.Usuarios;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {

    /**
     * Buscar documentos de un usuario ordenados por fecha (más recientes primero)
     */
    List<Documento> findByUsuarioOrderByFechaCreacionDesc(Usuarios usuario);
    
    /**
     * Buscar documentos por ID de usuario
     */
    List<Documento> findByUsuarioIdUsuario(Long idUsuario);
    
    /**
     * Contar documentos de un usuario
     */
    long countByUsuario(Usuarios usuario);
    
    /**
     * Buscar documentos por título que contenga texto
     */
    List<Documento> findByTituloContaining(String titulo);
}