package io.github.annusshka;

import io.github.annusshka.Math.Matrix.Matrix;
import io.github.annusshka.Math.Matrix.Matrix3x;
import io.github.annusshka.Math.Matrix.Matrix4x;
import io.github.annusshka.Math.Vector.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class MatrixTest {
    static final float EPS = 1e-10f;
    @Test
    public void createUnitMatrix() {
        Matrix3x matrix3x = new Matrix3x(new double[]{5.4, 0, 0, 0, 5.4, 0, 0, 0, 5.4});
        Assertions.assertThat(matrix3x.createIdentityMatrix(5.4).getVector()).
                isEqualTo(matrix3x.getVector());

        Matrix4x matrix4x = new Matrix4x(new double[]{3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3});
        Assertions.assertThat(matrix4x.createIdentityMatrix(3).getVector()).
                isEqualTo(matrix4x.getVector());
    }

    @Test
    public void isUnitMatrix() {
        Matrix3x matrix3x1 = new Matrix3x(new double[]{-0.5, 0, 0, 0, -0.5, 0, 0, 0, -0.5});
        Assertions.assertThat(Matrix.isUnitMatrix(matrix3x1, EPS)).isEqualTo(true);

        Matrix3x matrix3x2 = new Matrix3x(new double[]{-0.5, 6, 0, 0, -0.5, -0.5, 0, 0, -0.5});
        Assertions.assertThat(Matrix.isUnitMatrix(matrix3x2, EPS)).isEqualTo(false);

        Matrix3x matrix3x3 = new Matrix3x(new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
        Assertions.assertThat(Matrix.isUnitMatrix(matrix3x3, EPS)).isEqualTo(false);

        Matrix4x matrix4x1 = new Matrix4x(new double[]{3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3});
        Assertions.assertThat(Matrix.isUnitMatrix(matrix4x1, EPS)).isEqualTo(true);

        Matrix4x matrix4x2 = new Matrix4x(new double[]{3, 0, 0.4, 0, 0, 3, 0, 0, 6.5, 0, 3, 0, 0, 0, 0, 3});
        Assertions.assertThat(Matrix.isUnitMatrix(matrix4x2, EPS)).isEqualTo(false);
    }

    @Test
    public void getZeroMatrix() {
        Assertions.assertThat(Objects.requireNonNull(Matrix3x.getZeroMatrix()).getVector()).
                isEqualTo(new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Test
    public void transposeMatrix() {
        Matrix3x matrix3x = new Matrix3x(new double[]{0, 1.1, -3, 0, -4.5, 7.3, 6, 0.78, 1});
        Assertions.assertThat(Matrix.transposeMatrix(matrix3x).getVector()).
                isEqualTo(new double[]{0, 0, 6, 1.1, -4.5, 0.78, -3, 7.3, 1});

        Matrix4x matrix4x = new Matrix4x(new double[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
        Assertions.assertThat(Matrix.transposeMatrix(matrix4x).getVector()).
                isEqualTo(new double[]{0, 4, 8, 12, 1, 5, 9, 13, 2, 6, 10, 14, 3, 7, 11, 15});
    }

    @Test
    public void sumMatrix() throws Matrix.MatrixException {
        Matrix4x matrix4x1 = new Matrix4x(new double[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
        Matrix4x matrix4x2 = new Matrix4x(
                new double[]{0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14, -15});

        Assertions.assertThat(Matrix.sumMatrix(matrix4x1, Matrix4x.getZeroMatrix()).getVector()).
                isEqualTo(new double[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
        Assertions.assertThat(Matrix.sumMatrix(matrix4x1, matrix4x1).getVector()).
                isEqualTo(new double[]{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30});
        Assertions.assertThat(Matrix.sumMatrix(matrix4x1, matrix4x2).getVector()).
                isEqualTo(Matrix4x.getZeroMatrix().getVector());
    }

    @Test
    public void minusMatrix() throws Matrix.MatrixException {
        Matrix4x matrix4x1 = new Matrix4x(new double[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
        Matrix4x matrix4x3 = new Matrix4x(
                new double[]{0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14, -15});

        Assertions.assertThat(Matrix.minusMatrix(matrix4x1, Matrix4x.getZeroMatrix()).getVector()).
                isEqualTo(new double[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
        Assertions.assertThat(Matrix.minusMatrix(matrix4x1, matrix4x1).getVector()).
                isEqualTo(Matrix4x.getZeroMatrix().getVector());
        Assertions.assertThat(Matrix.minusMatrix(matrix4x1, matrix4x3).getVector()).
                isEqualTo(new double[]{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30});
    }

    @Test
    public void multiplicateOnValue() {
        Matrix3x matrix3x1 = new Matrix3x(new double[]{1, 2, 3, 4, 3, 2, 1, 0, 0});
        matrix3x1.multiplicateOnValue(3);
        Assertions.assertThat(matrix3x1.getVector()).isEqualTo(new double[]{3, 6, 9, 12, 9, 6, 3, 0, 0});

        Matrix3x matrix3x2 = new Matrix3x(new double[]{1, 2, 3, 4, 3, 2, 1, 0, 0});
        matrix3x2.multiplicateOnValue(0);
        Assertions.assertThat(matrix3x2.getVector()).isEqualTo(new double[matrix3x2.getLength()]);

        Matrix3x matrix3x3 = new Matrix3x(new double[]{1, 2, 3, 4, 3, -2, -1, 0, 0});
        Matrix3x result3x3 = new Matrix3x(new double[]{-0.73, -1.46, -2.19, -2.92, -2.19, 1.46, 0.73, 0, 0});
        matrix3x3.multiplicateOnValue(-0.73);
        Assertions.assertThat(matrix3x3.isEqualMatrix(result3x3)).isEqualTo(true);

        Matrix4x matrix4x1 = new Matrix4x(new double[]{1, 2, 3, 4, 3, 2, 1, 0, 0, 4, 3, 2, 1, 8, 4, 3});
        matrix4x1.multiplicateOnValue(3);
        Assertions.assertThat(matrix4x1.getVector()).
                isEqualTo(new double[]{3, 6, 9, 12, 9, 6, 3, 0, 0, 12, 9, 6, 3, 24, 12, 9});

        Matrix4x matrix4x2 = new Matrix4x(new double[]{1, 2, 3, 4, 3, 2, 1, 0, 0, 4, 3, 2, 1, 8, 4, 3});
        matrix4x2.multiplicateOnValue(0);
        Assertions.assertThat(matrix4x2.getVector()).isEqualTo(new double[matrix4x2.getLength()]);

        Matrix4x matrix4x3 = new Matrix4x(new double[]{1, 2, 3, 4, 3, -2, -1, 0, 0, 4, 3, 2, 1, 0, 4, 3});
        Matrix4x result4x3 = new Matrix4x(new double[]
                {-0.73, -1.46, -2.19, -2.92, -2.19, 1.46, 0.73, 0, 0, -2.92, -2.19, -1.46, -0.73, 0, -2.92, -2.19});
        matrix4x3.multiplicateOnValue(-0.73);
        Assertions.assertThat(matrix4x3.isEqualMatrix(result4x3)).isEqualTo(true);
    }

    @Test
    public void divideOnValue() throws Matrix.MatrixException {
        Matrix3x matrix3x1 = new Matrix3x(new double[]{3, 6, 9, 12, 9, 6, 3, 0, 0});
        matrix3x1.divideOnValue(3);
        Assertions.assertThat(matrix3x1.getVector()).
                isEqualTo(new double[]{1, 2, 3, 4, 3, 2, 1, 0, 0});

        Matrix3x matrix3x2 = new Matrix3x(new double[]{3, 6, 9, 12, 9, 6, 3, 0, 0});
        Throwable thrown2 = Assertions.catchThrowable(() -> {
            matrix3x2.divideOnValue(0);
        });
        Assertions.assertThat(thrown2).isInstanceOf(Matrix.MatrixException.class);
        Assertions.assertThat(thrown2.getMessage()).isNotBlank();
        Assertions.assertThat(thrown2.getMessage()).isEqualTo("Division by zero");

        Matrix4x matrix4x1 = new Matrix4x(new double[]{3, 6, 9, 12, 9, 6, -3, 0, 0, 2, 3, -4, 5, 1, 7, 3});
        Matrix4x result4x1 = new Matrix4x(new double[]
                {-15, -30, -45, -60, -45, -30, 15, 0, 0, -10, -15, 20, -25, -5, -35, -15});
        matrix4x1.divideOnValue(-0.2);
        Assertions.assertThat(matrix4x1.isEqualMatrix(result4x1)).isEqualTo(true);
    }

    @Test
    public void multiplicateOnVector() throws Matrix.MatrixException {
        Matrix3x matrix3x = new Matrix3x(new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        Vector3f vector3f = new Vector3f(new double[]{0, -0.5, 1.7});
        Vector3f result3f = new Vector3f(new double[]{4.1, 7.7, 11.3});
        Assertions.assertThat(matrix3x.multiplicateOnVector(vector3f).isEqual(result3f)).isEqualTo(true);

        Matrix4x matrix4x = new Matrix4x(new double[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
        Vector4f vector4f = new Vector4f(new double[]{0, 1, 2, 3});
        Assertions.assertThat(matrix4x.multiplicateOnVector(vector4f).getVector()).
                isEqualTo(new double[]{14, 38, 62, 86});
        Assertions.assertThat(matrix4x.multiplicateOnVector(matrix4x.getZeroVector(matrix4x.getSize())).getVector()).
                isEqualTo(matrix4x.getZeroVector(matrix4x.getSize()).getVector());

        Throwable thrown2 = Assertions.catchThrowable(() -> {
            matrix4x.multiplicateOnVector(vector3f);
        });
        Assertions.assertThat(thrown2).isInstanceOf(Matrix.MatrixException.class);
        Assertions.assertThat(thrown2.getMessage()).isNotBlank();
        Assertions.assertThat(thrown2.getMessage()).isEqualTo("Different sizes can't be multiplicated");
    }

    @Test
    public void multiplicateMatrices() throws Matrix.MatrixException {
        Matrix3x matrix3x1 = new Matrix3x(new double[]{0, 1, 2, 3, 4, 5, 6, 7, 8});
        Matrix3x matrix3x2 = new Matrix3x(new double[]{9, 1, 2, 3, 4, 5, 6, 7, 8});

        Assertions.assertThat(Matrix.multiplicateMatrices(matrix3x1, matrix3x2).getVector()).
                isEqualTo(new double[]{15, 18, 21, 69, 54, 66, 123, 90, 111});

        Matrix4x matrix4x1 = new Matrix4x(new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16});
        Throwable thrown2 = Assertions.catchThrowable(() -> {
            Matrix.multiplicateMatrices(matrix3x1, matrix4x1);
        });
        Assertions.assertThat(thrown2).isInstanceOf(Matrix.MatrixException.class);
        Assertions.assertThat(thrown2.getMessage()).isNotBlank();
        Assertions.assertThat(thrown2.getMessage()).isEqualTo("Different sizes can't be multiplicated");

        Matrix4x matrix4x2 = new Matrix4x(
                new double[]{0, 1.5, 0, -3.8, 4, 0, 6, -1.7, 8, 9, 1, 11, -12, 0, 4, 0.5});
        Matrix4x result = new Matrix4x(
                new double[]{-16, 28.5, 31, 27.8, -16, 70.5, 75, 51.8, -16, 112.5, 119, 75.8, -16, 154.5, 163, 99.8});
        Assertions.assertThat(Matrix.multiplicateMatrices(matrix4x1, matrix4x2).isEqualMatrix(result)).
                isEqualTo(true);
    }

    @Test
    public void getMatrixDeterminant() {
        Matrix3x matrix3x1 = new Matrix3x(new double[]{1, -2, 3, 4, 0, 6, -7, 8, 9});
        Assertions.assertThat(Matrix3x.getMatrixDeterminant(matrix3x1)).isEqualTo(204);

        Matrix3x matrix3x2 = new Matrix3x(new double[]{1, 0, 3, 4, 0, 6, -7, 0, 9});
        Assertions.assertThat(Matrix3x.getMatrixDeterminant(matrix3x2)).isEqualTo(0);

        Matrix4x matrix4x = new Matrix4x(new double[]{10, 0, 0, 0, 0, 4, 5, 2, 6, 2, 3, 3, 4, 1, 2, 1});
        Assertions.assertThat(Matrix4x.getMatrixDeterminant(matrix4x)).isEqualTo(-50);
    }

    @Test
    public void getDeterminant() {
        Matrix3x matrix3x1 = new Matrix3x(new double[]{2, 5, 7, 6, 3, 4, 5, -2, -3});
        Matrix3x matrix3x2 = new Matrix3x(new double[]{2, 0, 7, 6, 0, 4, 5, 0, -3});

        Assertions.assertThat(matrix3x1.getDeterminant()).isEqualTo(-1);
        Assertions.assertThat(matrix3x2.getDeterminant()).isEqualTo(0);
    }

    @Test
    public void getInverseMatrix() throws Exception {
        Matrix3x matrix3x1 = new Matrix3x(new double[]{2, 5, 7, 6, 3, 4, 5, -2, -3});
        Matrix3x inverseMatrix3x1 = new Matrix3x(new double[]{1, -1, 1, -38, 41, -34, 27, -29, 24});
        Assertions.assertThat(Matrix.getInverseMatrix(matrix3x1).isEqualMatrix(inverseMatrix3x1)).isEqualTo(true);
        Assertions.assertThat(Matrix.multiplicateMatrices(
                new Matrix3x(new double[]{2, 5, 7, 6, 3, 4, 5, -2, -3}), inverseMatrix3x1).isEqualMatrix(matrix3x1)).
                isEqualTo(true);

        Matrix3x matrix3x2 = new Matrix3x(new double[]{2, 0, 7, 6, 0, 4, 5, 0, -3});
        Throwable thrown = Assertions.catchThrowable(() -> {
            Matrix.getInverseMatrix(matrix3x2);
        });
        Assertions.assertThat(thrown).isInstanceOf(Matrix.MatrixException.class);
        Assertions.assertThat(thrown.getMessage()).isNotBlank();
        Assertions.assertThat(thrown.getMessage()).isEqualTo("Matrix hasn't inverse matrix");
    }

    @Test
    public void solutionByGaussMethod() throws Matrix.MatrixException {
        Matrix3x matrix3x1 = new Matrix3x(new double[]{3, 2, -5, 2, -1, 3, 1, 2, -1});
        Vector3f vector3f1 = new Vector3f(new double[]{-1, 13, 9});
        Assertions.assertThat(Matrix.solutionByGaussMethod(matrix3x1, vector3f1).getVector()).isEqualTo(new double[]{3, 5, 4});

        Matrix3x matrix3x = new Matrix3x(new double[]{2, 1, -1, -3, -1, 2, -2, 1, 2});
        Vector3f vector3f = new Vector3f(new double[]{8, -11, -3});
        Assertions.assertThat(Matrix.solutionByGaussMethod(matrix3x, vector3f).getVector()).isEqualTo(new double[]{2, 3, -1});

        //Бесконечно много решений, но найдём частное решение
        Matrix3x matrix3x2 = new Matrix3x(new double[]{1, 1, -1, 3, 2, -5, 3, 1, -7});
        Vector3f vector3f2 = new Vector3f(new double[]{4, 7, 2});
        Assertions.assertThat(Matrix.solutionByGaussMethod(matrix3x2, vector3f2).getVector()).
                isEqualTo(new double[]{2, 3, 1});

        //Нет решений
        //Почему-то был закомментирован
        Matrix3x matrix3x3 = new Matrix3x(new double[]{0, 1, 0, 0, 1, 0, 0, 0, 0});
        Vector3f vector3f3 = new Vector3f(new double[]{8, 6, 3});
        Throwable thrown = Assertions.catchThrowable(() -> {
            Matrix.solutionByGaussMethod(matrix3x3, vector3f3);
        });
        Assertions.assertThat(thrown).isInstanceOf(Matrix.MatrixException.class);
        Assertions.assertThat(thrown.getMessage()).isNotBlank();
        Assertions.assertThat(thrown.getMessage()).isEqualTo("There are no solutions");

        Matrix3x matrix3x4 = new Matrix3x(new double[]{1, 0, 0, 0, 0, 0, 2, 0, 0});
        Vector3f vector3f4 = new Vector3f(new double[]{8, 0, 0});
        Throwable thrown2 = Assertions.catchThrowable(() -> {
            Matrix.solutionByGaussMethod(matrix3x4, vector3f4);
        });
        Assertions.assertThat(thrown2).isInstanceOf(Matrix.MatrixException.class);
        Assertions.assertThat(thrown2.getMessage()).isNotBlank();
        Assertions.assertThat(thrown2.getMessage()).isEqualTo("There are no solutions");

        Matrix4x matrix4x  = new Matrix4x(new double[]{1, -1, 2, -3, 1, 4, -1, -2, 1, -4, 3, -2, 1, -8, 5, -2});
        Vector4f vector4f = new Vector4f(new double[]{1, -2, -2, -2});
        Assertions.assertThat(Matrix.solutionByGaussMethod(matrix4x, vector4f).getVector()).
                isEqualTo(new double[]{-8, 4, 8, 1});
    }
}

