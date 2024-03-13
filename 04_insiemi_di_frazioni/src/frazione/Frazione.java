package frazione;

import util.MyMath;
public class Frazione {
    private int num, den;
    public Frazione(int n, int d) {
        if (d == 0) {
            num = n;
            den = d;
            System.out.println("Warning: il denominatore richiesto e' 0");
        }
        if (d < 0) {
            num = -n; den = -d;
        }
        else { num = n; den = d; }
    }
    public Frazione(int n) { this(n, 1); }
    public int getNum() {
        return num;
    }
    public int getDen() {
        return den;
    }
    public boolean equals(Frazione f) {
        if (this.num == 0 && f.num == 0) return true;
        return (this.num * f.den == this.den * f.num);
    }
    public String toString() {
        return Integer.toString(num) + '/' + Integer.toString(den);
    }
    public Frazione minTerm() {
    	if (num == 0 || den == 0) return new Frazione(num, den);
        return new Frazione((num / MyMath.mcd(num, den)), (den / MyMath.mcd(num, den)));
    }
    public Frazione sumWithMcm(Frazione f) {
        Frazione res = new Frazione(1, MyMath.mcm(this.den, f.den));
        res.num = (this.num * (res.den / this.den)) + (f.num * (res.den / f.den));
        res = res.minTerm();
        return res;
    }
    public Frazione sum(Frazione f){
        int n = num * f.den + den * f.num;
        int d = den * f.den;
        if (n == 0) return new Frazione(n, MyMath.mcm(den, f.den));
        if (d == 0) return new Frazione(MyMath.mcm(num, f.num), d);
        Frazione fSum = new Frazione(n, d);
        return fSum.minTerm();
    }
    public Frazione sub(Frazione f) {
        Frazione inverted = new Frazione(-f.num, f.den);
        return this.sum(inverted).minTerm();
    }
    public Frazione mul(Frazione f) {
        return new Frazione((this.num * f.num), (this.den * f.den)).minTerm();
    }
    public Frazione div(Frazione f) {
        return this.mul(f.reciprocal()).minTerm();
    }
    public Frazione reciprocal() {
        if (num == 0) {
            System.out.println("Warning: il denominatore assegnato al reciproco e' 0");
            return new Frazione(den, num).minTerm();
        } else {
            return new Frazione(den, num).minTerm();
        }
    }
    public int compareTo(Frazione f) {
        if (this.equals(f)) {return 0;}
        int mcm = MyMath.mcm(this.den, f.den);
        if ((this.num * mcm) > (f.num * mcm)) {return 1;}
        else {return -1;}
    }
    public double getDouble() {
        return ((double) num) / ((double) den);
    }
	public static Frazione mul(Frazione[] fs) {
		if (fs.length == 0) { return null; }
		if (fs.length == 1) { return fs[0]; }
		Frazione res = new Frazione(1, 1);
		for (Frazione x : fs) {
			res = res.mul(x);
		}
		return res;
	}
	public static Frazione sum(Frazione[] fs) {
		if (fs.length == 0) { return null; }
		if (fs.length == 1) { return fs[0]; }
		Frazione res = new Frazione(0, 1);
		for (Frazione x : fs) {
			res = res.sum(x);
		}
		return res;
	}
    public static Frazione sub(Frazione[] fs) {
        if (fs.length == 0) { return null; }
        if (fs.length == 1) { return fs[0]; }
        Frazione res = new Frazione(0, 1);
        for (Frazione x : fs) {
            res = res.sub(x);
        }
        return res;
    }
    public static Frazione div(Frazione[] fs) {
        if (fs.length == 0) { return null; }
        if (fs.length == 1) { return fs[0]; }
        Frazione res = new Frazione(1, 1);
        for (Frazione x : fs) {
            res = res.div(x);
        }
        return res;
    }

	
	public static String convertToString(Frazione[] tutte) {
		java.util.StringJoiner res = new java.util.StringJoiner(", ", "[", "]");
		for (int k=0; k<tutte.length && tutte[k]!= null; k++){
		 res.add(tutte[k].toString());
		}
		return res.toString();
	}
	public static Frazione[] sum(Frazione[] setA, Frazione[] setB) {
		if (size(setA) != size(setB)) return null;
		Frazione[] result = new Frazione[size(setB)];
		for (int k=0; k<result.length; k++){
		 result[k] = setA[k].sum(setB[k]);
		}
		return result;
	}
    public static Frazione[] sub(Frazione[] setA, Frazione[] setB) {
        if (size(setA)!= size(setB)) return null;
        Frazione[] result = new Frazione[size(setB)];
        for (int k=0; k<result.length; k++){
            result[k] = setA[k].sub(setB[k]);
        }
        return result;
    }
	public static Frazione[] mul(Frazione[] setA, Frazione[] setB) {
		if (size(setA) != size(setB)) return null;
		Frazione[] result = new Frazione[size(setB)];
		for (int k=0; k<result.length; k++){
		 result[k] = setA[k].mul(setB[k]);
		}
		return result;
	}
    public static Frazione[] div(Frazione[] setA, Frazione[] setB) {
        if (size(setA)!= size(setB)) return null;
        Frazione[] result = new Frazione[size(setB)];
        for (int k=0; k<result.length; k++){
            result[k] = setA[k].div(setB[k]);
        }
        return result;
    }

	public static int size(Frazione[] tutte) {
		int i = 0;
		for (Frazione x : tutte) {
			if (x != null) i++;
		}
		return i;
	}
}