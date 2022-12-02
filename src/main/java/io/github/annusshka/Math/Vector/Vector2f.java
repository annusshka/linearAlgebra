package io.github.annusshka.Math.Vector;

/**
 * Класс вектора размерности 2, реализация абстрактного класса Vector
 */

public class Vector2f extends Vector {

    private static final int size = 2;

    private double[] vector = new double[size];

    public Vector2f(double[] vector) {
        super(vector, size);
        this.vector = vector;
    }

    @Override
    public Vector getZeroVector(int size) {
        if (size != this.getSize()) {
            size = this.getSize();
        }
        return new Vector2f(new double[size]);
    }
}