package fractioncollection;
import frazione.Frazione;

public class FractionCollectionTests {

	public static void main(String[] args) {
		// testMyFractionCollectionFrazioneArray
		Frazione f1 = new Frazione(1, 2);
		Frazione f2 = new Frazione(2, 3);
		Frazione[] fa = new Frazione[] { f1, f2 };
		// act
		FractionCollection c = new FractionCollection(fa);
		// assert
		assert(2==c.size());
		assert(f1.equals(c.get(0))==true);
		assert(f2.equals(c.get(1))==true);
	
		// testMyFractionCollectionInt
		c = new FractionCollection(5);
		// assert
		assert(0==c.size());

		// testMyFractionCollection
		c = new FractionCollection();
		// assert
		assert(0==c.size());

		// testPut
		f1 = new Frazione(1, 2);
		c = new FractionCollection();
		c.put(f1);

		// assert
		assert(1==c.size());
		assert(f1.equals(c.get(0))==true);

		//testPutWithResize
		f1 = new Frazione(1, 1);
		f2 = new Frazione(1, 2);
		Frazione f3 = new Frazione(1, 3);
		Frazione f4 = new Frazione(1, 4);
		Frazione f5 = new Frazione(1, 5);
		c = new FractionCollection(3);
		c.put(f1);
		c.put(f2);
		c.put(f3);
		c.put(f4); // needs resize
		c.put(f5);
		// assert
		assert(5==c.size());
		assert(f1.equals(c.get(0))==true);
		assert(f2.equals(c.get(1))==true);
		assert(f3.equals(c.get(2))==true);
		assert(f4.equals(c.get(3))==true);
		assert(f5.equals(c.get(4))==true);
		
		//testRemove
		f1 = new Frazione(1, 1);
		f2 = new Frazione(1, 2);
		f3 = new Frazione(1, 3);
		f4 = new Frazione(1, 4);
		f5 = new Frazione(1, 5);
		c = new FractionCollection();
		c.put(f1);
		c.put(f2);
		c.put(f3);
		c.put(f4);
		c.put(f5);
		c.remove(2);

		// assert
		assert(4==c.size());
		assert(f1.equals(c.get(0))==true);
		assert(f2.equals(c.get(1))==true);
		assert(f4.equals(c.get(2))==true);
		assert(f5.equals(c.get(3))==true);
		
		//testSum
		// fail("Do it yourself");
		FractionCollection c1 = new FractionCollection();
		c1.put(new Frazione(1, 1));
		c1.put(new Frazione(1, 2));
		c1.put(new Frazione(1, 3));
		c1.put(new Frazione(1, 4));
		c1.put(new Frazione(1, 5));
		
		FractionCollection c2 = new FractionCollection();
		c2.put(new Frazione(1, 1));
		c2.put(new Frazione(1, 2));
		c2.put(new Frazione(1, 3));
		c2.put(new Frazione(1, 4));
		c2.put(new Frazione(1, 5));

		FractionCollection res = c1.sum(c2);
		
		// assert
		assert(5==res.size());
		assert(new Frazione(2).equals(res.get(0))==true);
		assert(new Frazione(1).equals(res.get(1))==true);
		assert(new Frazione(2, 3).equals(res.get(2))==true);
		assert(new Frazione(1, 2).equals(res.get(3))==true);
		assert(new Frazione(2, 5).equals(res.get(4))==true);
	
		//testSub
		FractionCollection s1 = new FractionCollection();
		s1.put(new Frazione(1, 1));
		s1.put(new Frazione(1, 2));
		s1.put(new Frazione(5, 3));
		s1.put(new Frazione(1, 4));
		s1.put(new Frazione(3, 1));
		
		FractionCollection s2 = new FractionCollection();
		s2.put(new Frazione(1, 1));
		s2.put(new Frazione(2, 2));
		s2.put(new Frazione(1, 3));
		s2.put(new Frazione(1, 4));
		s2.put(new Frazione(1, 8));

		res = s1.sub(s2);
		
		// assert
		assert(5==res.size());
		assert(new Frazione(0).equals(res.get(0))==true);
		assert(new Frazione(-1, 2).equals(res.get(1))==true);
		assert(new Frazione(4, 3).equals(res.get(2))==true);
		assert(new Frazione(0, 2).equals(res.get(3))==true);
		assert(new Frazione(23, 8).equals(res.get(4))==true);

		//testMul
		FractionCollection m1 = new FractionCollection();
		m1.put(new Frazione(1, 1));
		m1.put(new Frazione(1, 2));
		m1.put(new Frazione(2, 3));
		m1.put(new Frazione(1, 5));
		m1.put(new Frazione(0));
		
		FractionCollection m2 = new FractionCollection();
		m2.put(new Frazione(1, 1));
		m2.put(new Frazione(1, 2));
		m2.put(new Frazione(1, 3));
		m2.put(new Frazione(3, 4));
		m2.put(new Frazione(1, 5));

		res = m1.mul(m2);
		
		// assert
		assert(5==res.size());
		assert(new Frazione(1).equals(res.get(0))==true);
		assert(new Frazione(1, 4).equals(res.get(1))==true);
		assert(new Frazione(2, 9).equals(res.get(2))==true);
		assert(new Frazione(3, 20).equals(res.get(3))==true);
		assert(new Frazione(0, 4).equals(res.get(4))==true);

		//testDiv
		FractionCollection d1 = new FractionCollection();
		d1.put(new Frazione(1, 1));
		d1.put(new Frazione(0));
		d1.put(new Frazione(-4, 3));
		
		FractionCollection d2 = new FractionCollection();
		d2.put(new Frazione(2, 1));
		d2.put(new Frazione(26, 5));
		d2.put(new Frazione(4, 3));

		res = d1.div(d2);
		
		// assert
		assert(3==res.size());
		System.out.println(res.toString());
		assert(new Frazione(1, 2).equals(res.get(0))==true);
		assert(new Frazione(0).equals(res.get(1))==true);
		assert(new Frazione(-1).equals(res.get(2))==true);
	}

}
