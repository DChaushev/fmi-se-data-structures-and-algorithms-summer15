/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ww1;

import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author Krisi
 */
class Leaf extends Unit<Integer, LinkedList<Integer>> {

    public Leaf(String name) {
        super(name);
    }


    
    @Override
    public int countSoldiers() {
        return children.size();
    }

    @Override
    public LinkedList<Integer> removeDeathSoldiers(LinkedList<Integer> deathSoldiers) {
        if (children.peekLast().intValue() < deathSoldiers.peekFirst().intValue()
                || children.peekFirst().intValue() > deathSoldiers.peekLast().intValue()) {
            return deathSoldiers;
        }
        for (int i = 0; i < children.size(); i++) {
            if (children.contains(deathSoldiers.get(i))) {
                children.remove(deathSoldiers.get(i));
                deathSoldiers.remove(i);
                i--;
            }
        }
        return deathSoldiers;
    }

    @Override
    public LinkedList<Integer> getListOfSoldiers() {
        return children;
    }

    @Override
    public int idLastSoldier() {
        return (int) children.peekLast();
    }
    @Override
    public void attach(LinkedList<Integer> soldiers) {
        this.children.addAll(soldiers);
        Collections.sort(children);
        isLeaf = true;
    }

}
