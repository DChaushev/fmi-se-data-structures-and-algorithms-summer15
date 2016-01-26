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
public class SoldiersData {
    
    private static Hashtable<Integer,Soldier> soldiers;
    public static void add(int key,Soldier value){
        SoldiersData.soldiers.put(key, value);
    }
    
    public static Soldier getSoldier(int id) {
        return soldiers.get(id);
    }
}
