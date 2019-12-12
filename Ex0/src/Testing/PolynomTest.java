package Testing;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import myMath.Polynom_able;
import myMath.ComplexFunction;
import myMath.Functions_GUI;
import myMath.Monom;
import myMath.Polynom;
import myMath.Range;
import myMath.function;
import org.w3c.dom.ls.LSOutput;

import java.util.Iterator;

public class PolynomTest {

    @Test
    public void initFromString() {
        Polynom_able p = new Polynom();

        String s1 = "-x^3+1-2x^2-3";
        String s2 = "-x-x+x+x^3+1";
        String s3 = "0";
        function p1 = p.initFromString(s1);
        function p2 = p.initFromString(s2);
        function p3 = p.initFromString(s3);
        assert (p1.toString().equals("-1.0x^3-2.0x^2-2.0"));
        assert (p2.toString().equals("1.0x^3-1.0x^1+1.0"));
        assert (p3.toString().equals("0"));
        s1 = "-1.0x^3+1.0-2x^2-3";
        p1 = new Polynom(s1);
        assert (p1.toString().equals("-1.0x^3-2.0x^2-2.0")); //-1.0x^3-2.0x^2-2.0
        s3 = "0-0";
        p3 = new Polynom(s3);
        assert (p3.toString().equals("0")); //0
        s3 = "-0-0";
        p3 = new Polynom(s3);
        assert (p3.toString().equals("0")); //0
        s3 = "-0-x";
        p3 = new Polynom(s3);
        assert (p3.toString().equals("-1.0x^1")); //-1.0x^1
        s3 = "-x-x";
        p3 = new Polynom(s3);
        assert (p3.toString().equals("-2.0x^1"));  //-2.0x^1
        s3 = "-x-x+2x";
        p3 = new Polynom(s3);
        assert (p3.toString().equals("0")); //0
        s3 = "-2x^3-x-x+2x-x+x^3"; //-1.0x^3-1.0x^1
        p3 = new Polynom(s3);
        assert (p3.toString().equals("-1.0x^3-1.0x^1"));
        s3 = "-x^3-x-x+2x-x+x^3"; //-1.0x^1
        p3 = new Polynom(s3);
        assert (p3.toString().equals("-1.0x^1"));
    }

    @Test
    public void addMonum() {
        Polynom_able p1 = new Polynom();
        String[] monoms1 = {"2x^3", "1", "x^2", "0.5x^2"};//I change the insertion order to check if the sorting algorithm works
        for (int i = 0; i < monoms1.length; i++) {
            Monom m = new Monom(monoms1[i]);
            p1.add(m);
        }
        assert (p1.toString().equals("2.0x^3+1.5x^2+1.0"));

        String[] monoms2 = {"-2x^3", "1", "-x^2", "-0.5x^2"};//I change the insertion order to check if the sorting algorithm works
        for (int i = 0; i < monoms2.length; i++) {
            Monom m = new Monom(monoms2[i]);
            p1.add(m);
        }
        assert (p1.toString().equals("2.0"));

        Monom m = new Monom("-2");
        p1.add(m);
        assert (p1.toString().equals("0"));


        String[] monoms3 = {"-1", "-x", "x", "-x^2"};

        for (int i = 0; i < monoms3.length; i++) {
            Monom d = new Monom(monoms3[i]);
            p1.add(d);
        }
        assert (p1.toString().equals("-1.0x^2-1.0"));

    }

    @Test
    public void addPolynum() {
        String s1 = "-x^3+1-2x^2-3";
        Polynom_able p1 = new Polynom(s1);
        String s2 = "1+x^3";
        Polynom_able p2 = new Polynom(s2);
        p1.add(p2);
        assert (p1.toString().equals("-2.0x^2-1.0"));
        p2.add(p2);
        assert (p2.toString().equals("2.0x^3+2.0"));
        s1 = "-0";
        p1 = new Polynom(s1);
        s2 = "0";
        p2 = new Polynom(s2);
        p1.add(p2);
        assert (p2.toString().equals("0"));
        s1 = "x-x^2";
        p1 = new Polynom(s1);
        s2 = "x^2-x";
        p2 = new Polynom(s2);
        p1.add(p2);
        assert (p2.toString().equals("1.0x^2-1.0x^1"));
        s1 = "0";
        p1 = new Polynom(s1);
        s2 = "x^3+1";
        p2 = new Polynom(s2);
        p1.add(p2);
        assert (p1.toString().equals("1.0x^3+1.0"));
        s1 = "0";
        p1 = new Polynom(s1);
        p2.add(p1);
        assert (p1.toString().equals("0"));
    }

    @Test
    public void root()
	{
        String s1 = "x^3+1-2x^2";
        String s2 = "x+1";
        Polynom_able p1 = new Polynom(s1);

        assert (p1.root(-0.5, 1, 0.1) == 0.953125);
        assert (p1.root(-0.5, 1, 0.0001) == 0.999908447265625);
        assert (p1.root(-2.5, 1, 0.0001) == -0.6180343627929688);
        s1 = "-x^4+1-2x^2-3x^3";
        p1 = new Polynom(s1);
        assert (p1.root(-2.5, -2, 0.0001) == -2.1787109375);
        assert (p1.root(0, 1, 0.0001) == 0.51287841796875);
        s1 = "0";
        p1 = new Polynom(s1);
        assert (p1.root(-1, 1, 0.0001) == 0.0);
        s1 = "x-1";
        p1 = new Polynom(s1);
        assert (p1.root(0, 1, 0.0001) == 0.99993896484375);

    }

