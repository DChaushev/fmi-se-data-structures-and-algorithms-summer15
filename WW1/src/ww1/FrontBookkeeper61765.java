/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ww1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

/**
 *
 * @author Krisi
 */
public class FrontBookkeeper61765 implements IFrontBookkeeper {

    static HashMap<String, Unit> army;
    static HashMap<Integer, Vector<Unit>> soldiers;

    @Override
    public String updateFront(String[] news) {

        String assignmentPattern = "\\w+\\s*=\\s*\\[((\\d+,\\s*)*\\d+){0,1}\\]";
        String attachmentPattern = "\\w+\\s+attached\\s+to\\s+\\w+";
        String positionalAttachmentPattern = "\\w+\\s+attached\\s+to\\s+\\w+\\s+after\\s+soldier\\s+\\d+";
        String deathPattern = "soldiers\\s+\\d+\\.\\.\\d+\\s+from\\s+\\w+\\s+died\\sheroically";
        String unitDisplayPattern = "show\\s+\\w+";
        String soldierDisplayPattern = "show\\s+soldier\\s+\\d+";

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < news.length; i++) {

            if (news[i].matches(assignmentPattern)) {
                String[] info = news[i].split("(\\s*=\\s*\\[)|(,\\s*)|\\]");
                if (info.length == 1) {
                    UnitsData.add(info[0], new Branch(info[0]));
                } else {
                    UnitsData.add(info[0], new Leaf(info[0]));
                    LinkedList<Integer> idSoldiers = new LinkedList<>();
                    for (int j = 1; j < info.length; j++) {
                        idSoldiers.push(Integer.parseInt(info[j]));
                        SoldiersData.add(idSoldiers.peekLast(), new Soldier(idSoldiers.peekLast(), UnitsData.getUnit(info[0])));
                    }
                    UnitsData.getUnit(info[0]).attach(idSoldiers);
                }

            }

            if (news[i].matches(attachmentPattern)) {
                String[] info = news[i].split("\\s+attached\\s+to\\s+", 2);
                if (!UnitsData.getUnit(info[0]).isPartOfUnit()) {
                    UnitsData.getUnit(info[0]).isBeingAttached();
                    UnitsData.getUnit(info[1]).attach(UnitsData.getUnit(info[0]));
                }
            }

            if (news[i].matches(positionalAttachmentPattern)) {
                String[] info = news[i].split("(\\s+attached\\s+to\\s+)|(\\s+after\\s+soldier\\s+)", 2);
                if (!UnitsData.getUnit(info[0]).isPartOfUnit()) {
                    UnitsData.getUnit(info[0]).isBeingAttached();
                    UnitsData.getUnit(info[1]).attach(UnitsData.getUnit(info[0]),Integer.parseInt(info[2]));
                }
            }

            if (news[i].matches(deathPattern)) {
                String[] info = news[i].split("(soldiers\\s+)|(\\.\\.)|(\\s+from\\s+)|(\\s+died\\sheroically)");
                LinkedList<Integer> deathSoldiers=new LinkedList<>();
                for (int j =Integer.parseInt(info[0]); j <= Integer.parseInt(info[1]); j++) {
                    deathSoldiers.push(j);
                }
                UnitsData.getUnit(info[2]).removeDeathSoldiers(deathSoldiers);
            }

            if (news[i].matches(unitDisplayPattern)) {
                String[] info=news[i].split("show\\s+",1);
                builder.append(UnitsData.getUnit(info[0]).showUnit()).append("\n");
            }

            if (news[i].matches(soldierDisplayPattern)) {
                String[] info=news[i].split("show\\s+soldier\\s+",1);
                builder.append(SoldiersData.getSoldier(Integer.parseInt(info[0])).getUnits().toString()).append("\n");
            }
        }
        return builder.toString();
    }
}
