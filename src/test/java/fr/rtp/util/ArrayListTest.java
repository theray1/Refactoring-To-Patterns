package fr.rtp.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    private ArrayList list;

    @BeforeEach
    void setup() {
        list = new ArrayList();
        list.setReadOnly(false);
    }

    @Test
    void add() {
        list.add("child");
        list.add("other");
        list.add(33);

        assert list.contains("child");
        assert list.contains(33);
        assert list.contains("other");
    }

    @Test
    void setReadOnly() {
        list.setReadOnly(true);
        list.add(42);
        assert !list.contains(42);

    }

    @Test
    void isReadOnly() {
        list.setReadOnly(false);
        assert !list.isReadOnly();

        list.setReadOnly(true);
        assert list.isReadOnly();
    }
}
