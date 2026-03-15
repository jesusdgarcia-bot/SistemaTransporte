/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematransporte.dao;

import sistematransporte.model.Conductor;
/**
 *
 * @author almen
 */
public class ConductorDao {
    
    public Conductor buscar(String idLicConductor){
        return new Conductor();
    }
    
    public boolean verificarIdLicConductor(String idLicConductor){
        return buscar(idLicConductor) == null;
    }
}
