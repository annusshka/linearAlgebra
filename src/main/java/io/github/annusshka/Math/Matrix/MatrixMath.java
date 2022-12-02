package io.github.annusshka.Math.Matrix;

public class MatrixMath {

    /*
    private boolean isMatrixMinus = false;

    public boolean isEqualMatrix(Matrix2 m1, Matrix2 m2) {
        return m1.length() == m2.length();
    }


    public static boolean isUnitMatrix(Matrix2 matrix) {
        /*
        double firstElement = matrix.Matrix()[0][0];
        for (int indexStroke = 0; indexStroke < matrix.size(); indexStroke++) {
            for (int indexS = 0; indexS < matrix.size(); indexS++) {
                if (indexStroke == indexS && matrix.Matrix()[indexStroke][indexS] != firstElement) {
                    return false;
                } else if (indexStroke != indexS && matrix.Matrix()[indexStroke][indexS] != 0) {
                    return false;
                }
            }
        }



        int size = matrix.size();;
        int indexRow = 0;
        int indexCol = 0;

        double firstElement = matrix.vector()[0];
        for (int index = 0; index < size * size; index++) {

            if (index == size * indexRow + indexCol && matrix.vector()[index] != firstElement) {
                return false;
            } else if (index == size * indexRow + indexCol) {
                indexRow++;
                indexCol++;
            } else if (matrix.vector()[index] != 0){
                return false;
            }
        }

        /*
        int index = 0;
        double firstElement = matrix.vector()[index++]; //проверить работает ли индекс правильно
        for (int k = 1; index < matrix.length(); index++) {
            if (index == matrix.size() * k + 1) {
                if (matrix.vector()[index] != firstElement) {
                    return false;
                }
                k++;
            } else if (matrix.vector()[index] != 0) {
                return false;
            }
        }



        return true;
    }

    /**
     * Конструктор единичной матрицы, умноженной на константу value
     * @param value значение константы, на которую умножают единичную матрицу
     * @param matrix
     * @throws Exception, если размер матрицы не соответствует 3х3 или 4х4
     * @return


    public static Matrix2 makeUnitMatrix(double value, Matrix2 matrix) throws Exception{
        int size = matrix.size();
        if (size != 3 && size != 4) {
            throw new Exception("Заданный размер матрицы не соответствует 3х3 или 4х4");
        }

        int indexRow = 0;
        int indexCol = 0;

        for (int index = 0; index < size * size; index++) {

            if (index == size * indexRow + indexCol) {
                matrix.vector()[index] = value;
                indexRow++;
                indexCol++;
            }
        }

        return matrix;
    }

    /*
    public static Matrix makeZeroMatrix(int size) throws Exception{
        if (size == 3) {
            return new Matrix3x();
        } else if (size == 4) {
            return new Matrix4x();
        } else {
            throw new Exception("Другой размер");
        }
    }



    public static Matrix2 transposeMatrix(Matrix2 matrix) {
        int strokeLength = matrix.length() / matrix.size();
        int k = 0;
        int indexCol = 0;
        int indexRow = 0;

        for (int index = 0; index < matrix.length(); index++) {
            indexCol = index % matrix.size();

            if (indexCol == 0) {
                index += ++k;
                if (index >= matrix.length()) {
                    return matrix;
                }
                indexRow = index / matrix.size();
                indexCol = index % matrix.size();
            }

            double value = matrix.vector()[index];
            matrix.vector()[index] =
                    matrix.vector()[strokeLength * indexCol + indexRow];
            matrix.vector()[strokeLength * indexCol + indexRow] = value;
        }

        return matrix;
    }

    public static double[] solutionByGaussMethod(Matrix2 matrix, Vector2 vector) {
        double coeff;
        int indexCol, indexRow, i, j;

        for (int index = 0; index < matrix.size() - 1; index++) {
            indexCol = index;
            indexRow = index + 1;

            swapRows(matrix, vector, index);

            coeff = getCoefficient(1, matrix.vector()[index * matrix.size() + index]);

            while (indexCol < matrix.size()) {
                matrix.vector()[index * matrix.size() + indexCol] *= coeff;
                indexCol++;
            }
            vector.vector()[index] *= coeff;

            /*
            if (matrix.vector()[i] == 0) {
                while (indexRow1 < matrix.size()) {
                    if (matrix.vector()[indexRow1 * matrix.size() + index] != 0) {
                        matrix.vector()[i] = matrix.vector()[indexRow1 * matrix.size() + index];
                        matrix.vector()[indexRow1 * matrix.size() + index] = 0;
                        break;
                    }
                    indexRow1++;
                    if (indexRow1 == matrix.size()) {
                        break;
                    }
                }
            }


            if (matrix.vector()[index * matrix.size() + index] == 0) {
                while (indexRow1 < matrix.size()) {
                    if (matrix.vector()[indexRow1 * matrix.size() + index] != 0) {
                        matrix.vector()[index * matrix.size() + index] = matrix.vector()[indexRow1 * matrix.size() + index];
                        matrix.vector()[indexRow1 * matrix.size() + index] = 0;
                    }
                    indexRow1++;
                }
            }



            while (indexRow < matrix.size()) {
                indexCol = index;
                //т.к. matrix.vector()[index * matrix.size() + index] - минимальный эл., то этот столбец не надо преобразовывать
                if (matrix.vector()[index * matrix.size() + index] == 0) {
                    break;
                }

                coeff = getCoefficient(matrix.vector()[indexRow * matrix.size() + index],
                        matrix.vector()[index * matrix.size() + index]);

                while (indexCol < matrix.size()) {
                    i = indexRow * matrix.size() + indexCol;
                    j = index * matrix.size() + indexCol;
                    matrix.vector()[i] = matrix.vector()[i] - matrix.vector()[j] * coeff;
                    indexCol++;
                }
                vector.vector()[indexRow] = vector.vector()[indexRow] - vector.vector()[index] * coeff;
                indexRow++;
            }
        }
        return method(matrix, vector);

        /*
        for (int indexRow = 0; indexRow < matrix.size(); ++indexRow) { //первый indexRow должен быть 1
            int indexCol = 0;
            coeff = getCoefficient(matrix.vector()[indexCol], matrix.vector()[indexRow % matrix.size()]);

            while (indexCol < matrix.size()) {
                index = indexRow * matrix.size() + indexCol;
                matrix.vector()[index] = matrix.vector()[index] * coeff - matrix.vector()[indexCol];
                vector.vector()[indexRow] = vector.vector()[indexRow] * coeff - vector.vector()[0];
                indexCol++;
            }
        }


    }

    private static void swapRows(Matrix2 matrix, Vector2 vector, int index) {
        int indexCol = index, changingIndexRow = -1;
        double changingValue;
        double minValue = Math.abs(matrix.vector()[index * matrix.size() + index]);

        for (int indexRow = index + 1; indexRow < matrix.size(); indexRow++) {
            if (matrix.vector()[indexRow * matrix.size() + index] != 0 &&
                    (minValue == 0 || Math.abs(matrix.vector()[indexRow * matrix.size() + index]) < minValue)) {
                minValue = matrix.vector()[indexRow * matrix.size() + index];
                changingIndexRow = indexRow;
            }
        }

        if (changingIndexRow != -1) {
            while (indexCol < matrix.size()) {
                changingValue = matrix.vector()[index * matrix.size() + indexCol];
                matrix.vector()[index * matrix.size() + indexCol] =
                        matrix.vector()[changingIndexRow * matrix.size() + indexCol];
                matrix.vector()[changingIndexRow * matrix.size() + indexCol] = changingValue;
                indexCol++;
            }
            changingValue = vector.vector()[index];
            vector.vector()[index] = vector.vector()[changingIndexRow];
            vector.vector()[changingIndexRow] = changingValue;
        }

        /*
        for (int indexRow = index; indexRow < matrix.size(); indexRow++) {
            if (matrix.vector()[indexRow * matrix.size() + index] == 0) {
                changingIndexRow = indexRow;
            }
            if (matrix.vector()[indexRow * matrix.size() + index] != 0 && changingIndexRow != -1) {
                while (indexCol < matrix.size()) {
                    changingValue = matrix.vector()[indexRow * matrix.size() + indexCol];
                    matrix.vector()[indexRow * matrix.size() + indexCol] = matrix.vector()[changingIndexRow * matrix.size() + indexCol];
                    matrix.vector()[changingIndexRow * matrix.size() + indexCol] = changingValue;
                    indexCol++;
                }
            }


    }


            solutionVector[index] = (vector.vector()[index] - sum) /
                    matrix.vector()[index * matrix.size() + index];
        }

        return solutionVector;
    }



    //Возможно деление на ноль!!!!
    //Если первое значение 0, то надо будет менять местами строки, если второе
    private static double getCoefficient(double value1, double value2) {
        return value1 / value2;
    }

    /*
    public boolean isZeroMatrix(Matrix matrix) {
        for (double[] element : matrix.Matrix()) {
            for (double elem : element) {
                if (elem != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public Matrix sumMatrix(Matrix m1, Matrix m2) {
        if (isEqualMatrix(m1, m2)) {
            for (int indexStroke = 0; indexStroke < m1.size(); indexStroke++) {
                for (int indexS = 0; indexS < m2.size(); indexS++) {
                    if (isMatrixMinus) {
                        m2.Matrix()[indexStroke][indexS] *= -1;
                    }
                    m1.Matrix()[indexStroke][indexS] = m1.Matrix()[indexStroke][indexS] + m2.Matrix()[indexStroke][indexS];
                }
            }
        }

        return m1;

        if (isEqualMatrix(m1, m2)) {
            for (int index = 0; index < m1.length(); index++) {
                if (isMatrixMinus) {
                    m2.vector()[index] *= -1;
                }
                m1.vector()[index] += m2.vector()[index];
            }
            isMatrixMinus = false;
        }
        return m1;


    }

    public Matrix minusMatrix(Matrix m1, Matrix m2) {
        isMatrixMinus = true;
        return sumMatrix(m1, m2);
    }

    public static Vector multiplicationMatrixOnVector(Vector vector, Matrix matrix) throws Exception {
        Vector vector1 = vector;


        if (vector.size() == matrix.size()) {
            int index, indexS = 0;
            for (int indexStroke = 0; indexStroke < matrix.length(); ) {
                index = 0;

                while (index < matrix.size()) {
                    vector1.vector()[indexS] += matrix.vector()[indexStroke] * vector.vector()[index];
                    index++;
                    indexStroke++;
                }

                indexS++;
            }
        }

        return vector1;
    }

    public Matrix multiplicationMatrixOnMatrix(Matrix m1, Matrix m2) {
        if (isEqualMatrix(m1, m2)) {

        }

        return m1;
    }

     */
}