package io.github.annusshka.Math.Vector;

public abstract class Vector {

    public static class VectorException extends Exception {

        public VectorException(String message) {
            super(message);
        }
    }

    protected final int size;

    protected double[] vector;

    public Vector(double[] vector, int size) {
        if (vector.length == size) {
            this.vector = vector;
            this.size = size;
        } else if (size > 0) {
            double[] rightVector = new double[size];
            System.arraycopy(vector, 0, rightVector, 0, Math.min(vector.length, size));
            this.vector = rightVector;
            this.size = size;
        } else {
            this.vector = new double[0];
            this.size = 0;
        }
    }

    static final float EPS = 1e-7f;

    public int getSize() {
        return size;
    }

    public double[] getVector() {
        return vector;
    }

    public void setData(double[] data) {
        if (data.length == size) {
            this.vector = data;
        } else {
            double[] rightVector = new double[size];
            System.arraycopy(data, 0, rightVector, 0, Math.min(data.length, size));
            this.vector = rightVector;
        }
    }

    public double get(final int index) {
        double element = 0;
        if (index >= 0 && index < this.getSize()) {
            element = vector[index];
        }
        return element;
    }

    public void set(final int index, final double value) {
        if (index >= 0 && index < getVector().length) {
            vector[index] = value;
        }
    }

    public abstract Vector getZeroVector(final int size);

    protected static boolean isEqualSize(final Vector v1, final Vector v2) {
        return v1.getSize() == v2.getSize();
    }

    public boolean isEqual(final Vector otherVector) {
        if (isEqualSize(this, otherVector)) {
            for (int index = 0; index < this.getVector().length; index++) {
                if (Math.abs(this.get(index) - otherVector.get(index)) >= EPS) {
                    return false;
                }
            }
        } else {
            return false;
        }

        return true;
    }

    public static Vector sumVector(final Vector vector1, final Vector vector2) throws VectorException {
        Vector resultVector = vector1.getZeroVector(vector1.getSize());

        if (isEqualSize(vector1, vector2)) {
            for (int index = 0; index < vector1.getSize(); index++) {
                resultVector.getVector()[index] = vector1.get(index) + vector2.get(index);
            }
        } else {
            throw new Vector.VectorException("Vectors of different sizes can't be summed");
        }

        return resultVector;
    }

    public static Vector minusVector(final Vector vector1, final Vector vector2) throws VectorException {
        Vector resultVector = vector1.getZeroVector(vector1.getSize());

        if (isEqualSize(vector1, vector2)) {
            for (int index = 0; index < vector1.getSize(); index++) {
                resultVector.getVector()[index] = vector1.get(index) - vector2.get(index);
            }

        } else {
            throw new Vector.VectorException("Vectors of different sizes can't be summed");
        }

        return resultVector;
    }

    public Vector sumWithConstant(final double constant) {
        for (int index = 0; index < this.getSize(); index++) {
            this.getVector()[index] += constant;
        }
        return this;
    }

    public Vector minusWithConstant(final double constant) {
        return sumWithConstant(-constant);
    }

    public Vector multiplicateVectorOnConstant(final double constant) {
        for (int index = 0; index < this.getSize(); index++) {
            this.getVector()[index] *= constant;
        }
        return this;
    }

    public Vector divideVectorOnConstant(final double constant) throws VectorException {
        if (Math.abs(0 - constant) < EPS) {
            throw new VectorException("Division by zero");
        }
        return multiplicateVectorOnConstant(1.0 / constant);
    }

    public double getVectorLength() {
        double length = 0;
        for (double value: this.getVector()) {
            length += Math.pow(value, 2);
        }
        return Math.sqrt(length);
    }

    public Vector normalizeVector() throws VectorException {
        return divideVectorOnConstant(getVectorLength());
    }

    public static double dotProduct(final Vector vector1, final Vector vector2) {
        double scalar = 0;
        if (isEqualSize(vector1, vector2)) {
            for (int index = 0; index < vector1.getSize(); index++) {
                scalar += vector1.get(index) * vector2.get(index);
            }
        }
        return scalar;
    }

    public void swapElements(final int index, final int changingIndex) {
        double changingValue = this.get(index);
        this.set(index, this.get(changingIndex));
        this.set(changingIndex,changingValue);
    }
}
