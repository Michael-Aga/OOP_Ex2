package classes;

import api.GeoLocation;

public class Geo_Location implements GeoLocation {
    private double x = 0;
    private double y = 0;
    private double z = 0;

    public Geo_Location(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    @Override
    public double distance(GeoLocation g) {

        double difference_of_x = Math.pow(this.x - g.x(),2);
        double difference_of_y = Math.pow(this.y - g.y(),2);
        double difference_of_z = Math.pow(this.z - g.z(),2);

        return Math.sqrt(difference_of_x + difference_of_y + difference_of_z);
    }
}
