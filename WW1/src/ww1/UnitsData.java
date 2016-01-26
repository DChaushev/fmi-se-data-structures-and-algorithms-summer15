/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ww1;

import java.util.Hashtable;

/**
 *
 * @author Krisi
 */
public class UnitsData {
    private static Hashtable<String,Unit> units;
    public static void add(String key,Unit value){
        UnitsData.units.put(key, value);
    }
    
    public static Unit getUnit(String name) {
        return units.get(name);
    }
}
