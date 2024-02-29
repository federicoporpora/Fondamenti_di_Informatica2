public class MyMathTest {
	public static void main(String args[]) {
		testOK();
		// testKO();
	}
	private static void testOK() {
		assert(MyMath.mcd(10, 5) == 5);
		assert(MyMath.mcd(7, 3) == 1);
		assert(MyMath.mcd(21,49) == 7);
		assert(MyMath.mcm(5, 7) == 35);
		assert(MyMath.mcm(4, 3) == 12);
		assert(MyMath.mcm(20,40) == 40);
	}
	// private static void testKO() {
	// 	assert(MyMath.mcd(10, 5) == 4);
	// }
}