package io.github.annusshka.Math.Vector;

/**
 * Класс вектора размерности 4, реализация абстрактного класса Vector
 */

public class Vector4d extends Vector {

    private static final int size = 4;

    private float[] vector = new float[size];

    public Vector4d(float[] vector) {
        super(vector, size);
        this.vector = vector;
    }

    public Vector4d() {
        super(new float[size], size);
    }

    @Override
    public Vector getZeroVector(int size) {
        if (size != this.getSize()) {
            size = this.getSize();
        }
        return new Vector4d(new float[size]);
    }
}