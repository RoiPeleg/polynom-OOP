package myMath;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 *
 * @author Boaz
 */
public class Polynom implements Polynom_able {
	private ArrayList<Monom> ls;

	/**
	 * Zero (empty polynom)
	 */
	public Polynom() {
		ls = new ArrayList<Monom>();
	}

	/**
	 * init a Polynom from a String such as:
	 * {"x", "3+1.4X^3-34x", "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 *
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) {
		this();
		ArrayList<Monom> ls1 = new ArrayList<Monom>();
		boolean firstC = false;
		Monom neg = new Monom("-1");
		s = s.replaceAll(" ", "");
		s.toLowerCase();
		if(s.length()==0)
			throw new RuntimeException("not valid Polynom");

		if (s.charAt(0) == '+') 
		{
			s = s.substring(1);	
		}

		if (s.charAt(0) != '-')
		{
			firstC = true;
		}
		Monom mm;
		String signs = s.replaceAll("[0-9]", "").replaceAll("\\^", "").replaceAll("x", "").replaceAll("\\.", "");
		if (s.charAt(0) == '-') s = s.substring(1);
		String[] monos = s.split("\\+|\\-");
		for (int i = 0; i < monos.length; i++)
		{
			mm = new Monom(monos[i]);
			ls.add(mm);
		}
		if (firstC) {
			signs = "+" + signs;
		}
		for (int i = 0; i < signs.length(); i++) {
			if (signs.charAt(i) == '-') {
				ls.get(i).multipy(neg);
			}
		}
		ls.sort(Monom.getComp());
		this.unify();
	}

	/**
	 * unifies all monos in lists
	 */
	private void unify() {
		for (int i = 0; i < ls.size() - 1; i++) {
			if (ls.get(i).get_power() == ls.get(i + 1).get_power()) {
				ls.get(i).add(ls.get(i + 1));
				Monom flip = ls.get(i + 1).flip();
				ls.get(i + 1).add(flip);
			}
		}
		eraseZeros();
	}
	private void eraseZeros()
	{
		for(int i = 0;i<ls.size();i++)
		{
			if(ls.get(i).get_coefficient()==0.0)
			{
				ls.remove(i);
				i--;
			}

		}
	}



	@Override
	public double f(double x) {
		if(this.isEmpty())
			return 0;
		double sum = 0;
		for (Monom monom : ls) {
			sum+=Math.pow(x, monom.get_power())*monom.get_coefficient();
		}
		return sum;
	}
	/**
	 * adds a polynom to the polynom
	 *
	 * @param p1: is a polynom to add
	 */
	@Override
	public void add(Polynom_able p1) {
		if (p1 == null) throw new RuntimeException("can't compute null");
		if(p1 instanceof Polynom)
		{
			Iterator<Monom> it = p1.iteretor();
			while (it.hasNext()) {
				Monom monom = (Monom) it.next();
				this.add(monom);			
			}
		}
		else
		{
			throw new RuntimeException("not polynom instance");
		}
		this.eraseZeros();
		this.unify();
	}

	/**
	 * adds a moonom to current ploynom
	 *
	 * @param m1 Monom - new monom to add
	 */
	@Override
	public void add(Monom m1) { //adding and sorting
		{
			if (m1 == null) throw new RuntimeException("can't compute null");
			if(ls.isEmpty())
				ls.add(m1);
			else
			{
				ls.add(m1);
				this.eraseZeros();
			}
		}
		ls.sort(Monom.getComp());
		this.unify();
	}
	public boolean isEmpty()
	{
		return ls.size()==0;
	}

	@Override
	public String toString() {
		String str = "";
		if(ls.isEmpty()) { return "0"; }
		for (int i = 0; i < ls.size() - 1; i++) {
			str += ls.get(i).toString();
			if (ls.get(i + 1).get_coefficient() > 0) {
				str += "+";
			}
		}
		str += ls.get(ls.size() - 1).toString();
		return str;
	}

	@Override
	public function initFromString(String s) {
		function p1 = new Polynom(s);
		return p1;
	}

