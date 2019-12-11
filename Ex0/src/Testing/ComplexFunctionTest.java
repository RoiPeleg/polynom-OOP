package Testing;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import myMath.ComplexFunction;
import myMath.Functions_GUI;
import myMath.Monom;
import myMath.Polynom;
import myMath.Range;
import myMath.function;
import org.w3c.dom.ls.LSOutput;

import java.util.Iterator;

public class ComplexFunctionTest {

    @Test
    public void f() {
        ComplexFunction cf = new ComplexFunction(new Polynom("x"));

        String s1 = "f(x) = plus(div(1.0x^1+1.0,2.0),x^3)";
        function f1 = cf.initFromString(s1);
        ComplexFunction cf1 = (ComplexFunction) f1;
        assert (Math.abs(cf1.f(5) - 128) <= 0.001);
        ComplexFunction cf2 = new ComplexFunction("comp", f1, new Polynom("-3x^2"));
        assert (Math.abs(cf2.f(0.5) + 0.297) <= 0.001);

        String s2 = "f(x) = mul(plus(4.6x^3-1.0,2.0),-x^5)";
        function f2 = cf.initFromString(s2);
        ComplexFunction cf3 = (ComplexFunction) f2;
        String s3 = "f(x) = mul(-x^5,plus(2.0,4.6x^3-1.0))";
        function f3 = cf.initFromString(s3);
        ComplexFunction cf4 = (ComplexFunction) f3;
        assert (Math.abs(cf3.f(7.8) - cf4.f(7.8)) <= 0.001);
    }

    @Test
    public void initFromString() {


        ComplexFunction a = new ComplexFunction(new Polynom("x"));

        String s1 = "f(x) = plus(div(1.0x^1+1.0,2.0),x^3)";
        function f1 = a.initFromString(s1);
        ComplexFunction cf1 = (ComplexFunction) f1;
        ComplexFunction cf2 = new ComplexFunction("plus", new ComplexFunction("div", new Polynom("1.0x^1+1.0"), new Polynom("2.0")), new Polynom("1.0x^3"));
        assert (cf1.equals(cf2,1,2));

        String s2 = "-3x^2-x^3-4+3";
        function f2 = a.initFromString(s2);
        Polynom cf3 = (Polynom) f2;
        ComplexFunction cf4 = new ComplexFunction(new Polynom("-3x^2-x^3-4+3"));
        assert (cf4.equals(cf3,1,2));
        function p1 = new Polynom("-3x^2-x^3-4+3");
        assert (cf4.equals(p1,1,2));

        String s3 = "f(x)= div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)";
        function f3 = a.initFromString(s3);
        ComplexFunction cf5 = (ComplexFunction) f3;
        String s4 = "div(plus(-1.0x^4+2.4x^2+3.1,0.1x^5-1.2999999999999998x^1+5.0),-1.0x^4+2.4x^2+3.1)";
        assert (cf5.toString().equals(s4));

    }

    @Test
    public void copy() {
        ComplexFunction cf = new ComplexFunction(new Polynom("x"));

        String s1 = "f(x) = plus(div(1.0x^1+1.0,2.0),x^3)";
        function f1 = cf.initFromString(s1);
        ComplexFunction cf1 = (ComplexFunction) f1;
        function f2 = f1.copy();
        ComplexFunction cf2 = (ComplexFunction) f2;
        assert (cf1.toString().equals(cf2.toString()));
        assert(!(cf1==cf2));

        String s2 = "f(x) = -x";
        function f3 = cf.initFromString(s2);
        function f4 = f3.copy();
        assert (f3.toString().equals(f4.toString()));
        assert(!(f3==f4));

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

        ComplexFunction cf = new ComplexFunction(new Polynom("x"));

        String s2 = "f(x) = mul(plus(4.6x^3-1.0,2.0),-x^5)";
        function f2 = cf.initFromString(s2);
        ComplexFunction cf3 = (ComplexFunction) f2;
        String s3 = "f(x) = mul(-x^5,plus(2.0,4.6x^3-1.0))";
        function f3 = cf.initFromString(s3);
        ComplexFunction cf4 = (ComplexFunction) f3;
        assert (cf3.equals(cf4,1,4));
    }
}