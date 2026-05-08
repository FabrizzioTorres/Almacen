package com.example.ALMACENMULTIPRO.controller;

import com.example.ALMACENMULTIPRO.model.Ingreso;
import com.example.ALMACENMULTIPRO.service.IngresoService;
import com.example.ALMACENMULTIPRO.service.ProductoService;
import com.example.ALMACENMULTIPRO.service.UsuarioService;
import com.example.ALMACENMULTIPRO.model.Usuario;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IngresoController {

    private final IngresoService ingresoService;
    private final ProductoService productoService;
    private final UsuarioService usuarioService;

    public IngresoController(
            IngresoService ingresoService,
            ProductoService productoService,
            UsuarioService usuarioService) {

        this.ingresoService = ingresoService;
        this.productoService = productoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/GestionIngreso")
    public String listarIngresos(Model model) {

        model.addAttribute(
                "ingresos",
                ingresoService.listarIngresos()
        );

        return "GestionIngreso";
    }

    @GetMapping("/ingresos")
    public String nuevoIngreso(Model model) {

        model.addAttribute(
                "ingreso",
                new Ingreso()
        );

        model.addAttribute(
                "productos",
                productoService.listarProductos()
        );

        model.addAttribute(
                "usuarios",
                usuarioService.listarUsuarios()
        );

        return "ingresos";
    }

    @PostMapping("/guardarIngreso")
    public String guardarIngreso(
            @ModelAttribute Ingreso ingreso) {

        ingresoService.guardarIngreso(
                ingreso
        );

        return "redirect:/GestionIngreso";
    }

    @GetMapping("/vermasIngreso/{id}")
    public String verMasIngreso(
            @PathVariable String id,
            Model model) {

        Ingreso ingreso = ingresoService.buscarIngreso(id);

        Usuario usuario = usuarioService.buscarUsuario(
                ingreso.getIngResponsable()
        );

        model.addAttribute("ingreso", ingreso);

        model.addAttribute(
                "nombreResponsable",
                usuario.getUsuNombre()
        );

        return "VermasIngreso";
    }
    @GetMapping("/eliminarIngreso/{id}")
    public String eliminarIngreso(
            @PathVariable String id) {

        ingresoService.eliminarIngreso(id);

        return "redirect:/GestionIngreso";
    }
    @GetMapping("/vermasIngresoTransaccion/{id}")
    public String verMasIngresoTransaccion(
            @PathVariable String id,
            Model model) {

        Ingreso ingreso = ingresoService.buscarIngreso(id);

        Usuario usuario = usuarioService.buscarUsuario(
                ingreso.getIngResponsable()
        );

        model.addAttribute("ingreso", ingreso);

        model.addAttribute(
                "nombreResponsable",
                usuario.getUsuNombre()
        );

        return "VermasIngresoTransaccion";
    }
}