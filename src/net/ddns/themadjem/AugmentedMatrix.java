package net.ddns.themadjem;

import java.text.DecimalFormat;

/**
 * @author Jesse Maddox
 * @version 1.0
 * @since Java 8
 */
public class AugmentedMatrix extends Matrix {
    private final byte INPUT_OUTPUT = 2;
    private final byte INVERSE = 4;
    private final byte ERROR = -1;
    private byte augType;

    /*
    //TODO Make constructor build Matrix out of a variable and solution matrix
    // [[xxx]   |   [[y]        [[xxx|y]
    //  [xxx]   |    [y]    ->   [xxx|y]
    //  [xxx]]  |    [y]]        [xxx|y]]
    */

    /**
     * Constructs a Augmented Matrix object
     * from a previously constructed Matrix
     * (Converts to Augmented)
     * Automatically detects if it is an
     * I/O matrix or an Inverse matrix
     *
     * @param m1 matrix to be copied
     */
    public AugmentedMatrix(Matrix m1) {
        super(m1);
        if (getCols() == getRows() * 2) {
            augType = INVERSE;
        } else if (getCols() == getRows() + 1) {
            augType = INPUT_OUTPUT;
        } else augType = ERROR;
    }

    /**
     * Constructs a augmented matrix from a
     * given square matrix and a generated identity.
     * Use only for this purpose!
     * <p>
     * [[xxx]   |   [[yyy]        [[xxx|yyy]
     * [xxx]   |    [yyy]    ->   [xxx|yyy]
     * [xxx]]  |    [yyy]]        [xxx|yyy]]
     *
     * @param m1             original matrix
     * @param appendIdentity adds identity to the augmented matrix
     */
    public AugmentedMatrix(Matrix m1, boolean appendIdentity) {
        super(m1.getRows(), m1.getCols() * 2);
        augType = INVERSE;
        IdentityMatrix m2 = new IdentityMatrix(getRows());
        for (int row = 0; row < m1.getRows(); row++) {
            for (int col = 0; col < m1.getCols(); col++) {
                setElement(row, col, m1.getElement(row, col));
                setElement(row, col + m2.getCols(), m2.getElement(row, col));
            }
        }

    }

    public AugmentedMatrix(Matrix m1, Matrix m2) {
        super(m1.getRows() + 1, m1.getCols());
        augType = INPUT_OUTPUT;
        for (int row = 0; row < getRows() - 1; row++) {
            for (int col = 0; col < getCols() - 1; col++) {
                if (col == getCols() - 1) {
                    setElement(row, col, m2.getElement(row, 0));
                }

            }

        }

    }

    public AugmentedMatrix rref() {
        MatrixRow[] result = getMatrixRows();
        if (augType == ERROR) {
            System.err.println("Invalid Augment");
            System.exit(ERROR);
        }
        for (int i = getRows() - 1; i >= 0; i--) {
            result[i].divideEquals(result[i].getElement(i));
            for (int j = getCols() - augType; j >= 0; j--) {
                if (j != i) {
                    result[j].plusEquals(result[i].multiply(result[j].getElement(i) * -1));
                }
            }
        }
        return new AugmentedMatrix(new Matrix(result));
    }

    /**
     * Returns the inverse of a square matrix augmented with an identity
     * @return
     */
    public Matrix inverse() {
        if (getCols() == getRows() * 2) {
            return this.rref();
        }
        return this;
    }

    private String matrix_string;

    private void addToStr(String str) {
        matrix_string = matrix_string.concat(str);
    }

    /**
     * Creates a string of a matrix,
     * formatted in a matrix style
     * Override:
     * Adds a bar "|" before the fourth element to get the effect of augment
     * [[ABCD]  ->  [[ABC|D]
     * [EFGH]  ->   [EFG|H]
     * [IJKL]] ->   [IJK|L]]
     *
     * @return formatted string of matrix
     */
    @Override
    public String toFormattedString() {
        matrix_string = "[";
        DecimalFormat df = new DecimalFormat("#0.000");

        for (int row = 0; row < getRows(); row++) {
            if (row != 0) addToStr(" ");
            addToStr("[");
            for (int col = 0; col < getCols(); col++) {
                addToStr("\t");
                if (col == getCols() - augType + 1) addToStr("|\t");
                addToStr(df.format(getElement(row, col)));
            }
            if (row == getRows() - 1) {
                addToStr("\t]");
            } else {
                addToStr("\t] \n");
            }
        }
        addToStr("]\n\n");
        return matrix_string;
    }
}