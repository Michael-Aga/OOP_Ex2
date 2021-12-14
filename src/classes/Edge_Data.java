package classes;

import api.EdgeData;

public class Edge_Data implements EdgeData {
    private int src;
    private int dest;
    private double w;
    private String Info;
    private int Tag;

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
}
