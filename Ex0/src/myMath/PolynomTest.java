
package myMath;

public class PolynomTest {
	public static void main(String[] args) {
		//test1();
		//test2();
		test3();
	}
	public static void test1() {
		Polynom_able p1 = new Polynom();
		String[] monoms = {"2x^3","1","x^2", "0.5x^2"};//I change the insertion order to check if the sorting algorithm works
		for (int i = 0; i < monoms.length; i++)
		{
			Monom m = new Monom(monoms[i]);
			p1.add(m);
			System.out.println(p1);
		}
		System.out.println(p1.derivative());
		System.out.println(p1.f(2.0));
		System.out.println(p1.area(0, 1, 0.00001));
		
		Polynom_able p3 = p1.derivative();
		p1.substract(p3);
		System.out.println(p1);
		System.out.println(p1.copy());
		String d = "x";
		Monom m1 = new Monom(d);
		p1.multiply(m1);
		System.out.println(p1);
		p1.multiply(new Monom("0"));
		System.out.println(p1);
		
		String s = "2x^3+1+x^2+0.5x^2";
		String s1 = "x+1";
		Polynom_able p2 = new Polynom(s);
		Polynom_able p5 = new Polynom(s1);
		p5.multiply(p5);
		System.out.println(p5);
		p1.add(p2);
		System.out.println(p1);
		
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
	
	public static void test3() {
		/*String s = "x^3+1-2x^2";
		String s1 = "x^3+1-2x^2";
		Polynom_able p1 = new Polynom(s);
		Polynom_able p5 = new Polynom(s1);
		System.out.println(p1);
		System.out.println(p1.root(0, 1.3, 0.1));
		System.out.println(p1.root(0, 1.3, 0.0001));
		System.out.println(p1.equals(p5));
		
		String s2 = "x^3+1-2x^2";
		String s3 = "x^3-1-2x^2";
		
		Polynom_able p2 = new Polynom(s2);
		Polynom_able p3 = new Polynom(s3);
		System.out.println(p2.equals(p3));
		*/
		String d = "-x-0";
		Polynom_able p = new Polynom(d);
		System.out.println(p);
		
		
	}
}
