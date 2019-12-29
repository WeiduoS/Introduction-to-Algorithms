package chapter31;

/**
 * @author Weiduo
 * @date 2019/10/30 - 12:47 AM
 */
public class GreatestCommonDivisor {
    static int gcd(int a, int b) {
        if(a == 0) return b;
        return gcd(b % a, a);
    }

    public static void main(String[] args) {
        int res = gcd( 24, 56);
        System.out.println("res: " + res);
    }
}
