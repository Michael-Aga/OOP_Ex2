package tests;

import classes.Edge_Data;
import api.EdgeData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {

    /**
     * Test the getSrc function
     */
    @Test
    void getSrcTest() {
        EdgeData e = new Edge_Data(1, 2, 3);
        assertEquals(1, e.getSrc());
    }

    /**
     * Test the getDest function
     */
    @Test
    void getDestTest() {
        EdgeData e = new Edge_Data(1, 2, 3);
        assertEquals(2, e.getDest());
    }

    /**
     * Test the getWeight function
     */
    @Test
    void getWeightTest() {
        EdgeData e = new Edge_Data(1, 2, 3);
        assertEquals(3.0, e.getWeight());
    }

    /**
     * Test the getInfo function
     */
    @Test
    void getInfoTest() {
        EdgeData e = new Edge_Data(1, 2, 3);
        e.setInfo("white");
        String es = e.getInfo();
        assertEquals("white", es);
    }

    /**
     * Test the setInfo function
     */
    @Test
    void setInfo() {
        EdgeData e = new Edge_Data(1, 2, 3);
        e.setInfo("gray");
        assertEquals("gray", e.getInfo());
        e.setInfo("black");
        assertEquals("black", e.getInfo());
    }


    @Test
    void setTag() {
        EdgeData e = new Edge_Data(1, 2, 3);
        e.setTag(0);
        assertEquals(0, e.getTag());
        e.setTag(1);
        assertEquals(1, e.getTag());
    }

    @Test
    void testEquals() {
        EdgeData e1 = new Edge_Data(1, 2, 3);
        EdgeData e2 = new Edge_Data(1, 2, 3);
        assertNotSame(e1, e2);
    }
}