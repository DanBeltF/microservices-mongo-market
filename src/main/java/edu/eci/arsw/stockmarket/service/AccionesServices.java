/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.stockmarket.service;

import java.io.IOException;

/**
 *
 * @author dbeltran
 */
public interface AccionesServices {
    public String obtenerAcciones(String rango, String nombreAccion) throws IOException;
}
