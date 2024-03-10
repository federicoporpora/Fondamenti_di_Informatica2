package libreriastatica;

import java.util.StringJoiner;

public class MatriceLib {
    public static double[][] sommaMatrici(double[][] a, double[][] b) {
        double[][] res = new double[a.length][b.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                res[i][j] = a[i][j] + b[i][j];
            }
        }
        return res;
    }
    public static double[][] prodottoMatrici(double[][] a, double[][] b) {
        double[][] res = new double[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    res[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return res;
    }
    public static void stampaMatrice(double[][] a) {
        StringJoiner esternoJoiner = new StringJoiner(",\n", "{", "}");
        for (int i = 0; i < a.length; i++) {
            StringJoiner internoJoiner = new StringJoiner(", ", "{", "}");
            for (int j = 0; j < a[i].length; j++) {
                internoJoiner.add(Double.toString(a[i][j]));
            }
            esternoJoiner.add(internoJoiner.toString());
        }
        System.out.println(esternoJoiner);
    }
}