package net.ddns.themadjem;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * @author Jesse Maddox
 * @version 1.0
 * @since Java 8
 */
public class Matrix {

    private RuntimeException UnequalSizeException = new RuntimeException("Matrices are not equal in size");
    private RuntimeException InvalidMultiplicationException = new RuntimeException("Matrices cannot be multiplied");


    private MatrixRow[] matrixRows; //Array of MatrixRows
    //x = number of rows (length of array)
    //y = number of columns (length of rows)
    private Vector2D size;
    private String matrix_string; //String to hold a matrix to be printed


    /**
     * Constructor for a new Matrix of a given 2-D array
     *
     * @param mat given 2-D double array
     */
    public Matrix(double[][] mat) {
        //Store double[][] as a private array of MatrixRows
        matrixRows = new MatrixRow[mat.length];
        for (int i = 0; i < mat.length; i++) {
            matrixRows[i] = new MatrixRow(mat[i]);
        }
        //compute dimensions and store in a Vector2D (rows, cols)
        setSize();
        //Check if matrix is valid (rows are all same size)
    }

    /**
     * Constructor that makes a Null Matrix of a given dimension in a Vector2D
     *
     * @param dim Vector2D dimensions
     */
    public Matrix(Vector2D dim) {
        matrixRows = new MatrixRow[dim.x];
        for (int i = 0; i < dim.x; i++) {
            matrixRows[i] = new MatrixRow(new double[dim.y]);
        }
        setSize();
    }

    /**
     * Constructor that makes a Null Matrix of given int dimensions
     *
     * @param rows number of rows
     * @param cols number of columns
     */
    public Matrix(int rows, int cols) {
        matrixRows = new MatrixRow[rows];
        for (int i = 0; i < rows; i++) {
            matrixRows[i] = new MatrixRow(cols);
        }
        setSize();
    }

