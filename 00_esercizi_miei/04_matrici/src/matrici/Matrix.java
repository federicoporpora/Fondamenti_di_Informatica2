package matrici;

import java.util.StringJoiner;

public class Matrix {
    private double[][] matrix;
    public Matrix(int rows, int cols) {
        matrix = new double[rows][cols];
    }
    public Matrix(double[][] values) {
        matrix = values;
    }
    private void setValue(int row, int col, double value) {
        matrix[row][col] = value;
    }
    public boolean isSquared() {
        return (matrix.length == matrix[0].length) ? true : false;
    }
    public double det() {
        return isSquared() ? calcDet() : Double.NaN;
    }
    public Matrix extractMinor(int rowToRemove, int colToRemove) {
        // Verifica se la rimozione della riga e della colonna specificate rende la matrice vuota o con dimensioni negative
        if (matrix.length - 1 <= 0 || matrix[0].length - 1 <= 0 || rowToRemove < 0 || rowToRemove >= matrix.length || colToRemove < 0 || colToRemove >= matrix[0].length) {
            return null;
        }
        double[][] res = new double[matrix.length - 1][matrix[0].length - 1];
        int rowAlreadyRemoved = 0, colAlreadyRemoved = 0;
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = 0; j < matrix[0].length - 1; j++) {
                if (i == rowToRemove) rowAlreadyRemoved = 1;
                if (j == colToRemove) colAlreadyRemoved = 1;
                res[i][j] = matrix[i + rowAlreadyRemoved][j + colAlreadyRemoved];
            }
            colAlreadyRemoved = 0;
        }
        return new Matrix(res);
    }
    
    private double calcDet() {
        if (matrix.length == 1) return matrix[0][0]; // TODO fare tutto con Matrix, non double[][]
        double det = 0;
        for (int i = 0; i < matrix.length; i++) {
            det += Math.pow(-1, i) * matrix[i][0] * (extractMinor(i, 0)).calcDet();
        }
        return det;
    }
    public Matrix extractSubMatrix(int startRow, int startCol, int rowCount, int colCount) {
        // Controlla se gli indici di inizio e la dimensione richiesta sono all'interno dei limiti della matrice
        if (startRow < 0 || startRow >= matrix.length || startCol < 0 || startCol >= matrix[0].length ||
            startRow + rowCount > matrix.length || startCol + colCount > matrix[0].length) {
            return null;
        }
    
        double[][] subMatrixValues = new double[rowCount][colCount];
        for (int i = startRow; i < startRow + rowCount; i++) {
            for (int j = startCol; j < startCol + colCount; j++) {
                subMatrixValues[i - startRow][j - startCol] = matrix[i][j];
            }
        }
        return new Matrix(subMatrixValues);
    }
    public int getCols() { return matrix[0].length; }
    public int getRows() { return matrix.length; }
    public double getValue(int row, int col) { return matrix[row][col]; }
    public Matrix mul(Matrix m) {
        // Verifica se il numero di colonne della prima matrice coincide con il numero di righe della seconda matrice
        if (this.getCols() != m.getRows()) {
            return null;
        }
        double[][] res = new double[this.getRows()][m.getCols()];
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < m.getCols(); j++) {
                for (int k = 0; k < this.getCols(); k++) {
                    res[i][j] += this.getValue(i, k) * m.getValue(k, j);
                }
            }
        }
        return new Matrix(res);
    }
    
    public Matrix sum(Matrix m) {
        // Verifica se le dimensioni delle matrici coincidono
        if (this.getRows() != m.getRows() || this.getCols() != m.getCols()) {
            return null;
        }
        double[][] res = new double[this.getRows()][this.getCols()];
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                res[i][j] = this.getValue(i, j) + m.getValue(i, j);
            }
        }
        return new Matrix(res);
    }
    
    public String toString() {
        StringJoiner esternoJoiner = new StringJoiner(",\n", "{", "}");
        for (int i = 0; i < getRows(); i++) {
            StringJoiner internoJoiner = new StringJoiner(", ", "{", "}");
            for (int j = 0; j < getCols(); j++) {
                internoJoiner.add(Double.toString(getValue(i, j)));
            }
            esternoJoiner.add(internoJoiner.toString());
        }
        return esternoJoiner.toString();
    }
}