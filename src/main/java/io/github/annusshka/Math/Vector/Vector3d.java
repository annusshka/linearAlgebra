package io.github.annusshka.Math.Vector;

/**
 * Класс вектора размерности 3, реализация абстрактного класса Vector
 */

public class Vector3d extends Vector {

    private static final int size = 3;

    private float[] vector = new float[size];

    public Vector3d(float[] vector) {
        super(vector, size);
        this.vector = vector;
    }

    public Vector3d() {
        super(new float[size], size);
    }

    @Override
    public Vector getZeroVector(int size) {
        if (size != this.getSize()) {
            size = this.getSize();
        }
        return new Vector3d(new float[size]);
    }

    public void crossProduct(final Vector3d vector1, final Vector3d vector2) {
        if (isEqualSize(vector1, vector2)) {
            this.getVector()[0] = vector1.get(1) * vector2.get(2) - vector1.get(2) * vector2.get(1);
            this.getVector()[1] = vector1.get(2) * vector2.get(0) - vector1.get(0) * vector2.get(2);
            this.getVector()[2] = vector1.get(0) * vector2.get(1) - vector1.get(1) * vector2.get(0);
        }
    }
}