package com.example.demo.Controladores;

import com.example.demo.Entidades.Usuarios;
import com.example.demo.Repositorios.UsuarioRepository;
import com.example.demo.Servicios.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;      // para login

    @Autowired
    private UsuarioRepository usuarioRepo;      // para registro

    @GetMapping("/login")
    public String loginForm() {
        return "login"; // templates/login.html
    }

    @PostMapping("/login")
    public String login(@RequestParam("nombreUsuario") String nombreUsuario,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model) {

        Usuarios u = usuarioService.autenticar(nombreUsuario, password);
        if (u == null) {
            model.addAttribute("error", "Usuario o contraseña inválidos");
            return "login";
        }

        session.setAttribute("uid", u.getIdUsuario());
        session.setAttribute("uname", u.getNombreUsuario());
        return "redirect:/docs"; // ajusta si tu landing es otra
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register"; // templates/register.html
    }

    @PostMapping("/register")
    public String register(@RequestParam("nombreUsuario") String nombreUsuario,
                           @RequestParam("password") String password,
                           Model model) {

        if (usuarioRepo.existsByNombreUsuario(nombreUsuario)) {
            model.addAttribute("error", "El usuario ya existe");
            return "register";
        }

        Usuarios u = new Usuarios();
        u.setNombreUsuario(nombreUsuario);
        u.setPassword(password); // texto plano (solo pruebas)
        usuarioRepo.save(u);

        return "redirect:/login?ok";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?out";
    }
}
