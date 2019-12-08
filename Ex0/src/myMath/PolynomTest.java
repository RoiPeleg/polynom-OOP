
package myMath;

public class PolynomTest {
	public static void main(String[] args) {
		//test1();
		//test2();
		//test3();
		//athmeticTest();
		test5();
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
		String s = "x^3+1-2x^2";
		String s1 = "x+1";
		Polynom_able p1 = new Polynom(s);
		Polynom_able p5 = new Polynom(s1);
		System.out.println(p1);
		System.out.println(p1.root(1, -0.5, 0.1));
		System.out.println(p1.root(1, -0.5, 0.0001));
	}

	public static void athmeticTest() {
		String s = "x^3+1-2x^2";
		String s1 = "1-x";
		Polynom_able p1 = new Polynom(s);
		Polynom_able p5 = new Polynom(s1);
		System.out.println(p1);
		System.out.println(p5);
		Polynom_able n = null;
		try {
			p1.add(n);
		} catch (Exception e) {
			System.out.println("Null pass add");
		}
		try {
			p1.multiply(n);
		} catch (Exception e) {
			System.out.println("Null pass multiply");
		}
		try {
			p1.substract(n);
		} catch (Exception e) {
			System.out.println("Null pass substract");
		}
		p1.multiply(p5);
		System.out.println(p1);
		p1 = new Polynom(s);
		p1.substract(p5);
		System.out.println(p1);
		p1 = new Polynom(s);
		p1.add(p5);
		System.out.println(p1);
	}
	
