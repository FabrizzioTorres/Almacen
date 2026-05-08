package com.example.ALMACENMULTIPRO.service;

import com.example.ALMACENMULTIPRO.model.Ingreso;
import com.example.ALMACENMULTIPRO.model.Salida;
import com.example.ALMACENMULTIPRO.model.Transaccion;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransaccionServiceImpl
        implements TransaccionService {

    private final IngresoService ingresoService;
    private final SalidaService salidaService;

    public TransaccionServiceImpl(
            IngresoService ingresoService,
            SalidaService salidaService) {

        this.ingresoService = ingresoService;
        this.salidaService = salidaService;
    }

    @Override
    public List<Transaccion> listarTransacciones() {

        List<Transaccion> transacciones =
                new ArrayList<>();

        // INGRESOS
        for (Ingreso ingreso :
                ingresoService.listarIngresos()) {

            Transaccion t =
                    new Transaccion();

            t.setIdTransaccion(
                    ingreso.getId_Ingreso()
            );

            t.setTipo("Ingreso");

            t.setResponsable(
                    ingreso.getIngResponsable()
            );

            t.setFecha(
                    ingreso.getIngFecha()
            );

            t.setHora(
                    ingreso.getIngHora()
            );

            transacciones.add(t);
        }

        // SALIDAS
        for (Salida salida :
                salidaService.listarSalidas()) {

            Transaccion t =
                    new Transaccion();

            t.setIdTransaccion(
                    salida.getId_Salida()
            );

            t.setTipo("Salida");

            t.setResponsable(
                    salida.getSalResponsable()
            );

            t.setFecha(
                    salida.getSalFecha()
            );

            t.setHora(
                    salida.getSalHora()
            );

            transacciones.add(t);
        }

        return transacciones;
    }
}