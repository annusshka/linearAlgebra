package io.github.annusshka.Math.Vector;

public abstract class Vector {

    public static class VectorException extends Exception {

        public VectorException(String message) {
            super(message);
        }
    }

    protected int size;

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

    static boolean isMinus = false;

    public int getSize() {
        return size;
    }

    public double[] getVector() {
        return vector;
    }

    public void setVector(double[] vector) {
        if (vector.length == size) {
            this.vector = vector;
        } else {
            double[] rightVector = new double[size];
            System.arraycopy(vector, 0, rightVector, 0, Math.min(vector.length, size));
            this.vector = rightVector;
        }
    }

    public double get(int index) {
        double element = 0;
        if (index >= 0 && index < this.getSize()) {
            element = vector[index];
        }
        return element;
    }

    public void set(int index, double value) {
        if (index >= 0 && index < getVector().length) {
            vector[index] = value;
        }
    }

    public abstract Vector getZeroVector(int size);

    protected static boolean isEqualSize(Vector v1, Vector v2) {
        return v1.getSize() == v2.getSize();
    }

    public boolean isEqual(Vector otherVector) {
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

    public static Vector sumVector(Vector vector1, Vector vector2) throws VectorException {
        Vector resultVector = vector1.getZeroVector(vector1.getSize());

        if (isEqualSize(vector1, vector2)) {
            for (int index = 0; index < vector1.getSize(); index++) {
                if (isMinus) {
                    resultVector.getVector()[index] = vector1.get(index) - vector2.get(index);
                } else {
                    resultVector.getVector()[index] = vector1.get(index) + vector2.get(index);
                }
            }
            isMinus = false;

        } else {
            throw new Vector.VectorException("Vectors of different sizes can't be summed");
        }

        return resultVector;
    }

    public static Vector minusVector(Vector vector1, Vector vector2) throws VectorException {
        isMinus = true;
        return sumVector(vector1, vector2);
    }

    public Vector sumWithConstant(double constant) {
        for (int index = 0; index < this.getSize(); index++) {
            this.getVector()[index] += constant;
        }
        return this;
    }

    public Vector minusWithConstant(double constant) {
        return sumWithConstant(-constant);
    }

    public Vector multiplicateVectorOnConstant(double constant) {
        for (int index = 0; index < this.getSize(); index++) {
            this.getVector()[index] *= constant;
        }
        return this;
    }

    public Vector divideVectorOnConstant(double constant) throws VectorException {
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

    public static double multiplicateScalarVector(Vector vector1, Vector vector2) {
        double scalar = 0;
        if (isEqualSize(vector1, vector2)) {
            for (int index = 0; index < vector1.getSize(); index++) {
                scalar += vector1.get(index) * vector2.get(index);
            }
        }
        return scalar;
    }

    public void swapElements(int index, int changingIndex) {
        double changingValue = this.get(index);
        this.set(index, this.get(changingIndex));
        this.set(changingIndex,changingValue);
    }
}
