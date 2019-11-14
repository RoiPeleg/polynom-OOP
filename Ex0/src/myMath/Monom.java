package myMath;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative),
 * see: https://en.wikipedia.org/wiki/Monomial
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply.
 *
 * @author Boaz
 */
public class Monom implements function {
	public static final Monom ZERO = new Monom(0, 0);
	public static final Monom MINUS1 = new Monom(-1, 0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();

	public static Comparator<Monom> getComp() {
		return _Comp;
	}

	public Monom(double a, int b) {
		this.set_coefficient(a);
		this.set_power(b);
	}

	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	public double get_coefficient() {
		return this._coefficient;
	}

	public int get_power() {
		return this._power;
	}

	/**
	 * this method returns the derivative monom of this.
	 *
	 * @return
	 */
	public Monom derivative() {
		if (this.get_power() == 0) {
			return getNewZeroMonom();
		}
		return new Monom(this.get_coefficient() * this.get_power(), this.get_power() - 1);
	}

	public double f(double x) {
		double ans = 0;
		double p = this.get_power();
		ans = this.get_coefficient() * Math.pow(x, p);
		return ans;
	}

	public boolean isZero() {
		return this.get_coefficient() == 0;
	}

	// ***************** add your code below **********************
	public Monom(String s) {
		s = s.toLowerCase();
		if (s.contains("x")) {
			if (s.contains("-") && s.length() == 2) {
				this._coefficient = -1.0;
				this._power = 1;
			} else {
				try {
					if (s.charAt(0) == 'x')
						this._coefficient = 1.0;
					else
						this._coefficient = Double.parseDouble(s.split("x")[0]);
					if (s.charAt(s.length() - 1) == 'x') {
						this._power = 1;
					} else {
						if (s.charAt(s.indexOf('x') + 1) == '^') {
							String[] s1 = s.split("\\^");
							this._power = Integer.parseInt(s1[1]);
						} else
							throw new RuntimeException("after x must be power symbol");
					}
				} catch (Exception e) {
					throw e;
				}
			}
		} else {
			try {
				this._coefficient = Double.parseDouble(s);
				this._power = 0;
			} catch (Exception e) {
				throw e;
			}
		}
	}

	public void add(Monom m) {
		if (m == null) throw new RuntimeException("can't handle null");

		if (this._power == m._power) {
			this._coefficient += m._coefficient;
		} else
			throw new RuntimeException("power not matching");
	}

	public void multipy(Monom d) {
		if (d == null) throw new RuntimeException("can't handle null");
		this._coefficient = this._coefficient * d._coefficient;
		this._power += d._power;
	}
	
	/**return the negative monom (create a new one)
	 * 
	 * @return
	 */
	public Monom flip() 
	{
		Monom m = new Monom(this) ;
		m._coefficient = -m._coefficient;
		return m;
	}

	public boolean equals(Monom d1) {
		if (Math.abs(this._power - d1.get_power()) < EPSILON) return false;
		else if (Math.abs(this._coefficient - d1.get_coefficient()) < EPSILON) {
			return false;
		}
		return true;
	}
	/**
	 * derivative of
	 */
	public Monom derive()
	{
		int p = 0;
		double c = 0;
		if(this._power==0)
			return ZERO;
		else
			p = this._power-1;
		c = this._coefficient*this._power;
		Monom m = new Monom(c,p);
		return m;
	}

	public String toString() {
		String ans = "";
		if (this._power != 0) {
			ans += this._coefficient+"x^" + this._power;
		}
		else
		{
			ans += this._coefficient;
		}
		return ans;
	}
	// you may (always) add other methods.

	//****************** Private Methods and Data *****************


	private void set_coefficient(double a) {
		this._coefficient = a;
	}

	private void set_power(int p) {
		if (p < 0) {
			throw new RuntimeException("ERR the power of Monom should not be negative, got: " + p);
		}
		this._power = p;
	}

	private static Monom getNewZeroMonom() {
		return new Monom(ZERO);
	}

	private double _coefficient;
	private int _power;
}
