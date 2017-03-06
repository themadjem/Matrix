package net.ddns.themadjem;

/**
 * @author Jesse Maddox
 * @version 1.0
 * @since Java 8
 */
public class MatrixRow {
    private double[] row;

    /**
     * Builds a Matrix row from an array of doubles
     *
     * @param row double array
     */
    public MatrixRow(double[] row) {
        this.row = row;
    }

    /**
     * Builds an empty Matrix row of a given size
     *
     * @param size size or row
     */
    public MatrixRow(int size) {
        this(new double[size]);
    }

    /**
     * Divides each row element by a given value
     * then sets the row to the new values
     *
     * @param value denominator
     */
    void divideEquals(double value) {
        for (int i = 0; i < row.length - 1; i++) {
            setElement(i, getElement(i) / value);
        }
    }

    /**
     * Adds a each row element to the
     * then sets the row to the new values
     *
     * @param row2 Second Matrix Row
     */
    void plusEquals(MatrixRow row2) {
        for (int j = 0; j < row.length; j++) {
            row[j] += row2.getElement(j);
        }
    }

    /**
     * Adds a value to each row element
     * then sets the row to the new values
     *
     * @param value additive value
     */
    void plusEquals(double value) {
        for (int j = 0; j < row.length; j++) {
            row[j] += value;
        }
    }

    /**
     * Scalar Multiplication
     * Multiplies each row element by a given value
     *
     * @param value multiplier
     * @return resulting muliplied row
     */
    MatrixRow multiply(double value) {
        double[] result = new double[row.length];
        for (int j = 0; j < row.length; j++) {
            result[j] = row[j] * value;
        }
        return new MatrixRow(result);
    }

    /**
     * Returns specific element of the row
     *
     * @param index index
     * @return element
     */
    public double getElement(int index) {
        return row[index];
    }

    /**
     * Returns the entire array of doubles
     *
     * @return double[]
     */
    public double[] getRow() {
        return row;
    }

    /**
     * Sets a specific element of the row to a given value
     *
     * @param index index
     * @param value new value
     */
    public void setElement(int index, double value) {
        row[index] = value;
    }
}