	/**
	 * subtracts a polynom from current one
	 *
	 * @param p1 - polynom to be subtracted
	 */
	@Override
	public void substract(Polynom_able p1) {
		if (p1 == null || isEmpty()) throw new RuntimeException("can't compute null");
		if (p1 instanceof Polynom) {
			Iterator<Monom> it = p1.iteretor();
			while (it.hasNext()) {
				Monom monom = (Monom) it.next();
				this.add(monom.flip());	
			}
			this.eraseZeros();
		} else {
			throw new RuntimeException("not polynom instance");
		}
		this.unify();
	}

	/**
	 * @param p1 - polyom to multply
	 */
	@Override
	public void multiply(Polynom_able p1) {
		if (p1 == null || isEmpty()) throw new RuntimeException("can't compute null");
		if(this == p1) p1 = this.copy();
		Polynom_able temp2 = this.copy(),temp3 = this.copy();
		this.substract(temp2);
		Iterator<Monom> p1It = p1.iteretor();
		while (p1It.hasNext())
		{
			temp2.multiply(p1It.next());
			this.add(temp2);
			temp2 = (Polynom_able) temp3.copy();
		}
		this.eraseZeros();
		ls.sort(Monom.getComp());
		this.unify();
	}

	/**
	 *
	 * @param p1 - polynom
	 * @return if this polynom equals p1
	 */
	@Override
	public boolean equals(Object p1) {
		if (p1 instanceof Polynom_able) {
			Polynom_able p = (Polynom_able) p1;
			Iterator<Monom> it = p.iteretor();
			for (Monom monom : ls) {
				if (!it.hasNext()) return false;
				if (!monom.equals(it.next())) return false;
			}
			return true;
		}
		return false;
	}

	/**
	 *
	 * @return true if this polynom is a zero polynom
	 */
	@Override
	public boolean isZero() {
		if (this.equals(new Polynom())) return true;
		return false;
	}

	@Override
	public double root(double x0, double x1, double eps) {
		if (f(x0) * f(x1) > 0) throw new RuntimeException("invalid input");
		double x = x0 + x1 / 2;
		double l = x0, r = x1;
		if(f(x0)<=f(x1))
		{
			while (r >= l) {
				x = l + (r - l) / 2;
				if (Math.abs(this.f(x)) < eps)
					return x;
				if ((this.f(x)) > 0)
					r=x;
				else
					l=x;
			}
		}
		else
		{
			while (r >= l) {
				x = l + (r - l) / 2;
				if (Math.abs(this.f(x)) < eps)
					return x;
				if ((this.f(x)) < 0)
					l=x;
				else
					r=x;
			}
		}
		return -1;

	}

	/**
	 * returns new copy of this polynom
	 *
	 * @return
	 */
	@Override
	public Polynom_able copy() {
		if (this.isEmpty())
			return null;
		Polynom_able p = new Polynom();
		for (Monom monom : ls) {
			p.add(new Monom(monom));
		}
		return p;
	}

	/**
	 * return a Polynom that represnts the current polynom derivative
	 *
	 * @return Polynom_able
	 */
	@Override
	public Polynom_able derivative() {
		if (this.isEmpty())
			return null;
		Polynom_able p = new Polynom();
		for (Monom monom : ls) {
			p.add(monom.derive());
		}
		return p;
	}

	/**
	 * @param x0  starting pooint
	 * @param x1  end point
	 * @param eps positive step value
	 * @return
	 */
	@Override
	public double area(double x0, double x1, double eps) {
		double sum = 0;
		for (double i = x0; i < x1; i += eps) {
			sum += this.f(i) * eps;
		}
		return sum;
	}

	/**
	 * returns the list's iterator
	 *
	 * @return Iterator<Monom>
	 */
	@Override
	public Iterator<Monom> iteretor() {
		Iterator<Monom> m = ls.iterator();
		return m;
	}

	/**
	 * multplies my in this polynom
	 * @param m1 - monom to be multiplied
	 */
	@Override
	public void multiply(Monom m1) {
		if (m1 == null || isEmpty()) throw new RuntimeException("can't compute null");
		for (Monom m : ls) {
			m.multipy(m1);
		}
		this.eraseZeros();
	}
}
