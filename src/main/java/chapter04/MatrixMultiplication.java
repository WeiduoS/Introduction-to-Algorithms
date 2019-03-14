package chapter04;

import java.util.*;

public class MatrixMultiplication {
    public static int[][] matrixProductIter(int[][] A, int[][] B) {
        int[][] C = new int[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < A[0].length; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    public static int[][] matrixProductRec(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        if (n == 1) {
            C[0][0] = A[0][0] * B[0][0];
        } else {
            int[][] A11 = new int[n / 2][n / 2];
            int[][] A12 = new int[n / 2][n / 2];
            int[][] A21 = new int[n / 2][n / 2];
            int[][] A22 = new int[n / 2][n / 2];
            copyToMatrix(A, 0, 0, n / 2, n / 2, A11);
            copyToMatrix(A, 0, n / 2, n / 2, n / 2, A12);
            copyToMatrix(A, n / 2, 0, n / 2, n / 2, A21);
            copyToMatrix(A, n / 2, n / 2, n / 2, n / 2, A22);

            int[][] B11 = new int[n / 2][n / 2];
            int[][] B12 = new int[n / 2][n / 2];
            int[][] B21 = new int[n / 2][n / 2];
            int[][] B22 = new int[n / 2][n / 2];
            copyToMatrix(B, 0, 0, n / 2, n / 2, B11);
            copyToMatrix(B, 0, n / 2, n / 2, n / 2, B12);
            copyToMatrix(B, n / 2, 0, n / 2, n / 2, B21);
            copyToMatrix(B, n / 2, n / 2, n / 2, n / 2, B22);

            int[][] C11 = new int[n / 2][n / 2];
            int[][] C12 = new int[n / 2][n / 2];
            int[][] C21 = new int[n / 2][n / 2];
            int[][] C22 = new int[n / 2][n / 2];

            add(matrixProductRec(A11, B11), matrixProductRec(A12, B21), C11);
            add(matrixProductRec(A11, B12), matrixProductRec(A12, B22), C12);
            add(matrixProductRec(A21, B11), matrixProductRec(A22, B21), C21);
            add(matrixProductRec(A22, B22), matrixProductRec(A21, B12), C22);

            copyFromMatrix(C, 0, 0, n / 2, n / 2, C11);
            copyFromMatrix(C, 0, n / 2, n / 2, n / 2, C12);
            copyFromMatrix(C, n / 2, 0, n / 2, n / 2, C21);
            copyFromMatrix(C, n / 2, n / 2, n / 2, n / 2, C22);
        }
        return C;
    }

    public static void copyToMatrix(int src[][], int rowS, int colS, int rowLen, int colLen, int dest[][]) {
        for (int i = rowS; i < rowS + rowLen; i++) {
            for (int j = colS; j < colS + colLen; j++) {
                dest[i - rowS][j - colS] = src[i][j];
            }
        }
    }

    public static void copyFromMatrix(int[][] dest, int rowS, int colS, int rowLen, int colLen, int[][] src) {
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                dest[i + rowS][j + colS] = src[i][j];
            }
        }
    }

