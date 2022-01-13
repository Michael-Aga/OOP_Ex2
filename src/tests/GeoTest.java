package tests;

import classes.Geo_Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeoTest {
    @Test
    void x() {
        var g1 = new Geo_Location(1, 2, 3);
        assertEquals(1.0, g1.x());
    }

    @Test
    void y() {
        var g1 = new Geo_Location(1, 2, 3);
        assertEquals(2.0, g1.y());
    }

    @Test
    void z() {
        var g1 = new Geo_Location(1, 2, 3);
        assertEquals(3.0, g1.z());
    }

    @Test
    void distance() {
        var g1 = new Geo_Location(1, 2, 3);
        var g2 = new Geo_Location(1, 2, 3);
        assertEquals(0.0, g1.distance(g2));
    }
}