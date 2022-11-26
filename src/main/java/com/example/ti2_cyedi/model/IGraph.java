package com.example.ti2_cyedi.model;

public interface IGraph<V> {
    public boolean addVertex(NodeGraph<V> vertex);
    public boolean addEdge(int weight, NodeGraph<V> vertex, NodeGraph<V> vertex2);
    public NodeGraph<V> searchVertex(V value);
    public EdgeGraph<V> searchEdge(V value, V value2);
}
