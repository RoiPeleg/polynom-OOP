package myMath;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

import javax.management.RuntimeErrorException;

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
		if (p1 == null || isEmpty()) throw new RuntimeException("can't compute null");
		if(p1 instanceof Polynom)
		{
			if(ls.isEmpty())
				throw new RuntimeException("list empty");
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

	public boolean isEmpty()
	{
		return ls.size()==0;
	}

	@Override
    public String toString() {
        String str = "";
        for (int i = 0; i < ls.size() - 1; i++) {
            str += ls.get(i).toString() + "+";
        }
        str += ls.get(ls.size() - 1).toString();
        return str;
    }

    /**
     * substracts a polynom from current one
     *
     * @param p1 - polynom to be substracted
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
        } else {
            throw new RuntimeException("not polynom instance");
        }
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
			temp2 = temp3.copy();
		}
	}

	/**
	 *
	 * @param p1 - polynom
	 * @return if this polynom equals p1
	 */
	@Override
	public boolean equals(Polynom_able p1) {
		Iterator<Monom> it = p1.iteretor();
		for (Monom monom : ls) {
			if (!it.hasNext()) return false;
			if (!monom.equals(it.next())) return false;
		}
		return true;
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
        // TODO Auto-generated method stub
        return 0;
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
        // TODO Auto-generated method stub
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
	}
}
