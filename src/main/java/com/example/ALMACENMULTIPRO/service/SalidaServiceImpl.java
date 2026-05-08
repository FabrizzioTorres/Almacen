package com.example.ALMACENMULTIPRO.service;

import com.example.ALMACENMULTIPRO.model.Producto;
import com.example.ALMACENMULTIPRO.model.Salida;
import com.example.ALMACENMULTIPRO.repository.SalidaRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalidaServiceImpl
        implements SalidaService {

    private final SalidaRepository salidaRepository;
    private final ProductoService productoService;

    public SalidaServiceImpl(
            SalidaRepository salidaRepository,
            ProductoService productoService) {

        this.salidaRepository =
                salidaRepository;

        this.productoService =
                productoService;
    }

    @Override
    public List<Salida> listarSalidas() {

        return salidaRepository.listarSalidas();
    }

    @Override
    public void guardarSalida(Salida salida) {

        if (salida.getDetalles() == null) {

            salida.setDetalles(
                    new ArrayList<>()
            );
        }

        salida.getDetalles().forEach(d -> {

            Producto producto =
                    productoService.buscarProducto(
                            d.getIdProducto()
                    );

            if (producto != null) {

                d.setNombreProducto(
                        producto.getProdNombre()
                );

                if (producto.getStock()
                        < d.getCantidad()) {

                    throw new RuntimeException(
                            "Stock insuficiente"
                    );
                }

                producto.setStock(
                        producto.getStock()
                                - d.getCantidad()
                );

                productoService.actualizarProducto(producto);
            }
        });

        salidaRepository.guardarSalida(
                salida
        );
    }

    @Override
    public Salida buscarSalida(String id) {

        return salidaRepository.buscarSalida(id);
    }

    @Override
    public void eliminarSalida(String id) {

        salidaRepository.eliminarSalida(id);
    }
}