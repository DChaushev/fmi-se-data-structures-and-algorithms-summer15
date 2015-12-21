/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ww1;

import java.util.Hashtable;
import java.util.LinkedList;

/**
 *
 * @author Krisi
 */
public class Soldier {
    private Integer idSoldier;
    private LinkedList<Unit>units;
    
    

    public Soldier(int idSoldier, Unit unit) {
        this.idSoldier=idSoldier;
        this.units.push(unit);
    }

    public int getIdSoldier() {
        return idSoldier;
    }

    public LinkedList<Unit> getUnits() {
        return units;
    }

    public void addUnits(Unit unit) {
        this.units.add(unit);
    }
    
    
}
