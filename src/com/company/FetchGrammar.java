package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FetchGrammar {

    //Ugly but simplifies main()
    public static void fetchGrammarFromFile(Map<Grammar, List<Grammar>> CFG) throws IOException {
        Map<String, List<String>> cfg = new HashMap<>();
        fillCFGFromFile(cfg);
        hashMapToGrammar(cfg, CFG);
    }

    private static void fillCFGFromFile (Map<String, List<String>> CFG) throws IOException {
        String filePath = "C:\\Users\\domin\\IdeaProjects\\ChomskyNormalForm\\src\\com\\company\\test.txt";

        List<String> nodes = new ArrayList<>();
        List<List<String>> path = new ArrayList<>();

        String line;
        BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath));
        while ((line = reader.readLine()) != null)
        {
            String[] parts = line.split(">", 2);
            if (parts.length >= 2)
            {
                String key = parts[0];
                String value = parts[1];
                if(!nodes.contains(key)) {
                    nodes.add(key);
                    path.add(new ArrayList<>());
                }

                int currentKey = nodes.indexOf(key);
                path.get(currentKey).add(value);

            } else {
                System.out.println("ignoring line: " + line);
            }
        }
        for(int i = 0; i < nodes.size(); i++){
            CFG.put(nodes.get(i), path.get(i));
        }

        reader.close();
//        System.out.println(CFG);
    }

    private static void hashMapToGrammar(Map<String, List<String>> cfg, Map<Grammar, List<Grammar>> CFG){
        List<Grammar> nodes = new ArrayList<>();
        List<List<Grammar>> path = new ArrayList<>();
        for (String symbol:cfg.keySet()
        ) {
            Grammar g = new Grammar();
            g.setNodeName(symbol);
            nodes.add(g);
        }

        for (String key : cfg.keySet()) {
            List<String> value = cfg.get(key);
            List<Grammar> grammarValues = new ArrayList<>();
            for (String symbol: value
            ) {
                Grammar g = new Grammar();
                g.setNodeName(symbol);
                grammarValues.add(g);
            }
            path.add(grammarValues);
        }

        for (int i = 0; i < nodes.size(); i++) {
            CFG.put(nodes.get(i), path.get(i));
        }

    }
}
