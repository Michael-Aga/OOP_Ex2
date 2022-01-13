package tests;

import classes.Edge_Data;
import classes.Geo_Location;
import classes.Node_Data;
import classes.Directed_Weighted_Graph;
import api.EdgeData;
import api.NodeData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class DWGTest {

    private static Node_Data n1, n2, n3, n4, n5, n6;

    /**
     * check that the address are different
     * check that hashmaps are different address
     */

    @Test
    @Timeout(value = 2, unit = TimeUnit.MILLISECONDS)
    void getNode() {
        Directed_Weighted_Graph graph = new Directed_Weighted_Graph();

        n1 = new Node_Data(1, "1,2,3");
        graph.addNode(n1);
        assertEquals(n1, graph.getNode(1));
        assertEquals(1, graph.nodeSize());
    }

    @Test
    void getEdge() {
        Directed_Weighted_Graph graph = new Directed_Weighted_Graph();

        n1 = new Node_Data(1, "1,2,3");
        n2 = new Node_Data(2, "2,1,3");
        n3 = new Node_Data(3, "-4,7,1");
        n4 = new Node_Data(4, "-5, 8, 2");

        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);

        graph.connect(n1.getKey(), n3.getKey(), 1.1);
        graph.connect(n2.getKey(), n4.getKey(), 1.2);

        assertEquals(new Edge_Data(1, 3, 1.1), graph.getEdge(1, 3));
        assertEquals(new Edge_Data(2, 4, 1.2), graph.getEdge(2, 4));

    }

    @Test
    void connect() {
        Directed_Weighted_Graph graph = new Directed_Weighted_Graph();

        n1 = new Node_Data(1, "1,2,3");
        n2 = new Node_Data(2, "2,1,3");

        graph.addNode(n1);
        graph.addNode(n2);
        graph.connect(1, 2, 1.0);
        assertEquals(1, graph.edgeSize());
        assertEquals(new Edge_Data(1, 2, 1.0), graph.getEdge(1, 2));
    }

    @Test
    void nodeIter() {
        Directed_Weighted_Graph graph = new Directed_Weighted_Graph();

        n1 = new Node_Data(1, "1,2,3");
        n2 = new Node_Data(2, "2,1,3");

        graph.addNode(n1);
        graph.addNode(n2);

        Iterator<NodeData> iterator = graph.nodeIter();
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            iterator.next();
        }
        assertEquals(count, 2);
    }

    @Test
    void edgeIter() {

        Directed_Weighted_Graph graph = new Directed_Weighted_Graph();

        n1 = new Node_Data(1, "1,2,3");
        n2 = new Node_Data(2, "2,1,3");
        n3 = new Node_Data(3, "-4,7,1");
        n4 = new Node_Data(4, "-5,8,2");

        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);

        graph.connect(n1.getKey(), n3.getKey(), 1.1);
        graph.connect(n2.getKey(), n4.getKey(), 1.2);

        Iterator<EdgeData> iterator = graph.edgeIter();
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            iterator.next();
        }
        assertEquals(count, 2);
    }

    @Test
    void testEdgeIterNodeID() {

        Directed_Weighted_Graph graph = new Directed_Weighted_Graph();

        n1 = new Node_Data(1, "1,2,3");
        n2 = new Node_Data(2, "2,1,3");
        n3 = new Node_Data(3, "-4,7,1");
        n4 = new Node_Data(4, "-5,8,2");

        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);

        graph.connect(n1.getKey(), n3.getKey(), 1.2);
        graph.connect(n1.getKey(), n4.getKey(), 1.4);

        Iterator<EdgeData> iterator = graph.edgeIter(n1.getKey());
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            iterator.next();
        }

        assertEquals(2, count);
    }

    @Test
    void removeNode() {
        n1 = new Node_Data(1, "1, 2, 3");
        n2 = new Node_Data(2, "2, 1, 3");
        n3 = new Node_Data(3, "-4, 7, 1");
        n4 = new Node_Data(4, "-5, 8, 2");
        n5 = new Node_Data(5, "-3, 6, 4");
        n6 = new Node_Data(6, "-1, 5, 4");

        Directed_Weighted_Graph graph = new Directed_Weighted_Graph();
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.addNode(n5);
        graph.addNode(n6);
        assertEquals(6, graph.nodeSize());
        assertEquals(n5, graph.getNode(5));
        graph.removeNode(5);
        assertEquals(5, graph.nodeSize());
        assertNull(graph.getNode(5));
    }

    @Test
    void removeEdge() {
        n1 = new Node_Data(1,"1, 2, 3");
        n2 = new Node_Data(2,"2, 1, 3");
        n3 = new Node_Data(3,"-4, 7, 1");
        n4 = new Node_Data(4,"-5, 8, 2");
        n5 = new Node_Data(5,"-3, 6, 4");
        n6 = new Node_Data(6,"-1, 5, 4");

        Directed_Weighted_Graph graph = new Directed_Weighted_Graph();
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.addNode(n5);
        graph.addNode(n6);
        graph.connect(1, 2, 1.0);
        assertEquals(1, graph.edgeSize());
        assertEquals(new Edge_Data(1, 2, 1.0), graph.getEdge(1, 2));
        graph.removeEdge(1, 2);
        assertEquals(0, graph.edgeSize());
        assertNull(graph.getEdge(1, 2));


    }

    @Test
    void nodeSize() {
        n1 = new Node_Data(1, "1, 2, 3");
        n2 = new Node_Data(2, "2, 1, 3");
        n3 = new Node_Data(3, "-4, 7, 1");
        n4 = new Node_Data(4, "-5, 8, 2");
        n5 = new Node_Data(5, "-3, 6, 4");
        n6 = new Node_Data(6, "-1, 5, 4");

        Directed_Weighted_Graph graph = new Directed_Weighted_Graph();
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.addNode(n5);
        graph.addNode(n6);

        int node_in_graph = graph.nodeSize();
        assertEquals(node_in_graph, 6);
    }

    @Test
    void edgeSize() {
        n1 = new Node_Data(1,"1, 2, 3");
        n2 = new Node_Data(2,"2, 1, 3");
        n3 = new Node_Data(3,"-4, 7, 1");
        n4 = new Node_Data(4,"-5, 8, 2");
        n5 = new Node_Data(5,"-3, 6, 4");
        n6 = new Node_Data(6,"-1, 5, 4");

        Directed_Weighted_Graph graph = new Directed_Weighted_Graph();
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.addNode(n5);
        graph.addNode(n6);

        graph.connect(1, 2, 1.0);
        graph.connect(1, 3, 1.0);
        graph.connect(5, 3, 1.0);

        int edges_in_graph = graph.edgeSize();
        assertEquals(edges_in_graph, 3);
    }

    @Test
    void getMC() {
        n1 = new Node_Data(1, "1, 2, 3");
        n2 = new Node_Data(2, "2, 1, 3");
        n3 = new Node_Data(3, "-4, 7, 1");
        n4 = new Node_Data(4, "-5, 8, 2");
        n5 = new Node_Data(5, "-3, 6, 4");
        n6 = new Node_Data(6, "-1, 5, 4");

        Directed_Weighted_Graph graph = new Directed_Weighted_Graph();
        assertEquals(0, graph.getMC());
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.addNode(n5);
        graph.addNode(n6);
        assertEquals(6, graph.getMC());
    }
}