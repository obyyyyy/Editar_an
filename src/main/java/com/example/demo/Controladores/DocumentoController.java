package com.example.demo.Controladores;

import com.example.demo.Entidades.Usuarios;
import com.example.demo.Entidades.Documento;
import com.example.demo.Servicios.UsuarioService;
import com.example.demo.Servicios.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class DocumentoController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private DocumentoService documentoService;
    
    // =====================================================
    // DASHBOARD - LISTA DE DOCUMENTOS
    // =====================================================
    
    @GetMapping("/docs")
    public String dashboard(HttpSession session, Model model) {
        Long uid = (Long) session.getAttribute("uid");
        if (uid == null) {
            return "redirect:/login";
        }
        
        Usuarios usuario = usuarioService.buscarPorId(uid);
        List<Documento> documentos = documentoService.obtenerDocumentosDelUsuario(usuario);
        
        model.addAttribute("usuario", usuario);
        model.addAttribute("documentos", documentos);
        
        return "docs"; // templates/dashboard.html
    }
    
    // =====================================================
    // CREAR NUEVO DOCUMENTO
    // =====================================================
    
    @GetMapping("/docs/nuevo")
    public String nuevoDocumento(HttpSession session, Model model) {
        Long uid = (Long) session.getAttribute("uid");
        if (uid == null) {
            return "redirect:/login";
        }
        
        Usuarios usuario = usuarioService.buscarPorId(uid);
        model.addAttribute("usuario", usuario);
        model.addAttribute("esNuevo", true);
        
        return "editor"; // templates/editor.html
    }
    
    @PostMapping("/docs/crear")
    public String crearDocumento(@RequestParam String titulo,
                                @RequestParam String contenido,
                                HttpSession session,
                                RedirectAttributes flash) {
        
        Long uid = (Long) session.getAttribute("uid");
        if (uid == null) {
            return "redirect:/login";
        }
        
        try {
            Usuarios usuario = usuarioService.buscarPorId(uid);
            documentoService.crear(titulo, contenido, usuario);
            flash.addFlashAttribute("success", "Documento creado exitosamente");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "Error al crear documento");
        }
        
        return "redirect:/docs";
    }
    
    // =====================================================
    // EDITAR DOCUMENTO EXISTENTE
    // =====================================================
    
    @GetMapping("/docs/{id}")
    public String editarDocumento(@PathVariable Long id,
                                 HttpSession session,
                                 Model model,
                                 RedirectAttributes flash) {
        
        Long uid = (Long) session.getAttribute("uid");
        if (uid == null) {
            return "redirect:/login";
        }
        
        Usuarios usuario = usuarioService.buscarPorId(uid);
        Documento documento = documentoService.buscarPorId(id);
        
        if (documento == null || !documentoService.perteneceAlUsuario(id, usuario)) {
            flash.addFlashAttribute("error", "Documento no encontrado");
            return "redirect:/docs";
        }
        
        model.addAttribute("usuario", usuario);
        model.addAttribute("documento", documento);
        model.addAttribute("esNuevo", false);
        
        return "editor"; // templates/editor.html
    }
    
    @PostMapping("/docs/{id}/actualizar")
    public String actualizarDocumento(@PathVariable Long id,
                                     @RequestParam String titulo,
                                     @RequestParam String contenido,
                                     HttpSession session,
                                     RedirectAttributes flash) {
        
        Long uid = (Long) session.getAttribute("uid");
        if (uid == null) {
            return "redirect:/login";
        }
        
        Usuarios usuario = usuarioService.buscarPorId(uid);
        Documento actualizado = documentoService.actualizar(id, titulo, contenido, usuario);
        
        if (actualizado != null) {
            flash.addFlashAttribute("success", "Documento actualizado exitosamente");
        } else {
            flash.addFlashAttribute("error", "Error al actualizar documento");
        }
        
        return "redirect:/docs";
    }
    
    // =====================================================
    // ELIMINAR DOCUMENTO
    // =====================================================
    
    @PostMapping("/docs/{id}/eliminar")
    public String eliminarDocumento(@PathVariable Long id,
                                   HttpSession session,
                                   RedirectAttributes flash) {
        
        Long uid = (Long) session.getAttribute("uid");
        if (uid == null) {
            return "redirect:/login";
        }
        
        Usuarios usuario = usuarioService.buscarPorId(uid);
        boolean eliminado = documentoService.eliminar(id, usuario);
        
        if (eliminado) {
            flash.addFlashAttribute("success", "Documento eliminado exitosamente");
        } else {
            flash.addFlashAttribute("error", "Error al eliminar documento");
        }
        
        return "redirect:/docs";
    }
}