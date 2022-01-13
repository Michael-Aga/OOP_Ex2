package classes;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;

import java.util.*;

/**
 * This class represents our algorithm that works on the graph
 */
public class Directed_Weight_Graph_Algo implements DirectedWeightedGraphAlgorithms {
    private DirectedWeightedGraph G;

    /**
     * This is an empty constructor for the graph
     */
    public Directed_Weight_Graph_Algo() {
        this.G = new Directed_Weighted_Graph();
    }

    /**
     * A function to init the graph
     * @param g A graph that we are working on
     */
    @Override
    public void init(DirectedWeightedGraph g) {
        this.G = g;
    }

    /**
     * Get the graph we are working on
     * @return The graph that we are working on
     */
    @Override
    public DirectedWeightedGraph getGraph() {
        return this.G;
    }

    /**
     * This function makes a copy of the graph that we are working on
     * @return The copy of the graph
     */
    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph g = new Directed_Weighted_Graph();

        Iterator<NodeData> nodeDataIterator = G.nodeIter();
        for (NodeData a = nodeDataIterator.next(); nodeDataIterator.hasNext(); a = nodeDataIterator.next()) {
            g.addNode(a);
        }

        Iterator<NodeData> nodeDataIterator1 = G.nodeIter();
        Iterator<EdgeData> edgeDataIterator = G.edgeIter();

