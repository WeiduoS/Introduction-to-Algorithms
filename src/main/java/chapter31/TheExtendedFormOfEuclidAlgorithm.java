package chapter31;

/**
 * @author Weiduo
 * @date 2019/10/30 - 1:04 AM
 */
public class TheExtendedFormOfEuclidAlgorithm {
    class Int {
        int v;
        Int(){};
        Int(int v) {
            this.v = v;
        }
    }

    private int gcdExtend(int a, int b, Int x, Int y) {
        if(b == 0) {
            x.v = 1;
            y.v = 0;
            return a;
        }else{
            int res = gcdExtend(b, a % b, x, y);
            int t = x.v;
            x.v = y.v;

            y.v = t - (a / b * y.v);

            return res;
        }
    }

    public static void main(String[] args) {
        TheExtendedFormOfEuclidAlgorithm obj = new TheExtendedFormOfEuclidAlgorithm();
        Int x = obj.new Int(1), y = obj.new Int(1);
        int res = obj.gcdExtend(4, 11, x, y);

        System.out.println("res: " + res);
        System.out.println("x: " + x.v + ", y: " + y.v);

    }
}
