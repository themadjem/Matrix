package net.ddns.themadjem;

/**
 * @author Jesse Maddox
 * @version 1.0
 * @since Java 8
 */
public class MatrixRow {
    double[] row;

    public MatrixRow(double[] row) {
        this.row = row;
    }

    public MatrixRow(int size) {
        this(new double[size]);
    }

    void divideEquals(double value) {
        for (int i = 0; i < row.length - 1; i++) {
            setElement(i, getElement(i) / value);
        }
    }

    void plusEquals(MatrixRow row2) {
        for (int j = 0; j < row.length; j++) {
            row[j] += row2.getElement(j);
        }
    }

    MatrixRow mult(double value) {
        double[] temp = new double[row.length];
        for (int j = 0; j < row.length; j++) {
            temp[j] = row[j] * value;
        }

        return new MatrixRow(temp);
    }

    public double getElement(int index) {
        return row[index];
    }

    public double[] getRow() {
        return row;
    }

    public void setElement(int index, double value) {
        row[index] = value;
    }
}
