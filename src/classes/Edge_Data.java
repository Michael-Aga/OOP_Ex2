package classes;

import api.EdgeData;

import java.util.Objects;

/**
 * This is the class that represents each edge in the graph
 */
public class Edge_Data implements EdgeData {
    private int src;
    private int dest;
    private double w;
    private String Info;
    private int Tag;

    /**
     * This is a constructor for each edge
     * @param src the src node id
     * @param dest the dest node id
     * @param weight the weight of the edge
     */
    public Edge_Data(int src, int dest, double weight) {
        this.src = src;
        this.dest = dest;
        this.w = weight;
    }

    @Override
    public int getSrc() {
        return src;
    }

    @Override
    public int getDest() {
        return dest;
    }

    @Override
    public double getWeight() {
        return w;
    }

    @Override
    public String getInfo() {
        return Info;
    }

    @Override
    public void setInfo(String s) {
        Info = s;
    }

    @Override
    public int getTag() {
        return Tag;
    }

    @Override
    public void setTag(int t) {
        Tag = t;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge_Data edge = (Edge_Data) o;
        return src == edge.src &&
                dest == edge.dest &&
                Double.compare(edge.w, w) == 0 &&
                Tag == edge.Tag &&
                Objects.equals(Info, edge.Info);
    }
}
