/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.stockmarket.service;

import edu.eci.arsw.stockmarket.bean.impl.AccionesAlpha;
import edu.eci.arsw.stockmarket.model.Accion;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import edu.eci.arsw.stockmarket.persistence.AccionesRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author dbeltran
 */
@Service
public class AccionesServicesImpl implements AccionesServices {
    
    @Autowired
    @Qualifier("Alpha")
    AccionesAlpha acciones;

    @Autowired
    AccionesRepository repositorio;

    @Override
    public String obtenerAcciones(String rango, String nombreAccion) throws IOException {
        String respuesta;
        Accion accionCache = repositorio.findByNombre(nombreAccion);
        if (accionCache == null) {
            ConcurrentHashMap<String, String> hm = new ConcurrentHashMap<>();
            respuesta = acciones.obtenerAcciones(rango, nombreAccion);
            
            if (respuesta.toLowerCase().contains("\"error message\"")) {
                throw new IOException("Hay dificultades con el API externo.");
            } else if (respuesta.toLowerCase().contains("\"note\"")) {
                throw new IOException("Al parecer esa acción no existe.");
            }
            hm.put(rango, respuesta);
            repositorio.save(new Accion(nombreAccion, hm));
        } else if (accionCache.getTimeSeries().containsKey(rango)) {
            respuesta = accionCache.getTimeSeries().get(rango);
            
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            Date fechaActual = null;
            
            JSONObject jsonObj = new JSONObject(respuesta.toLowerCase());
            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
            String strFecha = jsonObj.getJSONObject("meta data").getString("3. last refreshed");
            Date fechaCache = null;
            
            try {
                fechaCache = formatoDelTexto.parse(strFecha);
                fechaActual = formatoDelTexto.parse(dtf.format(now));
            } catch (ParseException ex) {
                Logger.getLogger(AccionesServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (fechaActual.compareTo(fechaCache) > 0) {
                respuesta = acciones.obtenerAcciones(rango, nombreAccion);
                accionCache.getTimeSeries().put(rango, respuesta);
                repositorio.save(accionCache);
            }
        } else {
            respuesta = acciones.obtenerAcciones(rango, nombreAccion);
            accionCache.getTimeSeries().put(rango, respuesta);
            repositorio.save(accionCache);
        }
        return respuesta;
    }
}
