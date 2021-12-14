package classes;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.*;

public class Directed_Weighted_Graph implements DirectedWeightedGraph {
    private HashMap<Integer, NodeData> Node_Data = new HashMap<>();
    private HashMap<Integer, HashMap<Integer, EdgeData>> toEdges = new HashMap<>();
    private HashMap<Integer, HashMap<Integer, EdgeData>> fromEdges = new HashMap<>();

    private int MC_Counter;
    private int EdgeCounter;

    public Directed_Weighted_Graph(Values values) {
        this.Node_Data = values.Node_Data;
        this.toEdges = values.toEdges;
        this.fromEdges = values.fromEdges;
    }

    public Directed_Weighted_Graph() {
        this.Node_Data = new HashMap<>();
        this.toEdges = new HashMap<>();
        this.fromEdges = new HashMap<>();
    }

    @Override
    public NodeData getNode(int key) {
        return Node_Data.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        Iterator<EdgeData> edgeDataIterator = edgeIter();

        if (Node_Data.containsKey(src) && Node_Data.containsKey(dest))
            while (edgeDataIterator.hasNext()) {
                EdgeData next = edgeDataIterator.next();
                if (next.getSrc() == src && next.getDest() == dest) {
                    return next;
                }
            }
        return null;
    }

    @Override
    public void addNode(NodeData n) {
        Node_Data.put(n.getKey(), n);

        HashMap<Integer, EdgeData> toHash = new HashMap<>();
        toEdges.put(n.getKey(), toHash);

        HashMap<Integer, EdgeData> fromHash = new HashMap<>();
        fromEdges.put(n.getKey(), fromHash);
        MC_Counter++;
    }

    @Override
    public void connect(int src, int dest, double w) {
        if (Node_Data.containsKey(src) && Node_Data.containsKey(dest) && w >= 0 && src != dest) {
            EdgeData ED = new Edge_Data(src, dest, w);
            if (getEdge(src, dest) == null) {
                toEdges.get(src).put(dest, ED);
                fromEdges.get(dest).put(src, ED);
                EdgeCounter++;
            } else {//Only used if it needs to update the weight
                toEdges.get(src).replace(dest, ED);
            }
            MC_Counter++;
        }
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return this.Node_Data.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        List<EdgeData> list = new ArrayList<>();

        this.toEdges.values().forEach((hash_of_edges) -> {
            list.addAll(hash_of_edges.values());
        });

        return list.iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        if (Objects.equals(getNode(node_id).getInfo(), "Entering"))
            return this.fromEdges.get(node_id).values().iterator();

        return this.toEdges.get(node_id).values().iterator();
    }

    @Override
    public NodeData removeNode(int key) {
        if (!this.Node_Data.containsKey(key))
            return null;

        else {
            Iterator<EdgeData> toEdgeData = edgeIter(key);

            while (toEdgeData.hasNext()) {
                removeEdge(key, toEdgeData.next().getDest());
            }

            getNode(key).setInfo("Entering");
            Iterator<EdgeData> fromEdgeData = edgeIter(key);

            while (fromEdgeData.hasNext()) {
                removeEdge(fromEdgeData.next().getSrc(), key);
            }

            getNode(key).setInfo(null);
            MC_Counter++;
            return this.Node_Data.remove(key);
        }
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        if (src != dest && toEdges.containsKey(src) && toEdges.get(src).containsKey(dest)) {
            EdgeCounter--;
            MC_Counter++;
            fromEdges.get(dest).remove(src);
            return toEdges.get(src).remove(dest);
        }
        return null;
    }

    @Override
    public int nodeSize() {
        return Node_Data.size();
    }

    @Override
    public int edgeSize() {
        return EdgeCounter;
    }

    @Override
    public int getMC() {
        return MC_Counter;
    }
}
