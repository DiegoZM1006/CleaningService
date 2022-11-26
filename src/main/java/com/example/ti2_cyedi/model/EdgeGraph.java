package com.example.ti2_cyedi.model;

public class EdgeGraph<V> {

    private NodeGraph<V> to;
    private int weight;

    public EdgeGraph(NodeGraph<V> to, int weight){
        this.to = to;
        this.weight = weight;
    }

    public NodeGraph<V> getTo() {
        return to;
    }

    public void setTo(NodeGraph<V> to) {
        this.to = to;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
