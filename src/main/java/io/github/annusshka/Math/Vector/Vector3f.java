package io.github.annusshka.Math.Vector;

/**
 * Класс вектора размерности 3, реализация абстрактного класса Vector
 */

public class Vector3f extends Vector {

    private static final int size = 3;

    private double[] vector;

    public Vector3f(double[] vector) {
        super(vector, size);
        this.vector = vector;
    }

    @Override
    public Vector getZeroVector(int size) {
        if (size != this.getSize()) {
            size = this.getSize();
        }
        return new Vector3f(new double[size]);
    }

    public static Vector сrossProduct(final Vector3f vector1, final Vector3f vector2) {
        Vector vector = vector1.getZeroVector(vector1.getSize());
        if (isEqualSize(vector1, vector2)) {
            vector.getVector()[0] = vector1.get(1) * vector2.get(2) - vector1.get(2) * vector2.get(1);
            vector.getVector()[1] = vector1.get(2) * vector2.get(0) - vector1.get(0) * vector2.get(2);
            vector.getVector()[2] = vector1.get(0) * vector2.get(1) - vector1.get(1) * vector2.get(0);
        }

        return vector;
    }
}