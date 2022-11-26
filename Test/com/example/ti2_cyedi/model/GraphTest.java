package com.example.ti2_cyedi.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    public Graph graph;

    public void setUp1(){
        graph = new Graph("adjacency_list", 2,2 );
        NodeGraph nodo = new NodeGraph(19,8);
        graph.addVertex(nodo);
    }

    public void setUp2(){
        graph = new Graph("adjacency_list", 2,2 );
    }

    public void setUp3(){
        graph = new Graph("adjacency_list", 2,2 );
        NodeGraph nodo = new NodeGraph(19,8);
        graph.addVertex(nodo);
        NodeGraph nodo2 = new NodeGraph(3,1);
        graph.addVertex(nodo2);
        NodeGraph nodo3 = new NodeGraph(1,9);
        graph.addVertex(nodo3);
        NodeGraph nodo4 = new NodeGraph(25,2);
        graph.addVertex(nodo4);
        NodeGraph nodo5 = new NodeGraph(6,10);
        graph.addVertex(nodo5);

        graph.addEdge(1,nodo4,nodo5);
    }

    public void setUp4(){
        graph = new Graph("adjacency_list", 2,2 );
        NodeGraph nodo = new NodeGraph(1,1);
        graph.addVertex(nodo);
        NodeGraph nodo2 = new NodeGraph(2,2);
        graph.addVertex(nodo2);
        NodeGraph nodo3 = new NodeGraph(3,3);
        graph.addVertex(nodo3);

        graph.addEdge(1,nodo, nodo2);
        graph.addEdge(2,nodo2, nodo3);
        graph.addEdge(3,nodo3, nodo);
    }

    @Test
    public void addVertexTest1(){
        setUp1();
        NodeGraph nodo1 = new NodeGraph(100000,2);
        graph.addVertex(nodo1);
        assertEquals(graph.searchVertex(100000),nodo1);
    }

    @Test
    public void addVertexTest2(){
        setUp2();
        NodeGraph nodo1 = new NodeGraph(2,2);
        graph.addVertex(nodo1);
        assertEquals(graph.searchVertex(2),nodo1);
    }

    @Test
    public void  addVertexTest3(){
        setUp2();
        NodeGraph nodo3 = new NodeGraph(5,1);
        graph.addVertex(nodo3);
        assertEquals(graph.searchVertex(5),nodo3);
    }

    @Test
    public void addEdgeTest1(){
        setUp3();
        assertNull(graph.searchEdge(1,2));
    }

    @Test
    public void addEdgeTest2(){
        setUp3();
        graph.addEdge(2,graph.searchVertex(1),graph.searchVertex(6));
        assertEquals(graph.searchEdge(1,6).getTo(), graph.searchVertex(6));
    }

    @Test
    public void addEdgeTest3(){
        setUp3();
        graph.addEdge(2,graph.searchVertex(19),graph.searchVertex(25));
        assertEquals(graph.searchEdge(19,25).getTo(), graph.searchVertex(25));
    }

    @Test
    public void searchVertexTest1(){
        setUp3();
        assertNull(graph.searchVertex(99));
    }

    @Test
    public void searchVertexTest2(){
        setUp2();
        NodeGraph nodoB = new NodeGraph(1,1);
        graph.addVertex(nodoB);
        assertEquals(graph.searchVertex(1), nodoB);
    }

    @Test
    public void searchVertexTest3(){
        setUp3();
        NodeGraph nodoB = new NodeGraph(87,100);
        graph.addVertex(nodoB);
        assertEquals(graph.searchVertex(87), nodoB);
    }

    @Test
    public void searchEdgeTest1(){
        setUp2();
        assertNull(graph.searchEdge(2,1));
    }

    @Test
    public void searchEdgeTest2(){
        setUp3();
        graph.addEdge(2,graph.searchVertex(1),graph.searchVertex(25));
        assertEquals(graph.searchEdge(1,25).getTo(), graph.searchVertex(25));
    }

    @Test
    public void searchEdgeTest3(){
        setUp3();
        graph.addEdge(2,graph.searchVertex(1),graph.searchVertex(19));
        assertNull(graph.searchEdge(1,9999));
    }

    @Test
    public void bfsTest1(){
        setUp4();
        assertTrue(graph.BFS(1));
    }

    @Test
    public void bfsTest2(){
        setUp1();
        assertTrue(graph.BFS(19));
    }

    @Test
    public void bfsTest3(){
        setUp3();
        assertFalse(graph.BFS(6));
    }

    @Test
    public void floydWarshallTest1(){
        setUp4();
        assertEquals(graph.floydWarshall().length,3);
    }

    @Test
    public void floydWarshallTest2(){
        setUp1();
        assertEquals(graph.floydWarshall().length, 1);
    }

    @Test
    public void floydWarshallTest3(){
        setUp3();
        assertEquals(graph.floydWarshall().length, 5);
    }

    @Test
    public void deleteGraphTest1(){
        setUp3();
        assertTrue(graph.deleteGraph());
    }

    @Test
    public void deleteGraphTest2(){
        setUp2();
        assertFalse(graph.deleteGraph());
    }

    @Test
    public void deleteGraphTest3(){
        setUp1();
        assertTrue(graph.deleteGraph());
    }

    @Test
    public void dfsTest1(){
        setUp4();
        assertEquals(graph.DFS(),1);
    }

    @Test
    public void dfsTest2(){
        setUp1();
        assertEquals(graph.DFS(),1);
    }

    @Test
    public void dfsTest3(){
        setUp2();
        assertEquals(graph.DFS(),0);
    }


}