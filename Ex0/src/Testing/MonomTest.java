package Testing;

import myMath.Monom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonomTest {

    @Test
    void derivative() {
    }

    @Test
    void isZero() {
    }

    @Test
    void add() {
    }

    @Test
    void multipy() {
        Monom m = new Monom("-1");
        Monom m2 = new Monom("4");
        Monom m3 = new Monom("4");
        m.multipy(m);
        assert (m.equals(new Monom("1")));
        m2.multipy(m3);
        assert (m2.equals(new Monom("16")));
        m2 = new Monom("4x");
        m3 = new Monom("5x");
        m2.multipy(m3);
        assert (m2.equals(new Monom("20x^2")));
        m2 = new Monom("4x^3");
        m3 = new Monom("5x^3");
        m2.multipy(m3);
        assert (m2.equals(new Monom("20x^6")));
    }

    @Test
    void flip() {
        Monom m = new Monom("x^2");
        Monom m1 = new Monom("-x^2");
        assertTrue(m1.flip().equals(m));
    }

    @Test
    void testEquals() {
        Monom m = new Monom("x^2");
        Monom m1 = new Monom("-x^2");
        assertFalse(m.equals(m1));
    }

    @Test
    void derive() {
        Monom m = new Monom("x^2");
        assertTrue(m.derive().equals(new Monom("2x")));
        assertTrue(m.derive().derive().equals(new Monom("2")));
        assertTrue(m.derive().derive().derive().equals(new Monom("0")));
    }

    @Test
    void initFromString() {
        Monom m = new Monom(0, 0);
        assertTrue(m.initFromString("x^2").equals(new Monom("x^2")));
    }

    @Test
    void copy() {
    }
}