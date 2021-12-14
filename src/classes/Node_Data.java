package classes;

import api.GeoLocation;
import api.NodeData;

public class Node_Data implements NodeData {
    private int id;
    private Geo_Location my_location;
    private double weight;
    private int tag;
    private String info;

    public Node_Data(int id, String pos) {
        this.id = id;
        String[] arr = pos.split(",");
        this.my_location = new Geo_Location(Double.parseDouble(arr[0]), Double.parseDouble(arr[1]), Double.parseDouble(arr[2]));
        this.weight = 0;
        this.info = "";
        this.tag = 0;
    }

    @Override
    public int getKey() {
        return this.id;
    }

    @Override
    public GeoLocation getLocation() {
        return this.my_location;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.my_location.setX(p.x());
        this.my_location.setY(p.y());
        this.my_location.setZ(p.z());
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight = w;
    }

    @Override
    public String getInfo() {
        return String.valueOf(info);
    }

    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }

    @Override
    public String toString() {
        return "Node_Data {" +
                "id = " + id +
                ", my location = "+"("+my_location.x() +","+ my_location.y() +","+ my_location.z()+")"+
                ", weight = " + weight +
                ", tag = " + tag +
                ", info = " + info +
                '}';
    }
}
