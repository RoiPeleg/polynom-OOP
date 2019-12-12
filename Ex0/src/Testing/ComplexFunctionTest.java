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

    public static void main(String[] args)
    {
        f();
        initFromString();
        copy();
        max();
        min();
        plus();
        mul();
        div();
        comp();
        testEquals();

    }

    @Test
    static public void f() {
        ComplexFunction cf = new ComplexFunction(new Polynom("x"));

        String s1 = "f(x) = plus(div(1.0x^1+1.0,2.0),x^3)";
        function f1 = cf.initFromString(s1);
        ComplexFunction cf1 = (ComplexFunction) f1;
        assert (Math.abs(cf1.f(5) - 128) <= 0.001); //expected value: 128
        ComplexFunction cf2 = new ComplexFunction("comp", f1, new Polynom("-3x^2"));
        assert (Math.abs(cf2.f(0.5) + 0.297) <= 0.001); //expected value: -0.297

        String s2 = "f(x) = mul(plus(4.6x^3-1.0,2.0),-x^5)";
        function f2 = cf.initFromString(s2);
        ComplexFunction cf3 = (ComplexFunction) f2;
        String s3 = "f(x) = mul(-x^5,plus(2.0,4.6x^3-1.0))";
        function f3 = cf.initFromString(s3);
        ComplexFunction cf4 = (ComplexFunction) f3;
        assert (Math.abs(cf3.f(7.8) - cf4.f(7.8)) <= 0.001);


    }

    @Test
    static public void initFromString() {


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

        String s5 = "f(x) = -0-0";
        function f5 = a.initFromString(s5);
        System.out.println(f5);
        assert (f5.equals(new Polynom("0")));

    }

    @Test
    static public void copy() {
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
    static public void max() {
        ComplexFunction cf = new ComplexFunction(new Polynom("x"));

        String s1 = "f(x) = max(max(6.0x^2+1.0,2.0),x^3)";
        function f1 = cf.initFromString(s1);
        ComplexFunction cf1 = (ComplexFunction) f1;
        assert (Math.abs(cf1.f(5))-151 <= 0.001); //expected value: 151
        function f2 = new ComplexFunction("comp", f1, new Polynom("-3x^5"));
        ComplexFunction cf2 = new ComplexFunction("max", f1, f2);
        assert (Math.abs(cf2.f(3) -3188647 ) <= 0.001); //expected value: 3188647
        assert (Math.abs(cf2.f(0.25) -2 ) <= 0.001); //expected value: 2
        String s2 = "f(x) = max(max(-6.0x^2+1.0,-2.0x^4),-x^3+4)";
        function f3 = cf.initFromString(s2);
        ComplexFunction cf3 = (ComplexFunction) f3;
        assert (Math.abs(cf3.f(-2))-12 <= 0.001); //expected value: 12
    }

    @Test
    static public void min() {
        ComplexFunction cf = new ComplexFunction(new Polynom("x"));

        String s1 = "f(x) = min(min(6.0x^2+1.0,x^5),7x^3)";
        function f1 = cf.initFromString(s1);
        ComplexFunction cf1 = (ComplexFunction) f1;
        assert (Math.abs(cf1.f(1))-1 <= 0.001); //expected value: 1
        function f2 = new ComplexFunction("comp", f1, new Polynom("-3x^5"));
        ComplexFunction cf2 = new ComplexFunction("min", f1, f2);
        assert (Math.abs(cf2.f(0.8) +6.64984632479 ) <= 0.001); //expected value: -6.64984632479
        assert (Math.abs(cf2.f(0.25) -0 ) <= 0.001); //expected value: 0
        String s2 = "f(x) = min(min(-6.0x^2+1.0,-2.0x^4),-x^3+4)";
        function f3 = cf.initFromString(s2);
        ComplexFunction cf3 = (ComplexFunction) f3;
        assert (Math.abs(cf3.f(0.5)+0.5) <= 0.001); //expected value: -0.5
    }

    @Test
    static public void plus() {
        ComplexFunction cf = new ComplexFunction(new Polynom("x"));
        String s1 = "f(x) = plus(div(1.0x^1+1.0,2.0),x^3)";
        function f1 = cf.initFromString(s1);
        ComplexFunction cf1 = (ComplexFunction) f1;
        assert (Math.abs(cf1.f(5) - 128) <= 0.001); //expected value: 128
        ComplexFunction cf2 = new ComplexFunction("comp", f1, new Polynom("-3x^2"));
        assert (Math.abs(cf2.f(0.5) + 0.297) <= 0.001); //expected value: -0.297

        String s2 = "f(x) = plus(plus(x^3,x^2),x^1)";
        function f2 = cf.initFromString(s2);
        ComplexFunction cf3 = (ComplexFunction) f2;
        assert (Math.abs(cf3.f(3) - 39) <= 0.001); //expected value: 39
        assert (Math.abs(cf3.f(-2) +6) <= 0.001); //expected value: -6

        String s3 = "f(x) = plus(-x,plus(0-x^2,plus(plus(plus(x^3,x^2),x^1),-x^3)))";
        function f3 = cf.initFromString(s3);
        ComplexFunction cf4 = (ComplexFunction) f3;
        assert (Math.abs(cf4.f(3) - 0) <= 0.001); //expected value: 0
        assert (Math.abs(cf4.f(-2) -0) <= 0.001); //expected value: 0

    }

    @Test
    static public void mul() {
        ComplexFunction cf = new ComplexFunction(new Polynom("x"));
        String s1 = "f(x) = mul(mul(1.4x^2-1.0-1+7,2.0),x^3)";
        function f1 = cf.initFromString(s1);
        ComplexFunction cf1 = (ComplexFunction) f1;
        assert (Math.abs(cf1.f(2) - 169.6) <= 0.001); //expected value: 169.6
        assert (Math.abs(cf1.f(-1.4) + 42.499072) <= 0.001); //expected value: −42.499072

        String s2 = "f(x) = mul(mul(mul(x,x),x),x)";
        function f2 = cf.initFromString(s2);
        ComplexFunction cf2 = (ComplexFunction) f2;
        assert (Math.abs(cf2.f(2) - 16) <= 0.001); //expected value: 8

        ComplexFunction cf3 = new ComplexFunction("mul", f2, new Polynom("x+1-1-x"));
        assert (Math.abs(cf3.f(2) - 0) <= 0.001); //expected value: 0
        assert (Math.abs(cf3.f(100) - 0) <= 0.001); //expected value: 0


    }

    @Test
    static public void div() {
        ComplexFunction cf = new ComplexFunction(new Polynom("x"));
        String s1 = "f(x) = div(div(1.4x^2-1.0-1+7,2.0),x^3)";
        function f1 = cf.initFromString(s1);
        ComplexFunction cf1 = (ComplexFunction) f1;
        assert (Math.abs(cf1.f(2) - 0.6625) <= 0.001); //expected value: 0.6625
        assert (Math.abs(cf1.f(-1.4) + 1.4110787172) <= 0.001); //expected value: −1.4110787172

        String s2 = "f(x) = div(div(div(x^5,x),x),x)";
        function f2 = cf.initFromString(s2);
        ComplexFunction cf2 = (ComplexFunction) f2;
        ComplexFunction cf3 = new ComplexFunction(new Polynom("x^2"));
        assert (cf2.equals(cf3,4,7));

    }

    @Test
     static public void comp() {

        ComplexFunction cf = new ComplexFunction(new Polynom("x"));
        String s1 = "f(x) = comp(comp(1.4x^2-1.0-1+7,-2.0x),9x^3)";
        function f1 = cf.initFromString(s1);
        ComplexFunction cf1 = (ComplexFunction) f1;
        assert (Math.abs(cf1.f(0) - 5) <= 0.001); //expected value: 5
        assert (Math.abs(cf1.f(3) - 330679.4) <= 0.001); //expected value: 330679.4

        String s2 = "f(x) = comp(comp(comp(x^1,x),x),x)";
        function f2 = cf.initFromString(s2);
        ComplexFunction cf2 = (ComplexFunction) f2;
        ComplexFunction cf3 = new ComplexFunction(new Polynom("-x+2x"));
        assert (cf2.equals(cf3,4,7));


    }

    @Test
    static public void testEquals() {

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