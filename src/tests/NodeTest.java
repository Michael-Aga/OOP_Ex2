package tests;


import classes.Geo_Location;
import classes.Node_Data;
import api.NodeData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {
    @Test
    void getKey() {
        Node_Data n = new Node_Data(1, "1, 2, 3");
        assertEquals(1, n.getKey());
    }

    @Test
    void getLocation() {
        Node_Data n = new Node_Data(1, "1, 2, 3");
        assertEquals(1, n.getLocation().x());
        assertEquals(2, n.getLocation().y());
        assertEquals(3, n.getLocation().z());
    }

    @Test
    void setLocation() {
        Node_Data n = new Node_Data(1,"1, 2, 3");
        n.setLocation(new Geo_Location(4, 5, 6));
        assertEquals(4, n.getLocation().x());
        assertEquals(5, n.getLocation().y());
        assertEquals(6, n.getLocation().z());
    }

    @Test
    void getWeight() {
        Node_Data n = new Node_Data(1, "1, 2, 3");
        n.setWeight(1.0);
        assertEquals(1.0, n.getWeight());
    }

    @Test
    void setWeight() {
        Node_Data n = new Node_Data(1,"1, 2, 3");
        n.setWeight(2.0);
        assertEquals(2.0, n.getWeight());
    }

    @Test
    void getInfo() {
        Node_Data n = new Node_Data(1,"1, 2, 3");
        n.setInfo("white");
        assertEquals("white", n.getInfo());
    }

    @Test
    void getTag() {
        Node_Data n = new Node_Data(1, "1, 2, 3");
        n.setTag(10);
        assertEquals(10, n.getTag());
    }
}