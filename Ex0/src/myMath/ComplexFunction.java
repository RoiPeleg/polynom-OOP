package myMath;

public class ComplexFunction implements complex_function {
    private function right, left;
    private Operation op;

    public ComplexFunction(String opertion, function cf, function cf2) {
        this.left = cf;
        this.right = cf2;
        switch (opertion) {
            case "plus":
                op = Operation.Plus;
                break;
            case "div":
                op = Operation.Divid;
                break;
            case "mul":
                op = Operation.Times;
                break;
            case "comp":
                op = Operation.Comp;
                break;
            case "max":
                op = Operation.Max;
                break;
            case "min":
                op = Operation.Min;
                break;
            case "none":
                op = Operation.None;
                break;
            case "error":
                throw new RuntimeException("undfiened opertion");
            default:
                throw new RuntimeException("undfiened opertion");
        }
    }

    public ComplexFunction(function p3) {
        this.left = p3;
        this.op = Operation.None;
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
                return left.f(right.f(x));
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
        s = s.replaceAll(" ", "");
        if (s.substring(0, 5).compareTo("f(x)=") == 0) {
            return stringbuild(s.substring(5));
        }
        int start = s.indexOf('(');
        if (start == -1) {
            return new Polynom().initFromString(s);
        }
        String opi = s.substring(0, start);
        if (opi.equals("plus") || opi.equals("div") || opi.equals("mul") || opi.equals("comp") || opi.equals("max") || opi.equals("min") || opi.equals("none")) {
            return stringbuild(s);
        }
        throw new RuntimeException("not valid expression");
    }

    private static function stringbuild(String s) {
        if (s.contains("(")) {
            int start = s.indexOf('('), end = start, l = s.length();
            int count = 0;
            String op = s.substring(0, start);
            if (start == l - 1)
                throw new RuntimeException("not valid expression");

            if (op.toLowerCase().compareTo("none") != 0) {
                while (s.charAt(end) != ',' && end < l - 1) {
                    end++;
                    if (s.charAt(end) == '(')
                        count++;
                    while (count != 0) {
                        end++;
                        if (s.charAt(end) == '(')
                            count++;
                        if (s.charAt(end) == ')')
                            count--;
                    }
                }
                if (end == l - 1)
                    throw new RuntimeException("not valid expression");

                return new ComplexFunction(op.toLowerCase(), stringbuild(s.substring(start + 1, end)), stringbuild(s.substring(end + 1, l - 1)));
            }
            return new ComplexFunction(stringbuild(s.substring(start + 1, l - 1)));

        } else {
            Polynom_able p = new Polynom();
            return p.initFromString(s);
        }
    }

    @Override
    public function copy() {
        String s = "";
        switch (this.op) {
            case Plus:
                s = "plus";
                break;
            case Divid:
                s = "div";
                break;
            case Times:
                s = "mul";
                break;
            case Comp:
                s = "comp";
                break;
            case Max:
                s = "max";
                break;
            case Min:
                s = "min";
                break;
            case None:
                s = "none";
                break;
            default:
                throw new RuntimeException("not valid expression");
        }
        function f = new ComplexFunction(s, this.left, this.right);
        return f;
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

    @Override
    public function left() {
        return this.left;
    }

    @Override
    public function right() {
        return this.right;
    }

    @Override
    public Operation getOp() {
        return this.op;
    }

    @Override

    public String toString() {
        String s = "";
        switch (this.op) {
            case Plus:
                s = "plus";
                break;
            case Divid:
                s = "div";
                break;
            case Times:
                s = "mul";
                break;
            case Comp:
                s = "comp";
                break;
            case Max:
                s = "max";
                break;
            case Min:
                s = "min";
                break;
            case None:
                s = "none";
                break;
            default:
                break;
        }
        if (this.op == Operation.None)
            s += "(" + left.toString() + ")";
        else
            s += "(" + left.toString() + "," + right.toString() + ")";
        return s;
    }


    public boolean equals(function f, int min, int max) {
        for (double i = min; i < max; i+=000.1)
            if (this.f(i) != f.f(i)) return false;
        return true;
    }
}