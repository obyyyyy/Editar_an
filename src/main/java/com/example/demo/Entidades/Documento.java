package com.example.demo.Entidades;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "documentos")
public class Documento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento")
    private Long idDocumento;
    
    @Column(name = "titulo", nullable = false, length = 255)
    private String titulo;
    
    @Lob
    @Column(name = "contenido", columnDefinition = "TEXT")
    private String contenido;
    
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuarios usuario;
    
    // =====================================================
    // CONSTRUCTORES
    // =====================================================
    
    public Documento() {
        this.fechaCreacion = LocalDateTime.now();
    }
    
    public Documento(String titulo, String contenido) {
        this();
        this.titulo = titulo;
        this.contenido = contenido;
    }
    
    public Documento(String titulo, String contenido, Usuarios usuario) {
        this();
        this.titulo = titulo;
        this.contenido = contenido;
        this.usuario = usuario;
    }
    
    public Documento(Long idDocumento, String titulo, String contenido, LocalDateTime fechaCreacion, Usuarios usuario) {
        this.idDocumento = idDocumento;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaCreacion = fechaCreacion;
        this.usuario = usuario;
    }
    
    // =====================================================
    // GETTERS Y SETTERS
    // =====================================================
    
    public Long getIdDocumento() {
        return idDocumento;
    }
    
    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getContenido() {
        return contenido;
    }
    
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    public Usuarios getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
    
    // =====================================================
    // toString, equals, hashCode
    // =====================================================
    
    @Override
    public String toString() {
        return "Documento{" +
                "idDocumento=" + idDocumento +
                ", titulo='" + titulo + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", usuario=" + (usuario != null ? usuario.getNombreUsuario() : "null") +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Documento documento = (Documento) o;
        return idDocumento != null && idDocumento.equals(documento.idDocumento);
    }
    
    @Override
    public int hashCode() {
        return idDocumento != null ? idDocumento.hashCode() : 0;
    }
}