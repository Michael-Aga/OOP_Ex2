package tests;

import classes.Geo_Location;
import classes.Node_Data;
import classes.Directed_Weighted_Graph;
import classes.Directed_Weight_Graph_Algo;
import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;
import org.junit.jupiter.api.*;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DWGATest {
    private static final DirectedWeightedGraph testedDWG = new Directed_Weighted_Graph();
    private static final DirectedWeightedGraphAlgorithms testedDWGA = new Directed_Weight_Graph_Algo();
    private static final List<NodeData> nodeList = new LinkedList<>();

    /**
     * Create a graph to be tested
     */
    @BeforeAll
    static void createGraph() {
        for (int i = 1; i < 6; i++) {
            NodeData myNode = new Node_Data(i, new Geo_Location(i,i,i));
            testedDWG.addNode(myNode);
            nodeList.add(i - 1, myNode);
        }
        testedDWG.connect(1, 2, 1.1);
        testedDWG.connect(1, 4, 1.2);
        testedDWG.connect(2, 3, 4.0);
        testedDWG.connect(3, 4, 1.4);
        testedDWG.connect(3, 1, 1.6);
        testedDWG.connect(4, 5, 1.8);
        testedDWG.connect(5, 3, 1.0);
        testedDWGA.init(testedDWG);
    }

    /**
     * checking: init and getGraph
     */
    @Test
    @Order(1)
    void checkInitAndGetGraph() {
        DirectedWeightedGraph dwgCopy = testedDWG;
        DirectedWeightedGraphAlgorithms dwgAcopy = new Directed_Weight_Graph_Algo();
        dwgAcopy.init(dwgCopy); // reset the graph by init
        assertEquals(dwgCopy, dwgAcopy.getGraph()); // equals cause
    }

    /**
     * Test the isConnected function
     */
    @Test
    @Order(2)
    void isConnected() {
        assertTrue(testedDWGA.isConnected());

        NodeData firstNode = new Node_Data(6, "6, 6, 6");
        NodeData secondNode = new Node_Data(7, "7, 7, 7");

        DirectedWeightedGraph isConnectedDWG = new Directed_Weighted_Graph();
        DirectedWeightedGraphAlgorithms isConnectedDWGAlgo = new Directed_Weight_Graph_Algo();

        isConnectedDWG.addNode(firstNode);
        isConnectedDWG.addNode(secondNode);

        isConnectedDWGAlgo.init(isConnectedDWG);
        assertFalse(isConnectedDWGAlgo.isConnected());
    }

    /**
     * Test the shortestPathDist function
     */
    @Test
    @Order(3)
    void shortestPathDist() {
        double shortestPD = testedDWGA.shortestPathDist(1, 3);
        assertEquals(shortestPD, 4.0);
        shortestPD = testedDWGA.shortestPathDist(1, 1);
        assertEquals(shortestPD, 0);
    }

    /**
     * Test the shortestPath function
     */
    @Test
    @Order(4)
    void shortestPath() {
        List<NodeData> ans = testedDWGA.shortestPath(1, 3);
        assertEquals(ans.size(), 4);
        ans = testedDWGA.shortestPath(1, 1);
        assertEquals(ans.size(), 1);
    }

    /**
     * Check the center function
     */
    @Test
    @Order(5)
    void center() {
        DirectedWeightedGraph myDWG = new Directed_Weighted_Graph();
        DirectedWeightedGraphAlgorithms myDWGAlgo = new Directed_Weight_Graph_Algo();

        for (int i = 0; i < 9; i++) {
            NodeData myNode = new Node_Data(i, new Geo_Location(i, i, i));
            myDWG.addNode(myNode);
        }
        myDWG.connect(0, 1, 1.0);
        myDWG.connect(1, 0, 1.0);
        myDWG.connect(1, 2, 1.0);
        myDWG.connect(2, 1, 1.0);
        myDWG.connect(2, 9, 1.0);
        myDWG.connect(9, 2, 1.0);
        myDWG.connect(2, 6, 1.0);
        myDWG.connect(6, 2, 1.0);
        myDWG.connect(6, 7, 1.0);
        myDWG.connect(7, 6, 1.0);
        myDWG.connect(6, 8, 1.0);
        myDWG.connect(8, 6, 1.0);
        myDWG.connect(2, 3, 1.0);
        myDWG.connect(3, 2, 1.0);
        myDWG.connect(3, 4, 1.0);
        myDWG.connect(4, 3, 1.0);
        myDWG.connect(3, 5, 1.0);
        myDWG.connect(5, 3, 1.0);

        myDWGAlgo.init(myDWG);
        NodeData center = myDWGAlgo.center();
        assertEquals(2, center.getKey());
    }

    /**
     * Check the TSP function
     */
    @Test
    @Order(6)
    void tsp() {
        DirectedWeightedGraphAlgorithms tspDWG = new Directed_Weight_Graph_Algo();

        tspDWG.load("./data/G1.json");

        List<NodeData> list4 = new LinkedList<>();
        list4.add(tspDWG.getGraph().getNode(0));
        list4.add(tspDWG.getGraph().getNode(1));
        list4.add(tspDWG.getGraph().getNode(5));
        list4.add(tspDWG.getGraph().getNode(7));

        System.out.println(list4);
    }
}