    public static void add(int[][] A, int[][] B, int[][] C) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
    }

    public static void sub(int[][] A, int[][] B, int[][] C) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
    }

    public static int[][] strassenMatrixProduct(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        if (n == 1) {
            C[0][0] = A[0][0] * B[0][0];
        } else {
            int[][] A11 = new int[n / 2][n / 2];
            int[][] A12 = new int[n / 2][n / 2];
            int[][] A21 = new int[n / 2][n / 2];
            int[][] A22 = new int[n / 2][n / 2];

            copyToMatrix(A, 0, 0, n / 2, n / 2, A11);
            copyToMatrix(A, 0, n / 2, n / 2, n / 2, A12);
            copyToMatrix(A, n / 2, 0, n / 2, n / 2, A21);
            copyToMatrix(A, n / 2, n / 2, n / 2, n / 2, A22);

            int[][] B11 = new int[n / 2][n / 2];
            int[][] B12 = new int[n / 2][n / 2];
            int[][] B21 = new int[n / 2][n / 2];
            int[][] B22 = new int[n / 2][n / 2];

            copyToMatrix(B, 0, 0, n / 2, n / 2, B11);
            copyToMatrix(B, 0, n / 2, n / 2, n / 2, B12);
            copyToMatrix(B, n / 2, 0, n / 2, n / 2, B21);
            copyToMatrix(B, n / 2, n / 2, n / 2, n / 2, B22);

            int[][] S1 = new int[n / 2][n / 2];
            int[][] S2 = new int[n / 2][n / 2];
            int[][] S3 = new int[n / 2][n / 2];
            int[][] S4 = new int[n / 2][n / 2];
            int[][] S5 = new int[n / 2][n / 2];
            int[][] S6 = new int[n / 2][n / 2];
            int[][] S7 = new int[n / 2][n / 2];
            int[][] S8 = new int[n / 2][n / 2];
            int[][] S9 = new int[n / 2][n / 2];
            int[][] S10 = new int[n / 2][n / 2];

            sub(B11, B12, S1);
            add(A11, A12, S2);
            add(A21, A22, S3);
            sub(B21, B11, S4);
            add(A11, A22, S5);
            add(B11, B22, S6);
            sub(A12, A22, S7);
            add(B21, B22, S8);
            sub(A11, A21, S9);
            add(B11, B12, S10);

            int[][] P1 = new int[n / 2][n / 2];
            int[][] P2 = new int[n / 2][n / 2];
            int[][] P3 = new int[n / 2][n / 2];
            int[][] P4 = new int[n / 2][n / 2];
            int[][] P5 = new int[n / 2][n / 2];
            int[][] P6 = new int[n / 2][n / 2];
            int[][] P7 = new int[n / 2][n / 2];

            P1 = strassenMatrixProduct(A11, S1);
            P2 = strassenMatrixProduct(S2, B22);
            P3 = strassenMatrixProduct(S3, B11);
            P4 = strassenMatrixProduct(A22, S4);
            P5 = strassenMatrixProduct(S5, S6);
            P6 = strassenMatrixProduct(S7, S8);
            P7 = strassenMatrixProduct(S9, S10);

            int[][] C11 = new int[n / 2][n / 2];
            int[][] C12 = new int[n / 2][n / 2];
            int[][] C21 = new int[n / 2][n / 2];
            int[][] C22 = new int[n / 2][n / 2];

            // int[][] temp = new int[n / 2][n / 2];
            add(P4, P5, C11);
            sub(C11, P2, C11);
            add(C11, P6, C11);

            add(P1, P2, C12);

            add(P3, P4, C21);

            add(P1, P5, C22);
            sub(C22, P3, C22);
            sub(C22, P7, C22);

            copyFromMatrix(C, 0, 0, n / 2, n / 2, C11);
            copyFromMatrix(C, 0, n / 2, n / 2, n / 2, C12);
            copyFromMatrix(C, n / 2, 0, n / 2, n / 2, C21);
            copyFromMatrix(C, n / 2, n / 2, n / 2, n / 2, C22);

        }
        return C;
    }

    public static void main(String[] args) {
        int[][] A = { { 43, 34, 6, 3 }, { 8, 754, 8, 76 }, { 67, 34, 56, 32 }, { 45, 23, 12, 90 } };
        int[][] B = { { 3, 432, 6, 7 }, { 62, 54, 56, 88 }, { 23, 72, 5, 72 }, { 83, 34, 98, 89 } };

        int[][] C = matrixProductIter(A, B);
        System.out.println("Iterative solution: ");
        for (int[] row : C) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println(" ");

        C = matrixProductRec(A, B);
        System.out.println("Recursive solution: ");
        for (int[] row : C) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println(" ");

        C = strassenMatrixProduct(A, B);
        System.out.println("Strassen solution: ");
        for (int[] row : C) {
            System.out.println(Arrays.toString(row));
        }
    }
}