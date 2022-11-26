package com.example.ti2_cyedi.model;

import java.util.*;

public class Graph<V> implements IGraph<V> {

    public ArrayList<NodeGraph<V>> arrGraph;
    public Integer[][] adjacencyMatrix;
    public NodeGraph<V>[][] matrixNode2;
    public Queue<NodeGraph<V>> queue;
    private int time;
    private String implementation;

    public Graph(String implementation, int row, int column) {
        if (implementation.equalsIgnoreCase("adjacency_list")) {
            this.arrGraph = new ArrayList<>();
            this.implementation = "1";
        } else {
            this.adjacencyMatrix = new Integer[row][column];
            this.matrixNode2 = new NodeGraph[row][1];
            this.implementation = "2";
        }
    }

    public String getImplementation() {
        return implementation;
    }

    @Override
    public boolean addVertex(NodeGraph<V> vertex) {
        if (arrGraph == null && adjacencyMatrix == null) {
            return false;
        }

        if (arrGraph != null) {
            this.arrGraph.add(vertex);
            return true;

        } else {
            if (this.matrixNode2.length == adjacencyMatrix.length - 1) {
                return false;
            } else {
                for (int i = 0; i < matrixNode2.length; i++) {
                    if (matrixNode2[i][0] == null) {
                        matrixNode2[i][0] = vertex;
                        break;
                    }
                }
                return true;
            }
        }
    }

    @Override
    public boolean addEdge(int weight, NodeGraph<V> vertex, NodeGraph<V> vertex2) {
        EdgeGraph<V> edge = new EdgeGraph<V>(vertex2, weight);

        if (arrGraph == null && adjacencyMatrix == null) {
            return false;
        }

        if (arrGraph != null) {
            vertex.addEdge(edge);
            return true;
        } else {
            adjacencyMatrix[vertex.getPosition()][vertex2.getPosition()] = weight;
            vertex.addEdge(edge);
        }

        return false;
    }

