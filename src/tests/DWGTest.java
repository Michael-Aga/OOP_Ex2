package tests;

import classes.Edge_Data;
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
    private static Node_Data firstNode, secondNode, thirdNode, forthNode, fifthNode, sixNode;

    /**
     * Test the getNode function
     */
    @Test
    @Timeout(value = 2, unit = TimeUnit.MILLISECONDS)
    void getNodetest() {
        Directed_Weighted_Graph getNodeDWG = new Directed_Weighted_Graph();

        firstNode = new Node_Data(1, "1,2,3");
        getNodeDWG.addNode(firstNode);
        assertEquals(firstNode, getNodeDWG.getNode(1));
        assertEquals(1, getNodeDWG.nodeSize());
    }

    /**
     * Test the getEdge function
     */
    @Test
    void getEdgetest() {
        Directed_Weighted_Graph getEdgeDWG = new Directed_Weighted_Graph();

        firstNode = new Node_Data(1, "1,2,3");
        secondNode = new Node_Data(2, "2,1,3");
        thirdNode = new Node_Data(3, "-4,7,1");
        forthNode = new Node_Data(4, "-5, 8, 2");

        getEdgeDWG.addNode(firstNode);
        getEdgeDWG.addNode(secondNode);
        getEdgeDWG.addNode(thirdNode);
        getEdgeDWG.addNode(forthNode);

        getEdgeDWG.connect(firstNode.getKey(), thirdNode.getKey(), 1.1);
        getEdgeDWG.connect(secondNode.getKey(), forthNode.getKey(), 1.2);

        assertEquals(new Edge_Data(1, 3, 1.1), getEdgeDWG.getEdge(1, 3));
        assertEquals(new Edge_Data(2, 4, 1.2), getEdgeDWG.getEdge(2, 4));

    }

    /**
     * Test the connect function
     */
    @Test
    void connecttest() {
        Directed_Weighted_Graph connectDWG = new Directed_Weighted_Graph();

        firstNode = new Node_Data(1, "1,2,3");
        secondNode = new Node_Data(2, "2,1,3");

        connectDWG.addNode(firstNode);
        connectDWG.addNode(secondNode);
        connectDWG.connect(1, 2, 1.0);
        assertEquals(1, connectDWG.edgeSize());
        assertEquals(new Edge_Data(1, 2, 1.0), connectDWG.getEdge(1, 2));
    }

    /**
     * Test the node iterator
     */
    @Test
    void nodeIteratortest() {
        Directed_Weighted_Graph nodeIterDWG = new Directed_Weighted_Graph();

        firstNode = new Node_Data(1, "1,2,3");
        secondNode = new Node_Data(2, "2,1,3");

        nodeIterDWG.addNode(firstNode);
        nodeIterDWG.addNode(secondNode);

        Iterator<NodeData> iterator = nodeIterDWG.nodeIter();
        int nodeCounter = 0;
        while (iterator.hasNext()) {
            nodeCounter++;
            iterator.next();
        }
        assertEquals(nodeCounter, 2);
    }

    /**
     * Test the edge iterator
     */
    @Test
    void edgeIteratortest() {
        Directed_Weighted_Graph edgeIterDWG = new Directed_Weighted_Graph();

        firstNode = new Node_Data(1, "1,2,3");
        secondNode = new Node_Data(2, "2,1,3");
        thirdNode = new Node_Data(3, "-4,7,1");
        forthNode = new Node_Data(4, "-5,8,2");

        edgeIterDWG.addNode(firstNode);
        edgeIterDWG.addNode(secondNode);
        edgeIterDWG.addNode(thirdNode);
        edgeIterDWG.addNode(forthNode);

        edgeIterDWG.connect(firstNode.getKey(), thirdNode.getKey(), 1.1);
        edgeIterDWG.connect(secondNode.getKey(), forthNode.getKey(), 1.2);

        Iterator<EdgeData> iterator = edgeIterDWG.edgeIter();
        int edgeCount = 0;
        while (iterator.hasNext()) {
            edgeCount++;
            iterator.next();
        }
        assertEquals(edgeCount, 2);
    }

    /**
     * Test the both node and edge iterator
     */
    @Test
    void testEdgeIterNodeIDtest() {
        Directed_Weighted_Graph bothDWG = new Directed_Weighted_Graph();

        firstNode = new Node_Data(1, "1,2,3");
        secondNode = new Node_Data(2, "2,1,3");
        thirdNode = new Node_Data(3, "-4,7,1");
        forthNode = new Node_Data(4, "-5,8,2");

        bothDWG.addNode(firstNode);
        bothDWG.addNode(secondNode);
        bothDWG.addNode(thirdNode);
        bothDWG.addNode(forthNode);

        bothDWG.connect(firstNode.getKey(), thirdNode.getKey(), 1.2);
        bothDWG.connect(firstNode.getKey(), forthNode.getKey(), 1.4);

        Iterator<EdgeData> iterator = bothDWG.edgeIter(firstNode.getKey());
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            iterator.next();
        }

        assertEquals(2, count);
    }

    /**
     * Test the removeNode function
     */
    @Test
    void removeNodetest() {
        firstNode = new Node_Data(1, "1, 2, 3");
        secondNode = new Node_Data(2, "2, 1, 3");
        thirdNode = new Node_Data(3, "-4, 7, 1");
        forthNode = new Node_Data(4, "-5, 8, 2");
        fifthNode = new Node_Data(5, "-3, 6, 4");
        sixNode = new Node_Data(6, "-1, 5, 4");

        Directed_Weighted_Graph removeNodeDWG = new Directed_Weighted_Graph();
        removeNodeDWG.addNode(firstNode);
        removeNodeDWG.addNode(secondNode);
        removeNodeDWG.addNode(thirdNode);
        removeNodeDWG.addNode(forthNode);
        removeNodeDWG.addNode(fifthNode);
        removeNodeDWG.addNode(sixNode);
        assertEquals(6, removeNodeDWG.nodeSize());
        assertEquals(fifthNode, removeNodeDWG.getNode(5));
        removeNodeDWG.removeNode(5);
        assertEquals(5, removeNodeDWG.nodeSize());
        assertNull(removeNodeDWG.getNode(5));
    }

    @Test
    void removeEdgetest() {
        firstNode = new Node_Data(1,"1, 2, 3");
        secondNode = new Node_Data(2,"2, 1, 3");
        thirdNode = new Node_Data(3,"-4, 7, 1");
        forthNode = new Node_Data(4,"-5, 8, 2");
        fifthNode = new Node_Data(5,"-3, 6, 4");
        sixNode = new Node_Data(6,"-1, 5, 4");

        Directed_Weighted_Graph removeEdgeDWG = new Directed_Weighted_Graph();
        removeEdgeDWG.addNode(firstNode);
        removeEdgeDWG.addNode(secondNode);
        removeEdgeDWG.addNode(thirdNode);
        removeEdgeDWG.addNode(forthNode);
        removeEdgeDWG.addNode(fifthNode);
        removeEdgeDWG.addNode(sixNode);
        removeEdgeDWG.connect(1, 2, 1.0);
        assertEquals(1, removeEdgeDWG.edgeSize());
        assertEquals(new Edge_Data(1, 2, 1.0), removeEdgeDWG.getEdge(1, 2));
        removeEdgeDWG.removeEdge(1, 2);
        assertEquals(0, removeEdgeDWG.edgeSize());
        assertNull(removeEdgeDWG.getEdge(1, 2));


    }

    /**
     * Test the nodeSize function
     */
    @Test
    void nodeSizetest() {
        firstNode = new Node_Data(1, "1, 2, 3");
        secondNode = new Node_Data(2, "2, 1, 3");
        thirdNode = new Node_Data(3, "-4, 7, 1");
        forthNode = new Node_Data(4, "-5, 8, 2");
        fifthNode = new Node_Data(5, "-3, 6, 4");
        sixNode = new Node_Data(6, "-1, 5, 4");

        Directed_Weighted_Graph nodeSizeDWG = new Directed_Weighted_Graph();
        nodeSizeDWG.addNode(firstNode);
        nodeSizeDWG.addNode(secondNode);
        nodeSizeDWG.addNode(thirdNode);
        nodeSizeDWG.addNode(forthNode);
        nodeSizeDWG.addNode(fifthNode);
        nodeSizeDWG.addNode(sixNode);

        int nodeQuantity = nodeSizeDWG.nodeSize();
        assertEquals(nodeQuantity, 6);
    }

    /**
     * Test the edgeSize function
     */
    @Test
    void edgeSizetest() {
        firstNode = new Node_Data(1,"1, 2, 3");
        secondNode = new Node_Data(2,"2, 1, 3");
        thirdNode = new Node_Data(3,"-4, 7, 1");
        forthNode = new Node_Data(4,"-5, 8, 2");
        fifthNode = new Node_Data(5,"-3, 6, 4");
        sixNode = new Node_Data(6,"-1, 5, 4");

        Directed_Weighted_Graph edgeSizeDWG = new Directed_Weighted_Graph();
        edgeSizeDWG.addNode(firstNode);
        edgeSizeDWG.addNode(secondNode);
        edgeSizeDWG.addNode(thirdNode);
        edgeSizeDWG.addNode(forthNode);
        edgeSizeDWG.addNode(fifthNode);
        edgeSizeDWG.addNode(sixNode);

        edgeSizeDWG.connect(1, 2, 1.0);
        edgeSizeDWG.connect(1, 3, 1.0);
        edgeSizeDWG.connect(5, 3, 1.0);

        int edgeQuantity = edgeSizeDWG.edgeSize();
        assertEquals(edgeQuantity, 3);
    }

    /**
     * Test the getMC function
     */
    @Test
    void getMCtest() {
        firstNode = new Node_Data(1, "1, 2, 3");
        secondNode = new Node_Data(2, "2, 1, 3");
        thirdNode = new Node_Data(3, "-4, 7, 1");
        forthNode = new Node_Data(4, "-5, 8, 2");
        fifthNode = new Node_Data(5, "-3, 6, 4");
        sixNode = new Node_Data(6, "-1, 5, 4");

        Directed_Weighted_Graph getMCGDW = new Directed_Weighted_Graph();
        assertEquals(0, getMCGDW.getMC());
        getMCGDW.addNode(firstNode);
        getMCGDW.addNode(secondNode);
        getMCGDW.addNode(thirdNode);
        getMCGDW.addNode(forthNode);
        getMCGDW.addNode(fifthNode);
        getMCGDW.addNode(sixNode);
        assertEquals(6, getMCGDW.getMC());
    }
}