package myMath;

public class ComplexFunction implements cont_function {
    private function right, left;

    public ComplexFunction(String div, function cf, function cf3) {
    }

    public ComplexFunction(function p3) {
        this.right = p3;
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
}
