package com.example.ALMACENMULTIPRO.controller;

import com.example.ALMACENMULTIPRO.model.Producto;
import com.example.ALMACENMULTIPRO.service.CategoriaService;
import com.example.ALMACENMULTIPRO.service.ProductoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public ProductoController(
            ProductoService productoService,
            CategoriaService categoriaService) {

        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/Gestion")
    public String listarProductos(Model model) {

        model.addAttribute(
                "productos",
                productoService.listarProductos()
        );

        return "Gestion";
    }

    @GetMapping("/Producto")
    public String mostrarFormularioProducto(
            Model model) {

        model.addAttribute(
                "producto",
                new Producto()
        );

        model.addAttribute(
                "categorias",
                categoriaService.listarCategorias()
        );

        return "Producto";
    }

    @PostMapping("/guardarProducto")
    public String guardarProducto(
            @ModelAttribute Producto producto) {

        productoService.guardarProducto(producto);

        return "redirect:/Gestion";
    }

    @GetMapping("/editarProducto/{id}")
    public String editarProducto(
            @PathVariable String id,
            Model model) {

        model.addAttribute(
                "producto",
                productoService.buscarProducto(id)
        );

        model.addAttribute(
                "categorias",
                categoriaService.listarCategorias()
        );

        return "EditarProducto";
    }

    @GetMapping("/eliminarProducto/{id}")
    public String eliminarProducto(
            @PathVariable String id) {

        productoService.eliminarProducto(id);

        return "redirect:/Gestion";
    }

	@PostMapping("/actualizarProducto")
    	public String actualizarProducto(
            @ModelAttribute Producto producto) {

        productoService.actualizarProducto(producto);

        return "redirect:/Gestion";
    }	
}