    public int availablePosition() {
        for (int i = 0; i < this.matrixNode2.length; i++) {
            if (this.adjacencyMatrix[i][0] == null) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public NodeGraph<V> searchVertex(V value) {
        if (arrGraph != null) {
            for (int i = 0; i < arrGraph.size(); i++) {
                if (arrGraph.get(i).getValue().equals(value)) {
                    return arrGraph.get(i);
                }
            }
        } else if (adjacencyMatrix != null) {
            for (int i = 0; i < matrixNode2.length; i++) {
                if(matrixNode2[i][0] != null) {
                    if (matrixNode2[i][0].getValue().equals(value)) {
                        return matrixNode2[i][0];
                    }
                }
            }
        }
        return null;
    }

    @Override
    public EdgeGraph<V> searchEdge(V value, V value2) {
        if (arrGraph != null) {

            for (int i = 0; i < arrGraph.size(); i++) {
                if (arrGraph.get(i).getValue().equals(value)) {
                    for (int j = 0; j < arrGraph.get(i).getEdgesAdjacencyList().size(); j++) {
                        if (arrGraph.get(i).getEdgesAdjacencyList().get(j).getTo().getValue().equals(value2)) {
                            return arrGraph.get(i).getEdgesAdjacencyList().get(j);
                        }
                    }
                }
            }

        } else if (adjacencyMatrix != null) {
            NodeGraph<V> graphNode = searchVertex(value);
            NodeGraph<V> graphNode2 = searchVertex(value2);

            if (graphNode != null && graphNode2 != null) {
                int position = graphNode.getPosition();
                int position2 = graphNode2.getPosition();

                if(adjacencyMatrix[position][position2] != null) {
                    int weight = adjacencyMatrix[position][position2];
                    return new EdgeGraph<>(graphNode2, weight);
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    public NodeGraph<V> search(V element) {
        for (NodeGraph<V> g : arrGraph) {
            if (g.getValue().equals(element)) {
                return g;
            }
        }
        return null;
    }

    public boolean BFS(V element) {
        NodeGraph<V> nodo = searchVertex(element);
        if(arrGraph != null) {
            for (NodeGraph<V> u : arrGraph) {
                u.setColor("White");
                u.setDistance(0);
                u.setPredecessor(null);
            }
        } else {
            for(int i = 0; i < matrixNode2.length; i++) {
                if (matrixNode2[i][0] != null) {
                    matrixNode2[i][0].setColor("White");
                    matrixNode2[i][0].setDistance(0);
                    matrixNode2[i][0].setPredecessor(null);
                }
            }
        }
        nodo.setColor("Gray");
        nodo.setDistance(0);
        nodo.setPredecessor(null);
        Queue<NodeGraph<V>> queueNodos = new LinkedList<>();
        queueNodos.add(nodo);
        while (!queueNodos.isEmpty()) {
            NodeGraph<V> nodoCola = queueNodos.poll();
            for (EdgeGraph<V> n : nodoCola.getEdgesAdjacencyList()) {
                NodeGraph<V> v = n.getTo();
                if (v.getColor().equalsIgnoreCase("White")) {
                    v.setColor("Gray");
                    v.setDistance(nodoCola.getDistance() + 1);
                    v.setPredecessor(nodoCola);
                    queueNodos.add(v);
                }
            }
            nodoCola.setColor("Black");
        }
        if(arrGraph != null) {
            for (NodeGraph<V> g : arrGraph) {
                if (g.getColor().equalsIgnoreCase("White")) {
                    return false;
                }
            }
        } else {
            for(int z = 0; z < matrixNode2.length; z++){
                if(matrixNode2[z][0] != null) {
                    System.out.println(matrixNode2[z][0].getColor());
                    if (matrixNode2[z][0].getColor().equalsIgnoreCase("White")){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public int DFS() {
        boolean matrixFlag = false;

        if (this.adjacencyMatrix != null) {
            matrixFlag = true;
        }

        for (NodeGraph<V> graphNode : arrGraph) {
            graphNode.setPredecessor(null);
            graphNode.setColor("White");
            graphNode.setTime(0);
        }
        int trees = 0;
        int time = 0;
        for (NodeGraph<V> graphNode : arrGraph) {
            if (graphNode.getColor().equals("White")) {
                trees++;
                DFS(graphNode);
            }
        }
        if (matrixFlag) {
            this.arrGraph = null;
        }
        return trees;
    }

    public void DFS(NodeGraph<V> graphNode) {
        time++;
        graphNode.setTime(time);
        graphNode.setColor("Gray");
        for (EdgeGraph<V> adjacencyEdge : graphNode.getEdgesAdjacencyList()) {
            if (adjacencyEdge.getTo().getColor().equals("White")) {
                adjacencyEdge.getTo().setPredecessor(graphNode);
                DFS(adjacencyEdge.getTo());
            }
        }
        graphNode.setColor("Black");
        time++;
        graphNode.setTime(time);
    }

    public int[][] floydWarshall() {

        int[][] dist = new int[arrGraph.size()][arrGraph.size()];

        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist[0].length; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        for (NodeGraph<V> v : arrGraph) {
            dist[v.getKey()][v.getKey()] = 0;
        }

        for (NodeGraph<V> v : arrGraph) {
            for (EdgeGraph<V> u : v.getEdgesAdjacencyList()) {
                dist[v.getKey()][u.getTo().getKey()] = u.getWeight();
            }
        }

        for (int k = 0; k < arrGraph.size(); k++) {
            for (int i = 0; i < arrGraph.size(); i++) {
                for (int j = 0; j < arrGraph.size(); j++) {
                    if (dist[i][j] > (dist[i][k] + dist[k][j]) && ((dist[i][k]) != Integer.MAX_VALUE && ((dist[k][j]) != Integer.MAX_VALUE))) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        for(int i=0; i<dist.length;i++){
            for (int j=0; j<dist[0].length;j++){
                System.out.print(dist[i][j]+" ");
            }
            System.out.println();
        }

        return dist;
    }

    public String floydWarshall2() {
        int I = Integer.MAX_VALUE;
        int[][] dist;

        if(this.implementation.equals("1")) {
            dist = new int[arrGraph.size()][arrGraph.size()];

            for (NodeGraph<V> v : arrGraph) {
                dist[v.getPosition()][v.getPosition()] = 0;
            }

            for (NodeGraph<V> v : arrGraph) {
                for (EdgeGraph<V> u : v.getEdgesAdjacencyList()) {
                    dist[v.getPosition()][u.getTo().getPosition()] = u.getWeight();
                }
            }
        } else {
            dist = new int[adjacencyMatrix.length][adjacencyMatrix.length];
            for(int i = 0; i < adjacencyMatrix.length; i++) {
                for(int j = 0; j < adjacencyMatrix.length; j++) {
                    if(adjacencyMatrix[i][j] != null) {
                        dist[i][j] = adjacencyMatrix[i][j];
                    } else {
                        dist[i][j] = I;
                    }
                }
            }
        }

        if ( dist == null  ||dist.length == 0) {
            return "";
        }

        int n = dist.length;
        int[][] cost = new int[n][n];
        int[][] path = new int[n][n];

        for (int v = 0; v < n; v++)
        {
            for (int u = 0; u < n; u++)
            {
                cost[v][u] = dist[v][u];

                if (v == u) {
                    path[v][u] = 0;
                }
                else if (cost[v][u] != Integer.MAX_VALUE) {
                    path[v][u] = v;
                }
                else {
                    path[v][u] = -1;
                }
            }
        }

        for (int k = 0; k < n; k++)
        {
            for (int v = 0; v < n; v++)
            {
                for (int u = 0; u < n; u++)
                {
                    if (cost[v][k] != Integer.MAX_VALUE
                            && cost[k][u] != Integer.MAX_VALUE
                            && (cost[v][k] + cost[k][u] < cost[v][u]))
                    {
                        cost[v][u] = cost[v][k] + cost[k][u];
                        path[v][u] = path[k][u];
                    }
                }

                if (cost[v][v] < 0)
                {
                    System.out.println("Negative-weight cycle found!!");
                    return "";
                }
            }
        }

        return printSolution(path, n);
    }

    public void printPath(int[][] path, int v, int u, List<Integer> route)
    {
        if (path[v][u] == v) {
            return;
        }
        printPath(path, v, path[v][u], route);
        route.add(path[v][u]);
    }

    public String printSolution(int[][] path, int n)
    {

        String strToReturn = "";

        for (int v = 0; v < n; v++)
        {
            for (int u = 0; u < n; u++)
            {
                if (u != v && path[v][u] != -1)
                {
                    List<Integer> route = new ArrayList<>();
                    List<String> routeStr = new ArrayList<>();
                    String strV = "";
                    route.add(v);
                    printPath(path, v, u, route);
                    route.add(u);

                    for(Integer i : route) {
                        strV += searchPos(i) + " ";
                        routeStr.add(strV);
                    }

                    String from = searchPos(v);
                    String to = searchPos(u);
                    strToReturn += "The shortest path from " + from +" —> " + to + " is: " + strV + "\n";
                }
            }
        }

        return strToReturn;
    }

    public String searchPos(int i) {

        String str = "";

        if(this.implementation.equals("1")) {
            for(NodeGraph<V> v : arrGraph) {
                if(v.getPosition() == i) {
                    str = (String) v.getValue();
                }
            }
        } else {
            for(int in = 0; in < matrixNode2.length; in++) {
                if(matrixNode2[in][0] != null) {
                    if(matrixNode2[in][0].getPosition() == i) {
                        str = (String) matrixNode2[in][0].getValue();
                    }
                }
            }
        }

        return str;

    }

    public boolean deleteGraph() {

        if(arrGraph != null) {
            if (!arrGraph.isEmpty()) {
                arrGraph.clear();
                return true;
            }
        }

        if (adjacencyMatrix != null) {
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                for (int j = 0; j < adjacencyMatrix.length; j++) {
                    adjacencyMatrix[i][j] = 0;
                }
            }
            for (int i = 0; i < matrixNode2.length; i++) {
                if (matrixNode2[i][0] != null) {
                    matrixNode2[i][0] = null;
                }
            }
            return true;
        }
        return false;
    }

    public ArrayList<NodeGraph<V>> getArrGraph() {
        return arrGraph;
    }

    public void setArrGraph(ArrayList<NodeGraph<V>> arrGraph) {
        this.arrGraph = arrGraph;
    }

    public Integer[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public void setAdjacencyMatrix(Integer[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public V getMatrixNode2() {
        if (matrixNode2[0][0] != null) {
            return matrixNode2[0][0].getValue();
        } else {
            return null;
        }
    }

}
