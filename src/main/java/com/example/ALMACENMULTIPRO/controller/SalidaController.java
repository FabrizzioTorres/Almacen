package com.example.ALMACENMULTIPRO.controller;

import com.example.ALMACENMULTIPRO.model.Salida;
import com.example.ALMACENMULTIPRO.model.Usuario;
import com.example.ALMACENMULTIPRO.service.ProductoService;
import com.example.ALMACENMULTIPRO.service.SalidaService;
import com.example.ALMACENMULTIPRO.service.UsuarioService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SalidaController {

    private final SalidaService salidaService;
    private final ProductoService productoService;
    private final UsuarioService usuarioService;

    public SalidaController(
            SalidaService salidaService,
            ProductoService productoService,
            UsuarioService usuarioService) {

        this.salidaService = salidaService;
        this.productoService = productoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/Gestionsalidas")
    public String listarSalidas(Model model) {

        model.addAttribute(
                "salidas",
                salidaService.listarSalidas()
        );

        return "Gestionsalidas";
    }

    @GetMapping("/Salida")
    public String nuevaSalida(Model model) {

        model.addAttribute(
                "salida",
                new Salida()
        );

        model.addAttribute(
                "productos",
                productoService.listarProductos()
        );

        model.addAttribute(
                "usuarios",
                usuarioService.listarUsuarios()
        );

        return "Salida";
    }

    @PostMapping("/guardarSalida")
    public String guardarSalida(
            @ModelAttribute Salida salida) {

        salidaService.guardarSalida(
                salida
        );

        return "redirect:/Gestionsalidas";
    }

    @GetMapping("/vermasSalida/{id}")
    public String verMasSalida(
            @PathVariable String id,
            Model model) {

        Salida salida = salidaService.buscarSalida(id);

        Usuario usuario = usuarioService.buscarUsuario(
                salida.getSalResponsable()
        );

        model.addAttribute("salida", salida);

        model.addAttribute(
                "nombreResponsable",
                usuario.getUsuNombre()
        );

        return "VermasSalidas";
    }

    @GetMapping("/eliminarSalida/{id}")
    public String eliminarSalida(
            @PathVariable String id) {

        salidaService.eliminarSalida(id);

        return "redirect:/Gestionsalidas";
    }
    @GetMapping("/vermasSalidaTransaccion/{id}")
    public String verMasSalidaTransaccion(
            @PathVariable String id,
            Model model) {

        Salida salida = salidaService.buscarSalida(id);

        Usuario usuario = usuarioService.buscarUsuario(
                salida.getSalResponsable()
        );

        model.addAttribute("salida", salida);

        model.addAttribute(
                "nombreResponsable",
                usuario.getUsuNombre()
        );

        return "VermasSalidaTransaccion";
    }
}