package io.github.annusshka.Math.Vector;

/**
 * Класс вектора размерности 4, реализация абстрактного класса Vector
 */

public class Vector4d extends Vector {

    private static final int size = 4;

    private double[] vector = new double[size];

    public Vector4d(double[] vector) {
        super(vector, size);
        this.vector = vector;
    }

    public Vector4d() {
        super(new double[size], size);
    }

    @Override
    public Vector getZeroVector(int size) {
        if (size != this.getSize()) {
            size = this.getSize();
        }
        return new Vector4d(new double[size]);
    }
}