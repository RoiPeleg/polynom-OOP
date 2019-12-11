package Testing;

import myMath.Monom;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonomTest {

    @Test
    void derivative() {
        Monom m = new Monom("x^2");
        assertTrue(m.derivative().equals(new Monom("2x")));
        assertTrue(m.derivative().derivative().equals(new Monom("2")));
        assertTrue(m.derivative().derivative().derivative().equals(new Monom("0")));
        try {
            m.derivative().derivative().derivative().derivative();
        } catch (Exception e) {
        }
    }

    @Test
    void isZero() {
        assertFalse(new Monom("x^2").isZero());
        assertTrue(new Monom("0").isZero());
    }

    @Test
    void add() {
        Monom m = new Monom("x^2");
        m.add(new Monom("2x^2"));
        assertTrue(m.equals(new Monom("3x^2")));
        try {
            m.add(null);
        } catch (Exception e) {
        }
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
        try {
            m.multipy(null);
        } catch (Exception e) {
        }
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
        Monom e = new Monom("x^2");
        m = (Monom) m.initFromString("x^2");
        assertTrue(m.equals(e));
    }

    @Test
    void copy() {
        Monom e = new Monom("x^2");
        Monom x = (Monom) e.copy();
        assertTrue(x.equals(e));
        assertTrue(((Monom) (new Monom("0")).copy()).equals(new Monom("0")));
    }
}