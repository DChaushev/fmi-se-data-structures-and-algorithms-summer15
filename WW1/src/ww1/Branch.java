/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ww1;

import java.util.LinkedList;
import java.util.Vector;

/**
 *
 * @author Krisi
 */
 class Branch extends Unit<Unit,Unit>{

    public Branch(String name) {
        super(name);
    }

    @Override
    public void attach(Unit unit) {
        children.add(unit);
    }
    
    @Override
    public int countSoldiers() {
        int size=0;
        for (int i = 0; i < children.size(); i++) {
            size+=children.get(i).countSoldiers();
        }
        return size;
    }
    
    @Override
    public LinkedList<Integer> removeDeathSoldiers(LinkedList<Integer> deathSoldiers) {
        for (int i = 0; i < children.size(); i++) {
            deathSoldiers=children.get(i).removeDeathSoldiers(deathSoldiers);
        }
        return deathSoldiers;
    }
    
    @Override
    public LinkedList<Integer> getListOfSoldiers() {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < children.size(); i++) {
            list.addAll(children.get(i).getListOfSoldiers());
        }
        return list;
    }
    
    public int idLastSoldier(){
        return children.peekLast().idLastSoldier();
    }

    @Override
    public void attach(Unit item, int afterSoldier) {
        for (int i = 0; i < children.size(); i++) {
            if(children.get(i).idLastSoldier()==afterSoldier){
                children.add(i+1, item);
                break;
            }
        }
    }
    
 }
