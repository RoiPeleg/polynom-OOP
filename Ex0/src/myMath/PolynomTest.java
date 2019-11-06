package myMath;

public class PolynomTest {
	public static void main(String[] args) {
		test1();
		test2();
	}
	public static void test1() {
		Polynom p1 = new Polynom();
		String[] monoms = {"x","1","x^2", "0.5x^2"};//I change the insertion order to check if the sorting algorithm works
		for (int i = 0; i < monoms.length; i++)
		{
			Monom m = new Monom(monoms[i]);
			p1.add(m);
			double aa = p1.area(0, 1, 0.0001);
			p1.substract(p1);
			System.out.println(p1);
		}
	}
	public static void test2() {
		Polynom p1 = new Polynom(), p2 =  new Polynom();
		String[] monoms1 = {"2", "-x","-3.2x^2","4","-1.5x^2"};
		String[] monoms2 = {"5", "1.7x","3.2x^2","-3","-1.5x^2"};
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		System.out.println("p1: "+p1);
		System.out.println("p2: "+p2);
		p1.add(p2);
		System.out.println("p1+p2: "+p1);
		p1.multiply(p2);
		System.out.println("(p1+p2)*p2: "+p1);
		String s1 = p1.toString();
		//Polynom_able pp1 = Polynom.parse(s1);
		Polynom pp1 = new Polynom(s1);
		System.out.println("from string: "+pp1);
	}
}
