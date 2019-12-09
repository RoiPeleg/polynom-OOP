package myMath;

public class ComplexFunction implements cont_function {
    private function right, left;
    private Operation op;

    public ComplexFunction(String opertion, function cf, function cf2) {
        this.left = cf;
        this.right = cf2;
        switch (opertion) {
            case "plus":
                op = Operation.Plus;
            case "div":
                op = Operation.Divid;
            case "mul":
                op = Operation.Times;
            case "comp":
                op = Operation.Comp;
            case "max":
                op = Operation.Max;
            case "min":
                op = Operation.Min;
            case "none":
                op = Operation.None;
            case "error":
                op = Operation.Error;
            default:
                throw new RuntimeException("undfiened opertion");
        }
    }

    public ComplexFunction(function p3) {
        this.left = p3;
        this.op = Operation.None;
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
        switch (op) {
            case Plus:
                return left.f(x) + right.f(x);
            case Divid:
                return left.f(x) / right.f(x);
            case Times:
                return left.f(x) * right.f(x);
            case Comp:
                return 0;//TODO - COMPSTION
            case Max:
                return Math.max(left.f(x), right.f(x));
            case Min:
                return Math.min(left.f(x), right.f(x));
            case None:
                return left.f(x);
        }
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
        function f2 = this.copy();
        this.left = f2;
        this.right = f;
        this.op = Operation.Max;
    }

    public void min(function f) {
        function f2 = this.copy();
        this.left = f2;
        this.right = f;
        this.op = Operation.Min;
    }

    public void plus(function f) {
        function f2 = this.copy();
        this.left = f2;
        this.right = f;
        this.op = Operation.Plus;
    }

    public void mul(function f) {
        function f2 = this.copy();
        this.left = f2;
        this.right = f;
        this.op = Operation.Times;
    }

    public void div(function f) {
        function f2 = this.copy();
        this.left = f2;
        this.right = f;
        this.op = Operation.Divid;
    }

    public void comp(function f) {
        function f2 = this.copy();
        this.left = f2;
        this.right = f;
        this.op = Operation.Comp;
    }
}
