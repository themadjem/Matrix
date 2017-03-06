package net.ddns.themadjem;

import java.util.Random;

/**
 * @author Jesse Maddox
 * @version 1.0
 * @since Java 8
 */
public class RandomMatrix extends Matrix {
    private Random rand = new Random();

    /**
     * Returns a square matrix of a given size with random elements
     * if onlyPositives is true, all elements will be positive between 1 and limit
     * if onlyPositives is false, all elements will be random between -limit and limit
     *
     * @param scl           size of matrix
     * @param limit         limit of random numbers
     * @param onlyPositives only positive numbers
     */
    public RandomMatrix(int scl, int limit, boolean onlyPositives) {
        super(scl, scl);
        int temp = 0;
        //if not true, make bounds +/-limit
        if (!onlyPositives) { //if not only Whole numbers
            temp = limit; // store original
            limit *= 2; //double limit
        }
        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getCols(); col++) {
                setElement(row, col, (rand.nextInt(limit) - temp));
            }
        }
    }

    /**
     * Returns a square matrix of a given size with random elements
     * if onlyPositives is true, all elements will be positive between 1 and limit
     * if onlyPositives is false, all elements will be random between -limit and limit
     *
     * @param dim           Vector of the dimensions
     * @param limit         limit of random numbers
     * @param onlyPositives only positive numbers
     */
    public RandomMatrix(Vector2D dim, int limit, boolean onlyPositives) {
        super(dim.x, dim.y);
        int temp = 0;
        //if not true, make bounds +/-limit
        if (!onlyPositives) { //if not only Whole numbers
            temp = limit; // store original
            limit *= 2; //double limit
        }
        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getCols(); col++) {
                setElement(row, col, (rand.nextInt(limit) - temp));
            }
        }
    }

    /**
     * Returns a square matrix of a given size with random elements
     * if onlyPositives is true, all elements will be positive between 1 and limit
     * if onlyPositives is false, all elements will be random between -limit and limit
     *
     * @param rows          number of rows
     * @param cols          number of columns
     * @param limit         limit of random numbers
     * @param onlyPositives only positive numbers
     */
    public RandomMatrix(int rows, int cols, int limit, boolean onlyPositives) {
        super(rows, cols);
        int temp = 0;
        //if not true, make bounds +/-limit
        if (!onlyPositives) { //if not only Whole numbers
            temp = limit; // store original
            limit *= 2; //double limit
        }
        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getCols(); col++) {
                setElement(row, col, (rand.nextInt(limit) - temp));
            }
        }
    }

    /**
     * Constructs a Random Matrix with a default of Only Positive numbers
     *
     * @param scl   size of Matrix
     * @param limit limit of random numbers
     */
    public RandomMatrix(int scl, int limit) {
        this(scl, limit, true);
    }

    /**
     * Constructs a Random Matrix with defaults of limit 10 only positive numbers
     *
     * @param scl size of Matrix
     */
    public RandomMatrix(int scl) {
        this(scl, 10, true);
    }

    /**
     * Constructs a RandomMatrix with a default limit of 10
     *
     * @param scl           size of matrix
     * @param onlyPositives only positive numbers
     */
    public RandomMatrix(int scl, boolean onlyPositives) {
        this(scl, 10, onlyPositives);
    }
}