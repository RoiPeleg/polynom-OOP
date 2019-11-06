package myMath;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

import myMath.Monom;

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
		s.toLowerCase();
		Monom mm;
		String[] monos = s.split("\\+|\\-");
		String signs = s.replaceAll("[0-9]", "").replaceAll("\\^", "").replaceAll("x", "").replaceAll(".", "");
		for (int i = 0; i < monos.length; i++) 
		{
			mm = new Monom(monos[i]);
			ls1.add(mm);
		}
		int i = 0;
		if (s.charAt(0) != '-') i++;
		for (i = 0; i < signs.length(); i++) {
			if (s.charAt(i) == '-') {
				ls1.get(i).multipy(new Monom("-1"));
			}
		}
		for(i=0;i<ls1.size();i++)
		{
			add(ls1.get(i));
		}
	}


	@Override
	public double f(double x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void add(Polynom_able p1) {

	}

	/**
	 * adds a monom to the polynom
	 *
	 * @param m1: is a monom to add
	 */
	@Override
	public void add(Monom m1) { //adding and sorting
		{
			if(ls.isEmpty())
				ls.add(m1);
			else
			{
				int c = 0;
				for(int i = 0;i<ls.size() && ls.get(i).get_power()<m1.get_power();i++)
					c++;
				if(c==ls.size())
					ls.add(m1);
				else
				{
					if(ls.get(c).get_power()==m1.get_power())
						ls.get(c).add(m1);	
					else
						ls.add(c, m1);
				}
			}
		}
	}
	@Override
	public String toString()
	{
		String str = "";
		for(int i=0;i<ls.size()-1;i++)
		{ 
			str+=ls.get(i).toString()+"+";
		}
		str+=ls.get(ls.size()-1).toString();
		return str;
	}

	@Override
	public void substract(Polynom_able p1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void multiply(Polynom_able p1) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean equals(Polynom_able p1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isZero() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double root(double x0, double x1, double eps) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Polynom_able copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Polynom_able derivative() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double area(double x0, double x1, double eps) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<Monom> iteretor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void multiply(Monom m1) {
		// TODO Auto-generated method stub

	}

}
