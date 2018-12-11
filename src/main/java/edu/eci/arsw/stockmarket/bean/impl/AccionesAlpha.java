/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.stockmarket.bean.impl;

import edu.eci.arsw.stockmarket.bean.Acciones;
import edu.eci.arsw.stockmarket.model.HttpConnection;
import java.io.IOException;
import org.springframework.stereotype.Service;

/**
 *
 * @author dbeltran
 */
@Service("Alpha")
public class AccionesAlpha implements Acciones{

    @Override
    public String obtenerAcciones(String rango, String nombreAccion) throws IOException {
        return HttpConnection.getUrlData("https://www.alphavantage.co/query?function=" + rango + "&symbol=" + nombreAccion + "&interval=5min&apikey=Q1QZFVJQ21K7C6XM");
    }
}