    /**
     * Constructs a new matrix as a copy of another given matrix
     *
     * @param mat matrix to be copied
     */
    public Matrix(Matrix mat) {
        matrixRows = new MatrixRow[mat.getRows()];
        for (int i = 0; i < mat.getCols() - 1; i++) {
            matrixRows[i] = new MatrixRow(new double[mat.getCols()]);
        }
        setSize();
        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getCols(); col++) {
                setElement(row, col, mat.getElement(row, col));
            }
        }
    }

    /**
     * Constructs a matrix from an array of MatrixRows
     *
     * @param a array of MatrixRows
     */
    public Matrix(MatrixRow[] a) {
        matrixRows = a;
        setSize();
    }

    /**
     * Checks if the matrix is Square
     * (rows == columns)
     */
    private boolean isSquare() {
        for (int row = 0; row < getRows(); row++) {
            if (matrixRows[row].getRow().length != getCols()) return false;
        }
        return true;
    }

    /**
     * Returns element of matrix of a given row and column position
     *
     * @param row row containing element
     * @param col column containing element
     * @return double element
     */
    public double getElement(int row, int col) {
        return matrixRows[row].getElement(col);
    }

    /**
     * Sets element at a given point in the matrix
     *
     * @param row   row
     * @param col   column
     * @param value value
     */
    public void setElement(int row, int col, double value) {
        matrixRows[row].setElement(col, value);
    }

    /**
     * Sets the Vector2D of the dimensions
     */
    private void setSize() {
        size = new Vector2D(matrixRows.length, matrixRows[0].getRow().length);
    }

    /**
     * Gets the dimensions of the matrix
     *
     * @return Vector2D of size
     */
    public Vector2D getDimensions() {
        return size;
    }

    /**
     * Gets the number of rows in the matrix
     *
     * @return size.x (number of rows)
     */
    public int getRows() {
        return size.x;
    }

    /**
     * Gets the number of columns in the matrix
     *
     * @return size.y (number of columns)
     */
    public int getCols() {
        return size.y;
    }

    /**
     * Gets the MatrixRow array
     *
     * @return array of MatrixRows
     */
    public MatrixRow[] getMatrixRows() {
        return matrixRows;
    }

    /**
     * Adds two Matrices together and returns the result
     * Must be two equal sized arrays
     * [[ABC]   [[JKL]     [[(A+J)(B+K)(C+L)]
     * [DEF]  -  [MNO]  =  [(D+M)(E+N)(F+O)]
     * [GHI]]    [PQR]]    [(G+P)(H+Q)(I+R)]]
     *
     * @param m2 Matrix to be added
     * @return Matrix of addition
     * @throws RuntimeException unequal sizes
     */
    public Matrix add(Matrix m2) throws RuntimeException {
        if (hasEqualSize(m2)) {
            Matrix result = new Matrix(getDimensions());
            for (int row = 0; row < getRows(); row++) {
                for (int col = 0; col < getCols(); col++) {
                    double res = getElement(row, col) + m2.getElement(row, col);
                    result.setElement(row, col, res);
                }
            }
            return result;
        } else {
            throw UnequalSizeException;
        }
    }

    /**
     * subtracts given matrix from parent
     * Must be equal in size
     * [[ABC]   [[JKL]    [[(A-J)(B-K)(C-L)]
     * [DEF]  -  [MNO]  =  [(D-M)(E-N)(F-O)]
     * [GHI]]    [PQR]]    [(G-P)(H-Q)(I-R)]]
     *
     * @return this-m2
     * @throws RuntimeException Unequal Sizes
     */
    public Matrix subtract(Matrix m2) throws RuntimeException {
        if (hasEqualSize(m2)) {
            Matrix result = new Matrix(size);
            for (int row = 0; row < getRows(); row++) {
                for (int col = 0; col < getCols(); col++) {
                    double res = getElement(row, col) - m2.getElement(row, col);
                    result.setElement(row, col, res);
                }
            }
            return result;
        } else throw UnequalSizeException;
    }

    /**
     * Scalar Multiplication
     * Multiplies each element by a given value
     *
     * @param value scalar
     * @return scaled matrix
     */
    public Matrix multiply(double value) {
        Matrix result = new Matrix(size);
        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getCols(); col++) {
                double res = getElement(row, col) * value;
                result.setElement(row, col, res);
            }
        }
        return result;
    }

    /**
     * Multiplies two matrices together assuming
     * the number of rows of the first is the same as
     * the number of columns on the second.
     *
     * @param m2 matrix to be multiplied by
     * @return result matrix with a size of m1's cols and m2's rows
     */
    public Matrix multiply(Matrix m2) throws RuntimeException {
        //this.size = (A,B)
        //m2.size = (C,D)
        //result.size = (A, D)
        Matrix result = new Matrix(this.getRows(), m2.getCols());
        result.empty();
        double res;
        if (this.getCols() == m2.getRows()) {//B === C
            for (int i = 0; i < result.getRows(); i++) {
                for (int j = 0; j < result.getCols(); j++) {
                    for (int k = 0; k < this.getCols(); k++) {
                        res = (this.getElement(i, k) * m2.getElement(k, j));
                        res += result.getElement(i, j);
                        result.setElement(i, j, res);
                    }
                }
            }
        } else {
            throw InvalidMultiplicationException;
        }
        return result;
    }

    /**
     * Sets each element of the Matrix to 0
     */
    public void empty() {
        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getCols(); col++) {
                setElement(row, col, 0d);
            }
        }
    }

    /**
     * Transposes a matrix
     * exchanges rows and columns
     * [[ABC]     [[ADG]
     * [DEF]  ->  [BEH]
     * [GHI]]     [CFI]]
     *
     * @return transposed matrix
     */
    public Matrix transpose() {
        Matrix result = new Matrix(getCols(), getRows());
        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getCols(); col++) {
                result.setElement(col, row, this.getElement(row, col));
            }
        }
        return result;
    }

    /**
     * Sets all elements of the matrix to a random
     * integer between zero and a given limit
     *
     * @param limit given limit of randoms
     */
    public void randomize(int limit) {
        Random rand = new Random();
        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getCols(); col++) {
                setElement(col, row, rand.nextInt(limit));
            }
        }
    }

    /**
     * Checks if two matrices are equal in size
     *
     * @param m2 compared matrix
     * @return true if equal
     */
    private boolean hasEqualSize(Matrix m2) {
        return size.equals(m2.size);
    }

    /**
     * Adds a given string to the end of matrix string
     *
     * @param str string to be added
     */
    private void addToStr(String str) {
        matrix_string = matrix_string.concat(str);
    }

    /**
     * Creates a string of a matrix,
     * formatted in a matrix style
     * {{A,B,C}{E,F,G}{H,I,J}} -> "[[\tA\tB\tC\t]\n [\tD\t...I\t]]" ->
     * [[   A   B   C   ]
     * [   D   E   F   ]
     * [   G   H   I   ]]
     *
     * @return formatted string of matrix
     */
    public String toFormattedString() {
        matrix_string = "[";
        DecimalFormat df = new DecimalFormat("#0.000");

        for (int row = 0; row < getRows(); row++) {
            if (row == 0) {
                addToStr("[");
            } else {
                addToStr(" [");
            }
            for (int col = 0; col < getCols(); col++) {
                addToStr("\t" + df.format(getElement(row, col)));
            }
            if (row == getRows() - 1) {
                addToStr("\t]");
            } else {
                addToStr("\t] \n");
            }
        }
        addToStr("]\n");
        return matrix_string;
    }
}