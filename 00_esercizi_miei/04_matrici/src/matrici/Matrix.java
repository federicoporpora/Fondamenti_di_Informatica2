package matrici;

public class Matrix {
    private double[][] matrix;
    public Matrix(int rows, int cols) {
        matrix = new double[rows][cols];
    }
    public Matrix(double[][] values) {
        matrix = values;
    }
    public int getRows() { return matrix.length; }
    public int getCols() { return matrix[0].length; }
    public double getValue(int row, int col) { return matrix[row][col]; }
    private void setValue(int row, int col, double value) { matrix[row][col] = value; }
    public boolean isSquared() {
        return this.getRows() == this.getCols();
    }
    public double det() {
        return isSquared() ? calcDet() : Double.NaN;
    }
    public Matrix extractMinor(int rowToRemove, int colToRemove) {
        if (this.getRows() - 1 <= 0 || this.getCols() - 1 <= 0 || rowToRemove < 0 || rowToRemove >= this.getRows() || colToRemove < 0 || colToRemove >= this.getCols()) {
            return null;
        }
        Matrix res = new Matrix(this.getRows() - 1, this.getCols() - 1);
        int rowAlreadyRemoved = 0, colAlreadyRemoved = 0;
        for (int i = 0; i < this.getRows() - 1; i++) {
            for (int j = 0; j < this.getCols() - 1; j++) {
                if (i == rowToRemove) rowAlreadyRemoved = 1;
                if (j == colToRemove) colAlreadyRemoved = 1;
                res.setValue(i, j, getValue(i + rowAlreadyRemoved, j + colAlreadyRemoved));
            }
            colAlreadyRemoved = 0;
        }
        return res;
    }
    private double calcDet() {
        if (this.getRows() == 1) return this.getValue(0, 0);
        double det = 0;
        for (int i = 0; i < this.getRows(); i++) {
            det += Math.pow(-1, i) * this.getValue(i, 0) * (extractMinor(i, 0)).calcDet();
        }
        return det;
    }
    public Matrix extractSubMatrix(int startRow, int startCol, int rowCount, int colCount) {
        if (startRow < 0 || startRow >= this.getRows() || startCol < 0 || startCol >= this.getCols() ||
            startRow + rowCount > this.getRows() || startCol + colCount > this.getCols()) {
            return null;
        }
        Matrix subMatrixValues = new Matrix(rowCount, colCount);
        for (int i = startRow; i < startRow + rowCount; i++) {
            for (int j = startCol; j < startCol + colCount; j++) {
                subMatrixValues.setValue(i - startRow, j - startCol, this.getValue(i, j));
            }
        }
        return subMatrixValues;
    }
    public Matrix mul(Matrix m) {
        if (this.getCols() != m.getRows()) {
            return null;
        }
        Matrix res = new Matrix(this.getRows(), m.getCols());
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < m.getCols(); j++) {
                for (int k = 0; k < this.getCols(); k++) {
                    res.setValue(i, j, res.getValue(i, j) + (this.getValue(i, k) * m.getValue(k, j)));
                }
            }
        }
        return res;
    } 
    public Matrix sum(Matrix m) {
        if (this.getRows() != m.getRows() || this.getCols() != m.getCols()) {
            return null;
        }
        Matrix res = new Matrix(this.getRows(), this.getCols());
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                res.setValue(i, j, this.getValue(i, j) + m.getValue(i, j));
            }
        }
        return res;
    }
    public String toString() {
        java.util.StringJoiner esternoJoiner = new java.util.StringJoiner(",\n", "{", "}");
        for (int i = 0; i < getRows(); i++) {
            java.util.StringJoiner internoJoiner = new java.util.StringJoiner(", ", "{", "}");
            for (int j = 0; j < getCols(); j++) {
                internoJoiner.add(Double.toString(getValue(i, j)));
            }
            esternoJoiner.add(internoJoiner.toString());
        }
        return esternoJoiner.toString();
    }
}