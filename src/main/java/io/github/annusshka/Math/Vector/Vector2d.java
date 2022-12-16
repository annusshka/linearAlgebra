package io.github.annusshka.Math.Vector;

/**
 * Класс вектора размерности 2, реализация абстрактного класса Vector
 */

public class Vector2d extends Vector {

    private static final int size = 2;

    private double[] vector = new double[size];

    public Vector2d(double[] vector) {
        super(vector, size);
        this.vector = vector;
    }

    public Vector2d() {
        super(new double[size], size);
    }

    @Override
    public Vector getZeroVector(int size) {
        if (size != this.getSize()) {
            size = this.getSize();
        }
        return new Vector2d(new double[size]);
    }
}