package tests;

import classes.Directed_Weight_Graph_Algo;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

class DWGAPerformanceTest {
    static DirectedWeightedGraphAlgorithms GAlgo = new Directed_Weight_Graph_Algo();

    @BeforeAll
    static void setup() {
        GAlgo.load("data/G1.json");
    }

    @Test
    void shortestPathDist() {
        GAlgo.shortestPathDist(1, 10);
    }

    @Test
    void shortestPathCenter() {
      GAlgo.shortestPathDist(1, 120);
    }

    @Test
    void tsp() {
        List<NodeData> list4 = new LinkedList<>();
        list4.add(GAlgo.getGraph().getNode(0));
        list4.add(GAlgo.getGraph().getNode(1));
        list4.add(GAlgo.getGraph().getNode(2));
        list4.add(GAlgo.getGraph().getNode(3));
        List<NodeData> ans =  GAlgo.tsp(list4);
        System.out.println(ans.toString());

    }
}