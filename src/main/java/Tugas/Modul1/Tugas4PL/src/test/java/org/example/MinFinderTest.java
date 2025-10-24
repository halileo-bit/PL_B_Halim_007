package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MinFinderTest {

    @Test
    void testSkenario1() {
        assertEquals(1, MinFinder.findMin(1, 2, 3));
    }

    @Test
    void testSkenario2() {
        assertEquals(-3, MinFinder.findMin(-1, -2, -3));
    }

    @Test
    void testSkenario3() {
        assertEquals(0, MinFinder.findMin(0, 0, 1));
    }
}
