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
    private static final List<NodeData> myList = new LinkedList<>();

    @BeforeAll
    static void createG() {
        for (int i = 1; i < 6; i++) {
            NodeData n = new Node_Data(i, new Geo_Location(i,i,i));
            testedDWG.addNode(n);
            myList.add(i - 1, n);
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
    void init_getGraph() {

        DirectedWeightedGraph dwgCopy = testedDWG;
        DirectedWeightedGraphAlgorithms dwgAcopy = new Directed_Weight_Graph_Algo();
        dwgAcopy.init(dwgCopy); // reset the graph by init
        assertEquals(dwgCopy, dwgAcopy.getGraph()); // equals cause
    }

    @Test
    @Order(2)
    void isConnected() {

        assertTrue(testedDWGA.isConnected()); // the graph that made BeforeAll
        // build another graph that not connected
        NodeData nCheck1 = new Node_Data(6, "6, 6, 6");
        NodeData nCheck2 = new Node_Data(7, "7, 7, 7");
        DirectedWeightedGraph g1 = new Directed_Weighted_Graph();
        DirectedWeightedGraphAlgorithms ga1 = new Directed_Weight_Graph_Algo();
        g1.addNode(nCheck1);
        g1.addNode(nCheck2);
        ga1.init(g1);
        assertFalse(ga1.isConnected());
    }

    /**
     * return the shortestPathDist (double)
     */
    @Test
    @Order(3)
    void shortestPathDist() {
        double ans = testedDWGA.shortestPathDist(1, 3);
        assertEquals(ans, 4.0);
        ans = testedDWGA.shortestPathDist(1, 1);
        assertEquals(ans, 0);
    }

    /**
     * return list<NodeData> of the shortest Path in the graph
     */
    @Test
    @Order(4)
    void shortestPath() {
        List<NodeData> ans = testedDWGA.shortestPath(1, 3);
        assertEquals(ans.size(), 4);
        ans = testedDWGA.shortestPath(1, 1);
        assertEquals(ans.size(), 1);
    }

    @Test
    @Order(5)
    void center() {
        DirectedWeightedGraph gg = new Directed_Weighted_Graph();
        DirectedWeightedGraphAlgorithms gga = new Directed_Weight_Graph_Algo();



        for (int i = 0; i < 9; i++) {
            NodeData n = new Node_Data(i, new Geo_Location(i, i, i));
            gg.addNode(n);
        }
        gg.connect(0, 1, 1.0);
        gg.connect(1, 0, 1.0);
        gg.connect(1, 2, 1.0);
        gg.connect(2, 1, 1.0);
        gg.connect(2, 9, 1.0);
        gg.connect(9, 2, 1.0);
        gg.connect(2, 6, 1.0);
        gg.connect(6, 2, 1.0);
        gg.connect(6, 7, 1.0);
        gg.connect(7, 6, 1.0);
        gg.connect(6, 8, 1.0);
        gg.connect(8, 6, 1.0);
        gg.connect(2, 3, 1.0);
        gg.connect(3, 2, 1.0);
        gg.connect(3, 4, 1.0);
        gg.connect(4, 3, 1.0);
        gg.connect(3, 5, 1.0);
        gg.connect(5, 3, 1.0);

        gga.init(gg);
        NodeData center = gga.center();
        assertEquals(2, center.getKey());

    }

    @Test
    @Order(6)
    void tsp() {
        DirectedWeightedGraphAlgorithms GAlgo = new Directed_Weight_Graph_Algo();
        GAlgo.load("./data/G1.json");
        List<NodeData> list4 = new LinkedList<>();
        list4.add(GAlgo.getGraph().getNode(0));
        list4.add(GAlgo.getGraph().getNode(1));
        list4.add(GAlgo.getGraph().getNode(5));
        list4.add(GAlgo.getGraph().getNode(7));

        System.out.println(list4);

    }

    @Test
    @Order(7)
    void shortestPathDistTestForMinus1() {
        DirectedWeightedGraph NotConnectedG = new Directed_Weighted_Graph();
        NodeData n1 = new Node_Data(0,"1.0, 2.0, 3.0");
        NodeData n2 = new Node_Data(1, "3.0, 4.0, 5.0");
        NotConnectedG.addNode(n1);
        NotConnectedG.addNode(n2);
        DirectedWeightedGraphAlgorithms notConnectGA = new Directed_Weight_Graph_Algo();
        notConnectGA.init(NotConnectedG);
        double result =  notConnectGA.shortestPathDist(0,1);
        assertEquals(result,-1.0);
    }

}