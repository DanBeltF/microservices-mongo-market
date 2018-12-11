/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.stockmarket.model;

import java.util.concurrent.ConcurrentHashMap;
import org.springframework.data.annotation.Id;

/**
 *
 * @author dbeltran
 */
public class Accion {
    @Id
    public String id;

    private String nombre;
    private ConcurrentHashMap<String, String> timeSeries;

    public Accion(String nombre, ConcurrentHashMap<String, String> timeSeries) {
        this.nombre = nombre;
        this.timeSeries = timeSeries;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ConcurrentHashMap<String, String> getTimeSeries() {
        return timeSeries;
    }

    public void setTimeSeries(ConcurrentHashMap<String, String> timeSeries) {
        this.timeSeries = timeSeries;
    }
}
