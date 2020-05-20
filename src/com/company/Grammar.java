package com.company;

public class Grammar {
    private String node;

    public String getNodeName() {
        return node;
    }

    public void setNodeName(String node) {
        this.node = node;
    }

    public boolean isEpsilon(){
        return node.equals("ε");
    }
}
