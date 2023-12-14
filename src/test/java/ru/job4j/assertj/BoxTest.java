package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisNotObject() {
        Box box = new Box(8, -3);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).startsWith("Cu");
    }

    @Test
    void qtyVertex() {
        Box box = new Box(8, 10);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(8);
        assertThat(number).isPositive();
    }

        @Test
    void whenNotExist() {
        Box box = new Box(7, 10);
        boolean figure = box.isExist();
        assertThat(figure).isFalse();
    }

    @Test
    void whenExist() {
        Box box = new Box(4, 10);
        boolean figure = box.isExist();
        assertThat(figure).isTrue();
    }

    @Test
    void getAriaCube() {
        Box box = new Box(8, 5);
        double area = box.getArea();
        assertThat(area).isEqualTo(150);
        assertThat(area).isPositive();
    }

    @Test
    void getTetrahedron() {
        Box box = new Box(4, 7);
        double area = box.getArea();
        assertThat(area).isEqualTo(84, withPrecision(1d));
        assertThat(area).isPositive();
    }

}
