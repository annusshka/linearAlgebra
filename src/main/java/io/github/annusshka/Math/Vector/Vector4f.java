package io.github.annusshka.Math.Vector;

/**
 * Класс вектора размерности 4, реализация абстрактного класса Vector
 */

public class Vector4f extends Vector {

    static final int size = 4;

    private double[] vector;

    public Vector4f(double[] vector) {
        super(vector, size);
        this.vector = vector;
    }

    @Override
    public Vector getZeroVector(int size) {
        if (size != this.getSize()) {
            size = this.getSize();
        }
        return new Vector4f(new double[size]);
    }
}