public class Frazione {
    private int num, den;
    public Frazione(int n, int d) {
        if (d == 0) {
            num = n;
            den = 1;
            System.out.println("Warning: il denominatore richiesto e' 0, ho assegnato invece 1");
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
        return (this.num * f.den == this.den * f.num);
    }
    public String toString() {
        return Integer.toString(num) + '/' + Integer.toString(den);
    }
    public Frazione minTerm() {
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
        Frazione fSum = new Frazione(n, d);
        return fSum.minTerm();
    }
    public Frazione mul(Frazione f) {
        return new Frazione((this.num * f.num), (this.den * f.den)).minTerm();
    }
    public Frazione sub(Frazione f) {
        Frazione res = new Frazione(1, MyMath.mcm(this.den, f.den));
        res.num = (this.num * (res.den / this.den)) - (f.num * (res.den / f.den));
        res = res.minTerm();
        return res;
    }
    public Frazione reciprocal() {
        if (num == 0) {
            System.out.println("Warning: il denominatore richiesto e' 0, ho assegnato invece 1");
            return new Frazione(den, 1).minTerm();
        } else {
            return new Frazione(den, num).minTerm();
        }
    }
    public Frazione div(Frazione f) {
        return (this.mul(f.reciprocal())).minTerm();
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
}