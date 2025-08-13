package com.example.demo.Servicios;

import com.example.demo.Entidades.Documento;
import com.example.demo.Entidades.Usuarios;
import com.example.demo.Repositorios.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentoService {
    
    @Autowired
    private DocumentoRepository documentoRepository;
    
    /**
     * Obtener todos los documentos de un usuario
     */
    public List<Documento> obtenerDocumentosDelUsuario(Usuarios usuario) {
        return documentoRepository.findByUsuarioOrderByFechaCreacionDesc(usuario);
    }
    
    /**
     * Buscar documento por ID
     */
    public Documento buscarPorId(Long id) {
        return documentoRepository.findById(id).orElse(null);
    }
    
    /**
     * Crear nuevo documento
     */
    public Documento crear(String titulo, String contenido, Usuarios usuario) {
        Documento documento = new Documento();
        documento.setTitulo(titulo);
        documento.setContenido(contenido);
        documento.setUsuario(usuario);
        return documentoRepository.save(documento);
    }
    
    /**
     * Actualizar documento existente
     */
    public Documento actualizar(Long id, String titulo, String contenido, Usuarios usuario) {
        Optional<Documento> documentoOpt = documentoRepository.findById(id);
        
        if (documentoOpt.isPresent()) {
            Documento documento = documentoOpt.get();
            
            // Verificar que pertenezca al usuario
            if (documento.getUsuario().getIdUsuario().equals(usuario.getIdUsuario())) {
                documento.setTitulo(titulo);
                documento.setContenido(contenido);
                return documentoRepository.save(documento);
            }
        }
        return null;
    }
    
    /**
     * Eliminar documento
     */
    public boolean eliminar(Long id, Usuarios usuario) {
        Optional<Documento> documentoOpt = documentoRepository.findById(id);
        
        if (documentoOpt.isPresent()) {
            Documento documento = documentoOpt.get();
            
            // Verificar que pertenezca al usuario
            if (documento.getUsuario().getIdUsuario().equals(usuario.getIdUsuario())) {
                documentoRepository.delete(documento);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Verificar si un documento pertenece a un usuario
     */
    public boolean perteneceAlUsuario(Long idDocumento, Usuarios usuario) {
        Documento documento = buscarPorId(idDocumento);
        return documento != null && documento.getUsuario().getIdUsuario().equals(usuario.getIdUsuario());
    }
}
