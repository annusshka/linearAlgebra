package io.github.annusshka.Math.Matrix;

import io.github.annusshka.Math.Vector.Vector;
import io.github.annusshka.Math.Vector.Vector3f;

public class Matrix3x extends Matrix {

    static final int size = 3;

    static final int length = 9;

    static double[] vector = new double[length];

    public Matrix3x(double[] vector) {
        super(vector, size);
        Matrix3x.vector = vector;
    }

    @Override
    public Matrix getZeroMatrix(int size) {
        return new Matrix3x(new double[length]);
    }

    @Override
    public Vector getZeroVector(int size) {
        if (size != this.getSize()) {
            size = this.getSize();
        }
        return new Vector3f(new double[size]);
    }

    public static Matrix getZeroMatrix() {
        return new Matrix3x(new double[length]);
    }

    @Override
    public Matrix createUnitMatrix(double value) {
        Matrix3x matrix = new Matrix3x(new double[size * size]);

        int indexMainDiagonal = 0;
        for (int index = 0; index < matrix.getLength(); index++) {

            if (index == indexMainDiagonal * size + indexMainDiagonal) {
                matrix.set(index, value);
                indexMainDiagonal++;
            }
        }

        return matrix;
    }

    @Override
    public Matrix createUnitMatrix() {
        return createUnitMatrix(1);
    }


    /**
     * Метод раскладывает матрицу 3х3 по первой строке на 3 минора
     * @param matrix
     * @return возвращает определитель
     */
    public static double getMatrixDeterminant(Matrix3x matrix) {
        double determinant = 0.0;

        for (int index = 0; index < matrix.getSize(); index++) {
            int sign = (int) Math.pow(-1, index % matrix.getSize());

            determinant += sign * matrix.getVector()[index] * matrix.getMinor(index);
        }

        return determinant;
    }

    private double getMinor(int index) {
        double minor;
        double value1, value2;

        int indexCol = index % size;
        int indexRow = index / size;
        int indexCol1 = 0;
        int indexRow1 = 0;

        if (indexCol1 == indexCol) {
            indexCol1++;
        }

        int indexCol2 = indexCol1 + 1;
        if (indexCol2 == indexCol) {
            indexCol2++;
        }

        if (indexRow1 == indexRow) {
            indexRow1++;
        }

        int indexRow2 = indexRow1 + 1;
        if (indexRow2 == indexRow) {
            indexRow2++;
        }

        value1 = this.get((indexRow1) * size + indexCol1) * this.get((indexRow2) * size + indexCol2);
        value2 = this.get((indexRow1) * size + indexCol2) * this.get((indexRow2) * size + indexCol1);
        minor = value1 - value2;

        return minor;
    }
}