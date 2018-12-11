/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.stockmarket.persistence;

import edu.eci.arsw.stockmarket.model.Accion;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author dbeltran
 */
public interface AccionesRepository extends MongoRepository<Accion, String> {

    public Accion findByNombre(String nombre);
}
