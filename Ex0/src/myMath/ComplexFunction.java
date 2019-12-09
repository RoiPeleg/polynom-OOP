package myMath;

public class ComplexFunction implements cont_function {
    private function right, left;
    private Operation op;

    public ComplexFunction(String div, function cf, function cf2) {
        this.left = cf;
        this.right = cf2;
    }

    public ComplexFunction(function p3) {
        this.left = p3;
    }

    @Override
    public double area(double x0, double x1, double eps) {
        return 0;
    }

    @Override
    public double root(double x0, double x1, double eps) {
        return 0;
    }

    @Override
    public double f(double x) {
        return 0;
    }

    @Override
    public function initFromString(String s) {
        return null;
    }

    @Override
    public function copy() {
        return null;
    }

    public void max(function f) {

    }

    public void min(function f) {

    }

    public void plus(function f) {

    }

    public void mul(function f) {

    }

    public void div(function f) {

    }

    public void comp(function f) {

    }
}