	public static void test5() {
		String s1 = "-x^3+1-2x^2-3";
		String s2 = "x^3+1";
		String s3 = "0";
		Polynom_able p1 = new Polynom(s1);
		Polynom_able p2 = new Polynom(s2);
		Polynom_able p3 = new Polynom(s3);
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		s1 = "-1x^3+1-2x^2-3";
		p1 = new Polynom(s1);
		System.out.println(p1); //-1.0x^3-2.0x^2-2.0
		//s2 = "x^3++1"; //invalid input
		//p2 = new Polynom(s2);
		//System.out.println(p2);	
		s3 = "0-0";
		p3 = new Polynom(s3);
		System.out.println(p3);//0
		s3 = "-0-0";
		p3 = new Polynom(s3);
		System.out.println(p3); //0
		s3 = "-0-x";
		p3 = new Polynom(s3);
		System.out.println(p3); //-1.0x^1
		s3 = "-x-x";
		p3 = new Polynom(s3);
		System.out.println(p3); //-2.0x^1
		s3 = "-x-x+2x";
		p3 = new Polynom(s3);
		System.out.println(p3);
		s3 = "-2x^3-x-x+2x-x+x^3"; //-1.0x^3-1.0x^1
		p3 = new Polynom(s3);
		System.out.println(p3);
		s3 = "-x^3-x-x+2x-x+x^3"; //-1.0x^3-1.0x^1
		p3 = new Polynom(s3);
		System.out.println(p3);
		
		/****************root testing******************/
		System.out.println("//****************root testing******************//");
		
		
		System.out.println(p1.root(-2.5, -2, 0.0001));
		s1 = "-x^4+1-2x^2-3x^3";
		p1 = new Polynom(s1);
		System.out.println(p1);
		System.out.println(p1.root(-2.5, -2, 0.0001));
		System.out.println(p1.root(0, 1, 0.0001));
		System.out.println(p1.root(0, 1, 0.0001));
		System.out.println(p1.root(0, 1, 0.0001));
		s1 = "0";
		p1 = new Polynom(s1);
		System.out.println(p1.root(-1, 1, 0.0001));
		s1 = "x-1";
		p1 = new Polynom(s1);
		System.out.println(p1.root(0, 1, 0.0001));
		
		/****************equal testing******************/
		System.out.println("//****************equal testing******************//");
	
		s1 = "-x^3+1-2x^2-3";
		p1 = new Polynom(s1);
		s2 = "x^3+1";
		p2 = new Polynom(s2);
		System.out.println(p1.equals(p1));
		System.out.println(p1.equals(p2));
		s2 = "-x^3+1-2x^2-3";
		p2 = new Polynom(s2);
		System.out.println(p1.equals(p2));
		s1 = "-x";
		p1 = new Polynom(s1);
		s2 = "-x+1";
		p2 = new Polynom(s2);
		System.out.println(p1.equals(p2));
		System.out.println(p2.equals(p3));
		s2 = "-2x^2";
		p2 = new Polynom(s2);
		System.out.println(p1.equals(p2));
		System.out.println(p2.equals(p3)); 
		s2 = "-2";
		p2 = new Polynom(s2);
		System.out.println(p1.equals(p2));
		System.out.println(p2.equals(p3));
		
		/****************adding testing******************/
		System.out.println("//****************adding testing******************//");
		
		s1 = "-x^3+1-2x^2-3";
		p1 = new Polynom(s1);
		s2 = "x^3+1";
		p2 = new Polynom(s2);
		System.out.println(p1);
		System.out.println(p2);
		p1.add(p2);
		System.out.println(p1);
		System.out.println(p2);
		p2.add(p2);
		System.out.println(p2);
		s1 = "-0";
		p1 = new Polynom(s1);
		s2 = "0";
		p2 = new Polynom(s2);
		p1.add(p2);
		System.out.println(p1);
		s1 = "x-x^2";
		p1 = new Polynom(s1);
		s2 = "x^2-x";
		p2 = new Polynom(s2);
		p1.add(p2);
		System.out.println(p1);
		
		s1 = "0";
		p1 = new Polynom(s1);
		s2 = "x^3+1";
		p2 = new Polynom(s2);
		
		p1.add(p2);
		System.out.println(p1);
		
		s1 = "0";
		p1 = new Polynom(s1);
		p2.add(p1);
		System.out.println(p2);

		/****************subtracting testing******************/
		System.out.println("//****************subtracting testing******************//");
		
		s1 = "-x^3+1-2x^2-3";
		p1 = new Polynom(s1);
		s2 = "x^3+1";
		p2 = new Polynom(s2);
		System.out.println(p1);
		System.out.println(p2);
		
		p1.substract(p2);
		System.out.println(p1);
		p2.substract(p2);
		System.out.println(p2);
		s1 = "-0";
		p1 = new Polynom(s1);
		s2 = "0";
		p2 = new Polynom(s2);
		//p1.substract(p2);
		System.out.println(p1);
		s1 = "x-x^2";
		p1 = new Polynom(s1);
		s2 = "x^2-x";
		p2 = new Polynom(s2);
		p1.substract(p2);
		System.out.println(p1);
		
		s1 = "0";
		p1 = new Polynom(s1);
		s2 = "x^3+1";
		p2 = new Polynom(s2);
		
		p1.substract(p2);
		System.out.println(p1);
		
		s1 = "0";
		p1 = new Polynom(s1);
		p2.substract(p1);
		System.out.println(p2);
		
		/****************multiply testing******************/
		System.out.println("//****************multiply testing******************//");
		
		s1 = "-x^3+1-2x^2-3";
		p1 = new Polynom(s1);
		s2 = "-x^3+1";
		p2 = new Polynom(s2);
		p1.multiply(p2);
		System.out.println(p1);
		
		s1 = "-x^3+1-2x^2-3";
		p1 = new Polynom(s1);
		s2 = "-1-1";
		p2 = new Polynom(s2);
		
		p1.multiply(p2);
		System.out.println(p1);
		p1.multiply(p1);
		System.out.println(p1);
		
		s2 = "0";
		p2 = new Polynom(s2);
		p1.multiply(p2);
		System.out.println(p1);
		
		p1.multiply(p2);
		System.out.println(p1);
		
		/****************derivative testing******************/
		System.out.println("//****************derivative testing******************//");
		
		s1 = "-x^3+1-2x^2-3";
		p1 = new Polynom(s1);
		System.out.println(p1.derivative());
		System.out.println(p1.derivative().derivative());
		System.out.println(p1.derivative().derivative().derivative());
		System.out.println(p1.derivative().derivative().derivative().derivative());
		System.out.println(p1.derivative().derivative().derivative().derivative().derivative());
		
	}
}
