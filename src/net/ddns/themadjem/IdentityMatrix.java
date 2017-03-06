package net.ddns.themadjem;

/**
 * @author Jesse Maddox
 * @version 1.0
 * @since Java 8
 */
public class IdentityMatrix extends Matrix {

    /**
     * Constructs the identity matrix of a given size
     *
     * Identity matrices are square and have a 1 in
     * each element where the row and column are equal
     * and 0 in every other element
     *
     * @param scl size of matrix
     */
    public IdentityMatrix(int scl) {
        super(scl, scl);
        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getCols(); col++) {
                if (row == col) {
                    setElement(row, col, 1);
                } else {
                    setElement(row, col, 0);
                }
            }
        }
    }

}
