package chapter02;

public class HornerPolynomial {
    public static int hornerPolynomial(int[] coefficient, int n, int x) {
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = res * x + coefficient[i];
        }
        return res;
    }

    public static void main(String[] args) {
        // Evaluate value of 2x3 - 6x2 + 2x - 1 for x = 3
        // coefficient[] = {2, -6, 2, -1}, x = 3
        int[] coefficient = { 2, -6, 2, -1 };
        int x = 3;
        int n = coefficient.length;
        int res = hornerPolynomial(coefficient, n, x);
        System.out.println("res: " + res);
    }
}