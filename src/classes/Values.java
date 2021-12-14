package classes;

import api.EdgeData;
import api.NodeData;

import java.util.HashMap;

public class Values {
    public HashMap<Integer, NodeData> Node_Data = new HashMap<>();
    public HashMap<Integer, HashMap<Integer, EdgeData>> toEdges = new HashMap<>();
    public HashMap<Integer, HashMap<Integer, EdgeData>> fromEdges = new HashMap<>();


    public Values(HashMap<Integer, NodeData> Nodes, HashMap<Integer,HashMap<Integer, EdgeData>> toEdges , HashMap<Integer,HashMap<Integer, EdgeData>> fromEdges ) {
        this.Node_Data = Nodes;
        this.fromEdges = fromEdges;
        this.toEdges = toEdges;
    }

}