	@Test
	public void area()
	{
		String s1 = "x^3+1-2x^2";
		String s2 = "-1+x";

		Polynom_able p1 = new Polynom(s1);
		Polynom_able p2 = new Polynom(s2);
		p2.multiply(p1);

		assert (Math.abs(p1.area(-0.5, 0.5, 0.1) - 0.833333333333)<=0.1);
		assert (Math.abs(p1.area(0, 3, 0.00001) - 5.25)<=0.001);
		assert (Math.abs(p2.area(-0.5, 0.5, 000.1) + 0.820833333333)<=000.1);
		assert (Math.abs(p2.area(1, 3, 0.0001) - 7.73333333333)<=0.01);

		s1 = "-x^4+1-2x^2-3x^3";
		p1 = new Polynom(s1);
		assert (Math.abs(p1.area(-2, -0.5, 0.0001) - 1.809375) <= 0.0001);
		assert (Math.abs(p1.area(1, 2, 0.0001) +21.1166666667) <= 0.01);
		s1 = "0";
		p1 = new Polynom(s1);
		assert (p1.area(-1, 5, 0.0001) == 0.0);
		s1 = "x-1";
		p1 = new Polynom(s1);
		assert (Math.abs(p1.area(1, 5, 0.0001) - 8) <= 0.01);
	}

    @Test
    public void equal() {
        String s1, s2;
        Polynom_able p1, p2;
        s1 = "-x^3+1-2x^2-3";
        p1 = new Polynom(s1);
        s2 = "x^3+1";
        p2 = new Polynom(s2);
        assert (p1.equals(p1));
        assert (!p1.equals(p2));
        s2 = "-x^3+1-2x^2-3";
        p2 = new Polynom(s2);
        assert (p1.equals(p2));
        assert (p2.equals(p1));
        s1 = "-x";
        p1 = new Polynom(s1);
        s2 = "-x+1";
        p2 = new Polynom(s2);
        assert (!p1.equals(p2));
        assert (!p2.equals(p1));
        s2 = "-2x^2";
        p2 = new Polynom(s2);
        assert (!p1.equals(p2));
        assert (!p2.equals(p1));
        s2 = "-2";
        p2 = new Polynom(s2);
        assert (!p1.equals(p2));
        assert (!p2.equals(p1));
    }

    @Test
    public void substract() {
        String s1, s2;
        Polynom_able p1, p2;

        s1 = "-x^3+1-2x^2-3";
        p1 = new Polynom(s1);
        s2 = "x^3+1";
        p2 = new Polynom(s2);

        p1.substract(p2);
        assert (p1.toString().equals("-2.0x^3-2.0x^2-3.0"));
        assert (p2.toString().equals("1.0x^3+1.0"));
        p2.substract(p2);
        assert (p2.toString().equals("0"));
        s1 = "-0";
        p1 = new Polynom(s1);
        s2 = "0";
        p2 = new Polynom(s2);
        p1.substract(p2);
        assert (p1.toString().equals("0"));
        assert (p2.toString().equals("0"));
        s1 = "x-x^2";
        p1 = new Polynom(s1);
        s2 = "x^2-x";
        p2 = new Polynom(s2);
        p1.substract(p2);
        assert (p1.toString().equals("-2.0x^2+2.0x^1"));

        s1 = "0";
        p1 = new Polynom(s1);
        s2 = "x^3+1";
        p2 = new Polynom(s2);

        p1.substract(p2);
        assert (p1.toString().equals("-1.0x^3-1.0"));

        s1 = "0";
        p1 = new Polynom(s1);
        p2.substract(p1);
        assert (p2.toString().equals("1.0x^3+1.0"));
    }

    @Test
    public void multiply() {
        String s1, s2;
        Polynom_able p1, p2;

        s1 = "-x^3+1-2x^2-3";
        p1 = new Polynom(s1);
        s2 = "-x^3+1";
        p2 = new Polynom(s2);
        p1.multiply(p2);
		assert (p1.toString().equals("1.0x^6+2.0x^5+1.0x^3-2.0x^2-2.0"));
        s1 = "-x^3+1-2x^2-3";
        p1 = new Polynom(s1);
        s2 = "-1-1";
        p2 = new Polynom(s2);
        p1.multiply(p2);
		assert (p1.toString().equals("2.0x^3+4.0x^2+4.0"));
        p1.multiply(p1);
		assert (p1.toString().equals("4.0x^6+16.0x^5+16.0x^4+16.0x^3+32.0x^2+16.0"));
        s2 = "0";
        p2 = new Polynom(s2);
        p1.multiply(p2);
		assert (p1.toString().equals("0"));
        p1.multiply(p2);
		assert (p1.toString().equals("0"));
    }

	@Test
	public void derivative()
	{
		String s1, s2;
		Polynom_able p1, p2;
		s1 = "-x^3+1-2x^2-3";
		p1 = new Polynom(s1);
		assert (p1.derivative().toString().equals("-3.0x^2-4.0x^1"));
		assert (p1.derivative().derivative().toString().equals("-6.0x^1-4.0"));
		assert (p1.derivative().derivative().derivative().toString().equals("-6.0"));
		assert (p1.derivative().derivative().derivative().derivative().toString().equals("0"));
		assert (p1.derivative().derivative().derivative().derivative().derivative().toString().equals("0"));
	}
}
