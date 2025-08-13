package com.example.demo.Entidades;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuarios {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;
    
    @Column(name = "nombre_usuario", nullable = false, length = 50)
    private String nombreUsuario;
    
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    
    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Documento> documentos;
    
    // =====================================================
    // CONSTRUCTORES
    // =====================================================
    
    public Usuarios() {
        this.fechaRegistro = LocalDateTime.now();
    }
    
    public Usuarios(String nombreUsuario, String password) {
        this();
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }
    
    public Usuarios(Long idUsuario, String nombreUsuario, String password, LocalDateTime fechaRegistro, List<Documento> documentos) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.fechaRegistro = fechaRegistro;
        this.documentos = documentos;
    }
    
    // =====================================================
    // GETTERS Y SETTERS
    // =====================================================
    
    public Long getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }
    
    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    public List<Documento> getDocumentos() {
        return documentos;
    }
    
    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }
    
    // =====================================================
    // toString, equals, hashCode
    // =====================================================
    
    @Override
    public String toString() {
        return "Usuarios{" +
                "idUsuario=" + idUsuario +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuarios usuarios = (Usuarios) o;
        return idUsuario != null && idUsuario.equals(usuarios.idUsuario);
    }
    
    @Override
    public int hashCode() {
        return idUsuario != null ? idUsuario.hashCode() : 0;
    }
}
