package com.company;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        Map<Grammar, List<Grammar>> hashMapGrammar = new HashMap<>();
        FetchGrammar.fetchGrammarFromFile(hashMapGrammar);

        //1 --------Epsilon-Removal--------

        Rules.printGrammar(hashMapGrammar);
        Rules.removeEpsilon(hashMapGrammar);
        Rules.printGrammar(hashMapGrammar);

    }
}
