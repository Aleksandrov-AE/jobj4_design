package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.offset;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BoxTest {

    @Test
    void whatsThisUnknownWithInvalidVirtex() {
        Box box = new Box(9, 9);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void whatsThisUnknownWithInvalidEdge() {
        Box box = new Box(8, 0);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }


    @Test
    void getNumberOfVerticesWithInvalidVirtex() {
        Box box = new Box(1, 1);
        int numberOfVertices = box.getNumberOfVertices();
        assertThat(numberOfVertices).isEqualTo(-1);
    }

    @Test
    void getNumberOfVerticesWithInvalidEdge() {
        Box box = new Box(0, 0);
        int numberOfVertices = box.getNumberOfVertices();
        assertThat(numberOfVertices).isEqualTo(-1);
    }

    @Test
    void isExistInvalidVirtex() {
        Box box = new Box(3, 1);
        boolean boxExist = box.isExist();
        assertThat(boxExist).isFalse();
    }

    @Test
    void isExistInvalidEdge() {
        Box box = new Box(4, -1);
        boolean boxExist = box.isExist();
        assertThat(boxExist).isFalse();
    }
    @Test
    void getAreaWithInvalidEdge() {
        Box box = new Box(4, -1);
        double area = box.getArea();
        assertEquals(0d, area, 0.0001);
    }

    @Test
    void getAreaForCube() {
        Box box = new Box(8, 3);
        double area = box.getArea();
        assertThat(area).isCloseTo(54d, offset(0.0001));
    }
}