package tests;

import classes.Directed_Weight_Graph_Algo;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

class DWGAPerformanceTest {
    static DirectedWeightedGraphAlgorithms myGraph = new Directed_Weight_Graph_Algo();

    /**
     * Load the graph from the data
     */
    @BeforeAll
    static void setup() {
        myGraph.load("data/G1.json");
    }

    @Test
    void shortestPathDist() {
        myGraph.shortestPathDist(1, 10);
    }

    @Test
    void shortestPathCenter() {
      myGraph.shortestPathDist(1, 120);
    }

    @Test
    void tsp() {
        List<NodeData> tspList = new LinkedList<>();
        tspList.add(myGraph.getGraph().getNode(0));
        tspList.add(myGraph.getGraph().getNode(1));
        tspList.add(myGraph.getGraph().getNode(2));
        tspList.add(myGraph.getGraph().getNode(3));
        List<NodeData> ans =  myGraph.tsp(tspList);
        System.out.println(ans.toString());
    }
}