        for (NodeData b = nodeDataIterator1.next(); nodeDataIterator1.hasNext(); b = nodeDataIterator1.next()) {
            for (EdgeData e = edgeDataIterator.next(); edgeDataIterator.hasNext(); e = edgeDataIterator.next()) {
                g.connect(b.getKey(), e.getDest(), e.getWeight());
            }
        }
        return g;
    }

    /**
     * Returns true if and only if (iff) there is a valid path from each node to each
     * other node. NOTE: assume directional graph (all n*(n-1) ordered pairs).
     * @return True if the graph is connected and false if it's not
     */
    @Override
    public boolean isConnected() {

        Iterator<NodeData> nodeDataIterator = G.nodeIter();
        while (nodeDataIterator.hasNext()) {
            NodeData a = nodeDataIterator.next();
            a.setTag(0);
        }

        int Tag = 1;
        int k = 0;

        Iterator<NodeData> nodeDataIterator1 = G.nodeIter();
        if (nodeDataIterator1.hasNext())
            while (nodeDataIterator1.hasNext()) {
                NodeData v = nodeDataIterator1.next();
                k = v.getKey();
                break;
            }
        else
            return true;

        NodeData startN = G.getNode(k);
        startN.setTag(Tag);
        Queue<NodeData> q = new LinkedList<>();
        q.add(startN);

        while (q.size() != 0) {
            NodeData n0 = q.poll();
            Iterator<EdgeData> it = G.edgeIter(n0.getKey());

            while (it.hasNext()) {
                EdgeData e = it.next();
                if (G.getNode(e.getDest()).getTag() == 0) {
                    G.getNode(e.getDest()).setTag(Tag);
                    q.add(G.getNode(e.getDest()));
                }
            }
        }

        Iterator<NodeData> ite = G.nodeIter();
        while (ite.hasNext()) {
            NodeData n = ite.next();
            if (n.getTag() == 0)
                return false;
        }

        Iterator<NodeData> iter = G.nodeIter();
        while (iter.hasNext()) {
            NodeData n = iter.next();
            n.setInfo("Entering");
            n.setTag(0);
        }

        while (!q.isEmpty()) q.remove();

        startN.setTag(Tag);
        q.add(startN);
        while (q.size() != 0) {
            NodeData n0 = q.poll();

            Iterator<EdgeData> iterator = G.edgeIter(n0.getKey());
            while (iterator.hasNext()) {
                EdgeData e = iterator.next();
                if (G.getNode(e.getSrc()).getTag() == 0) {
                    G.getNode(e.getSrc()).setTag(Tag);
                    q.add(G.getNode(e.getSrc()));
                }
            }
        }

        boolean b = true;

        Iterator<NodeData> iterator1 = G.nodeIter();
        while (iterator1.hasNext()) {
            NodeData n1 = iterator1.next();
            if (n1.getTag() == 0)
                b = false;
        }

        Iterator<NodeData> it1 = G.nodeIter();
        while (it1.hasNext()) {
            NodeData n2 = it1.next();
            n2.setInfo(null);
        }

        return b;
    }

    /**
     * Computes the length of the shortest path between src to dest
     * Note: if no such path --> returns -1
     * @param src - start node
     * @param dest - end (target) node
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        if (G.getNode(src) == null || G.getNode(dest) == null)
            return -1;

        Iterator<NodeData> it = G.nodeIter();
        while (it.hasNext()) {
            NodeData n = it.next();
            n.setWeight(Double.MAX_VALUE);
        }

        double dist;
        G.getNode(src).setWeight(0);
        PriorityQueue<NodeData> PriorQ = new PriorityQueue(new comp());
        PriorQ.add(G.getNode(src));

        while (!PriorQ.isEmpty()) {
            NodeData n = PriorQ.poll();
            dist = n.getWeight();

            Iterator<EdgeData> iter = G.edgeIter(n.getKey());
            while(iter.hasNext()){
                EdgeData e = iter.next();
                if (G.getNode(e.getDest()).getWeight() > dist)
                    PriorQ.add(G.getNode(e.getDest()));

                if (dist + e.getWeight() < G.getNode(e.getDest()).getWeight())
                    G.getNode(e.getDest()).setWeight(dist + e.getWeight());
            }
        }
        if (G.getNode(dest).getWeight() == Double.MAX_VALUE)
            return -1;

        return G.getNode(dest).getWeight();
    }

    /**
     * Computes the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * see: https://en.wikipedia.org/wiki/Shortest_path_problem
     * Note if no such path --> returns null;
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        if (G.getNode(src) == null || G.getNode(dest) == null)
            return null;

        Iterator<NodeData> nodeDataIterator = G.nodeIter();
        while(nodeDataIterator.hasNext()){
            NodeData n = nodeDataIterator.next();
            n.setWeight(Double.MAX_VALUE);
            n.setInfo(null);
        }

        double dist;
        G.getNode(src).setWeight(0);
        PriorityQueue<NodeData> PriorQ = new PriorityQueue(new comp());
        PriorQ.add(G.getNode(src));

        while (!PriorQ.isEmpty()) {
            NodeData n = PriorQ.poll();
            dist = n.getWeight();

            Iterator<EdgeData> edgeDataIterator = G.edgeIter(n.getKey());
            while(edgeDataIterator.hasNext()){
                EdgeData e = edgeDataIterator.next();
                if (G.getNode(e.getDest()).getWeight() > dist)
                    PriorQ.add(G.getNode(e.getDest()));

                if (dist + e.getWeight() < G.getNode(e.getDest()).getWeight()) {
                    G.getNode(e.getDest()).setWeight(dist + e.getWeight());
                    G.getNode(e.getDest()).setTag(n.getKey());
                }
            }
        }

        if (G.getNode(dest).getWeight() == Double.MAX_VALUE)
            return null;

        LinkedList<NodeData> first_list = new LinkedList();
        LinkedList<NodeData> second_list = new LinkedList();

        first_list.add(G.getNode(dest));
        while (first_list.getLast() != G.getNode(src)) {
            assert first_list.peekLast() != null;
            NodeData n = G.getNode(first_list.peekLast().getTag());
            first_list.add(n);
        }

        while (!first_list.isEmpty()) {
            second_list.add(first_list.pollLast());
        }

        return second_list;
    }

    /**
     * Finds the api.NodeData which minimizes the max distance to all the other nodes.
     * Assuming the graph isConnected, elese return null. See: https://en.wikipedia.org/wiki/Graph_center
     * @return the Node data to which the max shortest path to all the other nodes is minimized.
     */
    @Override
    public NodeData center() {
        if (!isConnected()) {
            return null;
        }
        int key = 0;
        double Max = Double.MAX_VALUE;
        Iterator<NodeData> nodeDataIterator = G.nodeIter();

        while(nodeDataIterator.hasNext()){
            NodeData src = nodeDataIterator.next();
            double dist = 0;
            Iterator<NodeData> nodeDataIterator1 = G.nodeIter();

            while(nodeDataIterator1.hasNext()){
                NodeData dst = nodeDataIterator1.next();
                if (src != dst) {
                    double dist1 = shortestPathDist(src.getKey(), dst.getKey());
                    if (dist1 > dist) {
                        dist = dist1;
                    }
                }
            }
            if (dist < Max) {
                Max = dist;
                key = src.getKey();
            }
        }
        return G.getNode(key);
    }

    /**
     * Computes a list of consecutive nodes which go over all the nodes in cities.
     * the sum of the weights of all the consecutive (pairs) of nodes (directed) is the "cost" of the solution -
     * the lower the better.
     * See: https://en.wikipedia.org/wiki/Travelling_salesman_problem
     */
    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        if (cities == null || cities.isEmpty()) return null;
        if( !isConnected()) return null;

        List<NodeData> best_tsp = new ArrayList<>();
        NodeData src = cities.get(0);
        best_tsp.add(src);

        for (int i = 1; i < cities.size(); i++) {
            NodeData dest = cities.get(i);
            if (best_tsp.contains(dest)) continue;

            List<NodeData> path = shortestPath(src.getKey() , dest.getKey());
            for (int j = 0; j < path.size(); j++) {
                if (path.get(j) != src) {
                    best_tsp.add(path.get(j));
                }
            }
            src = dest;
        }
        return best_tsp;
    }

    /**
     * Saves this weighted (directed) graph to the given
     * file name - in JSON format
     * @param file - the file name (may include a relative path).
     * @return true - if the file was successfully saved
     */
    @Override
    public boolean save(String file) {
        return Save.save(file, getGraph());
    }

    /**
     * This method loads a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     * @param file - file name of JSON file
     * @return true - if the graph was successfully loaded.
     */
    @Override
    public boolean load(String file) {
        try {
            Values values = Load.load(file);
            Directed_Weighted_Graph g = new Directed_Weighted_Graph(values);
            this.init(g);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * This is a function to compare 2 nodes by their weight
     */
    private static class comp implements Comparator<NodeData> {
        @Override
        public int compare(NodeData o1, NodeData o2) {
            return Double.compare(o1.getWeight(), o2.getWeight());
        }
    }
}
