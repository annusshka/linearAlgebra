package io.github.annusshka;

import io.github.annusshka.Math.Vector.Vector;
import io.github.annusshka.Math.Vector.Vector2f;
import io.github.annusshka.Math.Vector.Vector3f;
import io.github.annusshka.Math.Vector.Vector4f;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class VectorTest {

    static final float EPS = 1e-7f;

    @Test
    public void isEqual() {
        Vector2f vector2f1 = new Vector2f(new double[]{1, 2});
        Vector2f vector2f2 = new Vector2f(new double[]{1, 3});
        Vector2f vector2f3 = new Vector2f(new double[]{1, 2});

        Assertions.assertThat(vector2f1.isEqual(vector2f2)).isEqualTo(false);
        Assertions.assertThat(vector2f1.isEqual(vector2f3)).isEqualTo(true);

        Vector3f vector3f1 = new Vector3f(new double[]{1, 2, 3});
        Vector3f vector3f2 = new Vector3f(new double[]{1, 2, 4});
        Vector3f vector3f3 = new Vector3f(new double[]{1, 2, 3});

        Assertions.assertThat(vector3f1.isEqual(vector3f2)).isEqualTo(false);
        Assertions.assertThat(vector3f1.isEqual(vector3f3)).isEqualTo(true);

        Vector3f vector4f1 = new Vector3f(new double[]{1, 2, 3, 4});
        Vector3f vector4f2 = new Vector3f(new double[]{1, 2, 4, 3});
        Vector3f vector4f3 = new Vector3f(new double[]{1, 2, 3, 4});

        Assertions.assertThat(vector4f1.isEqual(vector4f2)).isEqualTo(false);
        Assertions.assertThat(vector4f1.isEqual(vector4f3)).isEqualTo(true);
    }

    @Test
    public void sumVectors() throws Vector.VectorException {
        Vector2f vector2f1 = new Vector2f(new double[]{3, 5});
        Vector2f vector2f2 = new Vector2f(new double[]{-3, 5});
        Vector3f vector3f1 = new Vector3f(new double[]{1, 2, 3});
        Vector3f vector3f2 = new Vector3f(new double[]{1, 2, 4});
        Vector4f vector4f1 = new Vector4f(new double[]{1, 2, 3, 4});

        Assertions.assertThat(Vector.sumVector(vector2f1, vector2f2).getVector()).
                isEqualTo(new double[]{0, 10});

        Throwable thrown = Assertions.catchThrowable(() -> {
            Vector.sumVector(vector3f1, vector4f1);
        });
        Assertions.assertThat(thrown).isInstanceOf(Vector.VectorException.class);
        Assertions.assertThat(thrown.getMessage()).isNotBlank();
        Assertions.assertThat(thrown.getMessage()).
                isEqualTo("Vectors of different sizes can't be summed");

        Assertions.assertThat(Vector.sumVector(vector3f1, vector3f2).getVector()).
                isEqualTo(new double[]{2, 4, 7});
        Assertions.assertThat(Vector.minusVector(vector3f2, vector3f1).getVector()).
                isEqualTo(new double[]{0, 0, 1});
    }

    @Test
    public void sumWithConstant() {
        Vector2f vector2f = new Vector2f(new double[]{3, 5});
        Vector3f vector3f = new Vector3f(new double[]{1, 2, 3});
        Vector4f vector4f = new Vector4f(new double[]{1, 2, 3, 4});

        Assertions.assertThat(vector2f.sumWithConstant(5.3).getVector()).isEqualTo(new double[]{8.3, 10.3});
        Assertions.assertThat(vector3f.sumWithConstant(0).getVector()).isEqualTo(new double[]{1, 2, 3});
        Assertions.assertThat(vector4f.sumWithConstant(-2).getVector()).isEqualTo(new double[]{-1, 0, 1, 2});
    }

    @Test
    public void minusWithConstant() {
        Vector2f vector2f = new Vector2f(new double[]{3, 5});
        Vector2f resultVector = new Vector2f(new double[]{-0.3, 1.7});
        Assertions.assertThat(vector2f.minusWithConstant(3.3).isEqual(resultVector)).
                isEqualTo(true);

        Vector3f vector3f = new Vector3f(new double[]{1, 2, 3});
        Assertions.assertThat(vector3f.minusWithConstant(0).getVector()).isEqualTo(new double[]{1, 2, 3});

        Vector4f vector4f = new Vector4f(new double[]{1, 2, 3, 4});
        Assertions.assertThat(vector4f.minusWithConstant(-2).getVector()).isEqualTo(new double[]{3, 4, 5, 6});
    }

    @Test
    public void multiplicateVectorOnConstant() {
        Vector2f vector2f = new Vector2f(new double[]{3, 4});
        Assertions.assertThat(vector2f.multiplicateVectorOnConstant(0).getVector()).
                isEqualTo(new double[]{0, 0});

        Vector3f vector3f = new Vector3f(new double[]{2, 3, 4});
        Vector3f resultVector = new Vector3f(new double[]{4.8, 7.2, 9.6});
        Assertions.assertThat(vector3f.multiplicateVectorOnConstant(2.4).isEqual(resultVector)).
                isEqualTo(true);

        Vector4f vector4f = new Vector4f(new double[]{-4, 3, 4, -5});
        Assertions.assertThat(vector4f.multiplicateVectorOnConstant(-3).getVector()).
                isEqualTo(new double[]{12, -9, -12, 15});
    }

    @Test
    public void divideVectorOnConstant() throws Vector.VectorException {
        Vector2f vector2f = new Vector2f(new double[]{3, 4});
        Throwable thrown = Assertions.catchThrowable(() -> {
            vector2f.divideVectorOnConstant(0);
        });
        Assertions.assertThat(thrown).isInstanceOf(Vector.VectorException.class);
        Assertions.assertThat(thrown.getMessage()).isNotBlank();
        Assertions.assertThat(thrown.getMessage()).isEqualTo("Division by zero");

        Vector3f vector3f = new Vector3f(new double[]{2, 3, 4});
        Vector3f resultVector1 = new Vector3f(new double[]{0.8333333, 1.25, 1.6666666});
        Assertions.assertThat(vector3f.divideVectorOnConstant(2.4).isEqual(resultVector1)).isEqualTo(true);

        Vector4f vector4f = new Vector4f(new double[]{-4, 3, 4, -5});
        Vector4f resultVector2 = new Vector4f(new double[]{0.4, -0.3, -0.4, 0.5});
        Assertions.assertThat(vector4f.divideVectorOnConstant(-10).isEqual(resultVector2)).
                isEqualTo(true);
    }

    @Test
    public void getVectorLength() {
        Vector2f vector2f = new Vector2f(new double[]{3, 4});
        Assertions.assertThat(vector2f.getVectorLength()).isEqualTo(5);

        Vector3f vector3f = new Vector3f(new double[]{0, 3.3, 4.1});
        double result1 = 5.2630789;
        Assertions.assertThat(Math.abs(vector3f.getVectorLength() - result1) < EPS).isEqualTo(true);

        Vector4f vector4f = new Vector4f(new double[]{-4, 3, 4, -5});
        double result2 = 8.1240384;
        Assertions.assertThat(Math.abs(vector4f.getVectorLength() - result2) < EPS).isEqualTo(true);
    }

    @Test
    public void normalizeVector() throws Vector.VectorException {
        Vector2f vector2f = new Vector2f(new double[]{0, 0});
        Throwable thrown = Assertions.catchThrowable(vector2f::normalizeVector);
        Assertions.assertThat(thrown).isInstanceOf(Vector.VectorException.class);
        Assertions.assertThat(thrown.getMessage()).isNotBlank();
        Assertions.assertThat(thrown.getMessage()).isEqualTo("Division by zero");

        Vector3f vector3f = new Vector3f(new double[]{0, 3.3, 4.1});
        Vector3f resultVector1 = new Vector3f(new double[]{0, 0.6270094, 0.7790116});
        Assertions.assertThat(vector3f.normalizeVector().isEqual(resultVector1)).isEqualTo(true);

        Vector4f vector4f = new Vector4f(new double[]{-8, 3, 2, -2});
        Vector4f resultVector2 = new Vector4f(new double[]{-0.8888888, 0.3333333, 0.2222222, -0.2222222});
        Assertions.assertThat(vector4f.normalizeVector().isEqual(resultVector2)).isEqualTo(true);
    }

    @Test
    public void multiplicateScalarVector() {
        Vector2f vector2f1 = new Vector2f(new double[]{0, 1});
        Vector2f vector2f2 = new Vector2f(new double[]{5, 6});
        Assertions.assertThat(Vector.multiplicateScalarVector(vector2f1, vector2f2)).
                isEqualTo(6);

        Vector3f vector3f1 = new Vector3f(new double[]{0, 3.3, 4.1});
        Vector3f vector3f2 = new Vector3f(new double[]{-7.3, -1, 3.4});
        double result1 = 10.64;
        Assertions.assertThat(Math.abs(Vector.multiplicateScalarVector(vector3f1, vector3f2) - result1) < EPS).
                isEqualTo(true);

        Vector4f vector4f1 = new Vector4f(new double[]{-8, 3, 2, -2});
        Vector4f vector4f2 = new Vector4f(new double[]{0.7, 3, 0, 1});
        double result2 = 1.4;
        Assertions.assertThat(Math.abs(Vector.multiplicateScalarVector(vector4f1, vector4f2) - result2) < EPS).
                isEqualTo(true);
    }

    @Test
    public void multiplicationVector() {
        Vector3f vector3f1 = new Vector3f(new double[]{-2, 3, 0});
        Vector3f vector3f2 = new Vector3f(new double[]{-2, 0, 6});

        Assertions.assertThat(Vector3f.multiplicateVectors(vector3f1, vector3f2).getVector()).
                isEqualTo(new double[]{18, 12, 6});
    }
}
