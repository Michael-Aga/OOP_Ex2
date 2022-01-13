package classes;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.*;

/**
 * This class represents a graph that we are working on
 */
public class Directed_Weighted_Graph implements DirectedWeightedGraph {
    private HashMap<Integer, NodeData> Node_Data = new HashMap<>();
    private HashMap<Integer, HashMap<Integer, EdgeData>> toEdges = new HashMap<>();
    private HashMap<Integer, HashMap<Integer, EdgeData>> fromEdges = new HashMap<>();

    private int MC_Counter;
    private int EdgeCounter;

    /**
     * This is a constructor that get the hash maps that it needs for the graph
     * @param values the conversion of the data from the json
     */
    public Directed_Weighted_Graph(Values values) {
        this.Node_Data = values.Node_Data;
        this.toEdges = values.toEdges;
        this.fromEdges = values.fromEdges;
    }

    /**
     * This is an empty constructor
     */
    public Directed_Weighted_Graph() {
        this.Node_Data = new HashMap<>();
        this.toEdges = new HashMap<>();
        this.fromEdges = new HashMap<>();
    }

    /**
     * returns the node_data by the node_id,
     * @param key - the node_id
     * @return the node_data by the node_id, null if none.
     */
    @Override
    public NodeData getNode(int key) {
        return Node_Data.get(key);
    }

    /**
     * returns the data of the edge (src,dest), null if none.
     * Note: this method should run in O(1) time.
     * @param src From which node the edge comes from
     * @param dest To which node the edge is connected
     * @return The edge you wanted
     */
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

    /**
     * adds a new node to the graph with the given node_data.
     * Note: this method should run in O(1) time.
     * @param n The id of the node you want to add
     */
    @Override
    public void addNode(NodeData n) {
        Node_Data.put(n.getKey(), n);

        HashMap<Integer, EdgeData> toHash = new HashMap<>();
        toEdges.put(n.getKey(), toHash);

        HashMap<Integer, EdgeData> fromHash = new HashMap<>();
        fromEdges.put(n.getKey(), fromHash);
        MC_Counter++;
    }

    /**
     * Connects an edge with weight w between node src to node dest.
     * * Note: this method should run in O(1) time.
     * @param src - the source of the edge.
     * @param dest - the destination of the edge.
     * @param w - positive weight representing the cost (aka time, price, etc) between src-->dest.
     */
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

    /**
     * This method returns an Iterator for the
     * collection representing all the nodes in the graph.
     * Note: if the graph was changed since the iterator was constructed - a RuntimeException should be thrown.
     * @return Iterator<node_data>
     */
    @Override
    public Iterator<NodeData> nodeIter() {
        return this.Node_Data.values().iterator();
    }

    /**
     * This method returns an Iterator for all the edges in this graph.
     * Note: if any of the edges going out of this node were changed since the iterator was constructed - a RuntimeException should be thrown.
     * @return Iterator<api.EdgeData>
     */
    @Override
    public Iterator<EdgeData> edgeIter() {
        List<EdgeData> list = new ArrayList<>();

        this.toEdges.values().forEach((hash_of_edges) -> {
            list.addAll(hash_of_edges.values());
        });

        return list.iterator();
    }

    /**
     * This method returns an Iterator for edges getting out of the given node (all the edges starting (source) at the given node).
     * Note: if the graph was changed since the iterator was constructed - a RuntimeException should be thrown.
     * @return Iterator<api.EdgeData>
     */
    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        if (Objects.equals(getNode(node_id).getInfo(), "Entering"))
            return this.fromEdges.get(node_id).values().iterator();

        return this.toEdges.get(node_id).values().iterator();
    }

    /**
     * Deletes the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * This method should run in O(k), V.degree=k, as all the edges should be removed.
     * @return the data of the removed node (null if none).
     * @param key The id of the node you want to remove
     */
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

    /**
     * Deletes the edge from the graph,
     * Note: this method should run in O(1) time.
     * @param src From which node the edge comes from
     * @param dest To what node the edges is connected
     * @return the data of the removed edge (null if none).
     */
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

    /**
     * Note: this method should run in O(1) time.
     * @return Returns the number of vertices (nodes) in the graph.
     */
    @Override
    public int nodeSize() {
        return Node_Data.size();
    }

    /**
     * Note: this method should run in O(1) time.
     * @return Returns the number of edges (assume directional graph).
     */
    @Override
    public int edgeSize() {
        return EdgeCounter;
    }

    /**
     * @return Returns the Mode Count - for testing changes in the graph.
     */
    @Override
    public int getMC() {
        return MC_Counter;
    }
}
