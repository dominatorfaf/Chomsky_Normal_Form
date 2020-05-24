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

    public boolean isSingular(){
        return node.length() == 1;
    }

    public boolean isSingularUppercase(){
        if(isSingular()){
            return Character.isUpperCase(node.charAt(0));
        }
        return false;
    }
}
