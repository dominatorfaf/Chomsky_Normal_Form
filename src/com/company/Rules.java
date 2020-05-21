package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Rules {
    static boolean hasEpsilon = false;

    //1. removing epsilon
    public static void removeEpsilon(Map<Grammar, List<Grammar>> CFG) {
        String epsilonProduction = "";
        hasEpsilon = false;
        for (Grammar key: CFG.keySet()) {
            for (List<Grammar> values : CFG.values()) {
                for (Grammar value: values
                ) {
                    if(value.getNodeName().equals("ε")){
                        hasEpsilon = true;
                        value.setNodeName("");
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
            Grammar t;
            for (Grammar key : CFG.keySet()) {
                for (List<Grammar> values : CFG.values()) {
                    for (int i = 0; i < values.size(); i++) {
                        if (values.get(i).getNodeName().contains(epsProduction)) {
                            t = values.get(i);
                            t.setNodeName(values.get(i).getNodeName().replace(epsProduction, ""));
                            values.add(t);
                        }
                    }
                }
            }
        }
        removeEpsilon(CFG); // Coming back to the initial function
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
