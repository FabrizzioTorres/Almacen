package com.example.ALMACENMULTIPRO.controller;

import com.example.ALMACENMULTIPRO.service.TransaccionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransaccionController {

    private final TransaccionService transaccionService;

    public TransaccionController(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    @GetMapping("/Transacciones")
    public String verTransacciones(Model model) {

        model.addAttribute(
                "transacciones",
                transaccionService.listarTransacciones()
        );

        return "Transacciones";
    }
}