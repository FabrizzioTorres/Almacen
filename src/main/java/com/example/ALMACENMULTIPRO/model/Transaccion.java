package com.example.ALMACENMULTIPRO.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaccion {

    private String idTransaccion;
    private String tipo;
    private String responsable;
    private LocalDate fecha;
    private LocalTime hora;

    public Transaccion() {
    }

    public Transaccion(String idTransaccion,
                       String tipo,
                       String responsable,
                       LocalDate fecha,
                       LocalTime hora) {

        this.idTransaccion = idTransaccion;
        this.tipo = tipo;
        this.responsable = responsable;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
}