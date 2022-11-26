package com.example.ti2_cyedi.model;

import java.util.ArrayList;

public class NodeGraph<V> {

    private V value;
    private ArrayList<EdgeGraph<V>> edgesAdjacencyList;
    private int position;

    public String color;
    public int distance;
    public NodeGraph<V> predecessor;
    int time;
    private int key;
    private int id;

    public NodeGraph(V value, int position){
        this.value = value;
        this.position = position;
        edgesAdjacencyList = new ArrayList<>();

        this.color = null;
        this.distance = 0;
        this.predecessor = null;
    }

    public NodeGraph(V value){
        this.value = value;
        edgesAdjacencyList = new ArrayList<>();

        this.color = null;
        this.distance = 0;
        this.predecessor = null;
    }

    public boolean hasMember(EdgeGraph<V> id) {
        return this.edgesAdjacencyList.contains(id);
    }

    public void addEdge(EdgeGraph<V> edge) {
        edgesAdjacencyList.add(edge);
    }
    public int getPosition(){
        return this.position;
    }

    public void setPosition(int position){
        this.position = position;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public ArrayList<EdgeGraph<V>> getEdgesAdjacencyList() {
        return edgesAdjacencyList;
    }

    public void setEdgesAdjacencyList(ArrayList<EdgeGraph<V>> edgesAdjacencyList) {
        this.edgesAdjacencyList = edgesAdjacencyList;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public NodeGraph<V> getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(NodeGraph<V> predecessor) {
        this.predecessor = predecessor;
    }

    public int getTime(){
        return this.time;
    }

    public void setTime(int time){
        this.time = time;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

}
