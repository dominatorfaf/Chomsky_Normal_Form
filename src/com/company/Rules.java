package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Rules {
    static boolean hasEpsilon = false;
    static String epsilonProduction = "";

    //------------------------------------------------------------------
    // 1. Redefine Start point
    //------------------------------------------------------------------

    public static void redefineStartPoint(Map<Grammar, List<Grammar>> CFG){
        boolean toAdd = false;
        List<Grammar> g = new ArrayList<>();
        for (List<Grammar> values : CFG.values()) {
            for (Grammar value : values) {
                if (value.getNodeName().charAt(value.getNodeName().length() - 1) == 'S') {
                    toAdd = true;
                    break;
                }
            }
        }
        if(toAdd){
            Grammar s = new Grammar();
            Grammar t = new Grammar();
            s.setNodeName("S*");
            t.setNodeName("S");
            g.add(t);
            CFG.put(s, g);
        }
    }
    //------------------------------------------------------------------
    // 2. Removing Epsilon
    //------------------------------------------------------------------

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
    // 3. Removing unit production
    //------------------------------------------------------------------

    public static void removeUnitProductions(Map<Grammar, List<Grammar>> CFG){
        String keyToRemove = "";

        for (Grammar key: CFG.keySet()) {
            for (List<Grammar> values : CFG.values()) {
                for (int i = 0; i < values.size(); i++) {
                    if (values.get(i).isSingularUppercase() && key.getNodeName().equals(values.get(i).getNodeName())) {
                        keyToRemove = values.get(i).getNodeName();
                        values.remove(values.get(i));
                    }
                }
            }
        }

        for (Grammar key: CFG.keySet()) {
            for (List<Grammar> values : CFG.values()) {
                for (int i = 0; i < values.size(); i++) {
                    if (key.getNodeName().equals(keyToRemove)){

                        CFG.remove(key);
                    }
                }
            }
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
