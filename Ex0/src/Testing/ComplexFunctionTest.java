package Testing;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import myMath.ComplexFunction;
import myMath.Functions_GUI;
import myMath.Monom;
import myMath.Polynom;
import myMath.Range;
import myMath.function;

import java.util.Iterator;

public class ComplexFunctionTest {

    @Test
    public void area() {
        String s = "f(x) = plus(div(1.0x^1+1.0,2.0),x^3)";
        ComplexFunction cf = new ComplexFunction(new Polynom("x"));
        function f1 = cf.initFromString(s);

        ComplexFunction cf1 = (ComplexFunction) f1;


        assert (Math.abs(cf1.area(0, 5, 0.000001)-165)<=0.001);
    }

    @Test
    public void root() {
        String s = "f(x) = plus(div(1.0x^1+1.0,2.0),x^3)";
        ComplexFunction cf = new ComplexFunction(new Polynom("x"));
        function f1 = cf.initFromString(s);
        ComplexFunction cf1 = (ComplexFunction) f1;
        assert (Math.abs(cf1.root(-1, 0, 0.000001)+0.59)<=0.001);




    }

    @Test
    public void f() {
    }

    @Test
    public void initFromString() {
    }

    @Test
    public void copy() {
    }

    @Test
    public void max() {
    }

    @Test
    public void min() {
    }

    @Test
    public void plus() {
    }

    @Test
    public void mul() {
    }

    @Test
    public void div() {
    }

    @Test
    public void comp() {
    }

    @Test
    public void testEquals() {
    }
}