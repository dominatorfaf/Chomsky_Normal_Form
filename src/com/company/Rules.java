package com.company;

import javafx.css.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Rules {
    static boolean hasEpsilon = false;
    static String epsilonProduction = "";

    //1. removing epsilon
    public static void removeEpsilon(Map<Grammar, List<Grammar>> CFG) {
        epsilonProduction = "";
        hasEpsilon = false;
        System.out.println(epsilonProduction);

        for (Grammar key: CFG.keySet()) {
            for (List<Grammar> values : CFG.values()) {
                for(int i = 0; i < values.size(); i++) {
                    if(values.get(i).getNodeName().equals("ε")){
                        hasEpsilon = true;
                        values.remove(values.get(i));
                        epsilonProduction = key.getNodeName();
                        break;
                    }
                }
            }
        }
        if (hasEpsilon) {
            epsilonRemoval(epsilonProduction, CFG);
        } else {
            System.out.println("No Epsilons - Done!");
        }
    }

    //------------------------------------------------------------------
    // AIDING FUNCTIONS
    //------------------------------------------------------------------

    private static void epsilonRemoval(String epsProduction, Map<Grammar, List<Grammar>> CFG){
        int instanceOfEpsProduction = 0;
        for (List<Grammar> values : CFG.values()) {
            for (Grammar value: values) {
                if(value.getNodeName().contains(epsProduction)){
                    instanceOfEpsProduction++;
                }
            }
        }
        while(0 < instanceOfEpsProduction) {
            for (List<Grammar> values : CFG.values()) {
                for (int i = 0; i < values.size(); i++) {
                    if (values.get(i).getNodeName().contains(epsProduction)) {
                        Grammar t = new Grammar();
                        t.setNodeName(values.get(i).getNodeName());
                        t.setNodeName(t.getNodeName().replace(epsProduction, ""));
//                        if(t.getNodeName().equals("")){ // causes STACK OVERFLOW
//                            t.setNodeName("ε");
//                        }
                        values.add(t);
                        instanceOfEpsProduction--;
                    }
                }
            }
        }
        removeEpsilon(CFG); // Coming back to the initial function

//        Rules.printGrammar(CFG);
    }

    public static void printGrammar(Map<Grammar, List<Grammar>> CFG) {
        for (Grammar key : CFG.keySet()) {
            List<Grammar> value = CFG.get(key);
            System.out.print("\nKey = " + key.getNodeName() + " : ");
            for (Grammar g: value
            ) {
                if(!g.getNodeName().equals("")) {
                    System.out.print(g.getNodeName() + ", ");
                }
            }
        }
    }

}
