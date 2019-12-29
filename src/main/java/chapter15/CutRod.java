package chapter15;

/**
 * @author Weiduo
 * @date 2019/8/31 - 3:49 PM
 */
public class CutRod {

    static Integer[] memo;
    private static int topDown(int[] price, int length) {
        memo = new Integer[length + 1];
        return helper(price, length);
    }

    private static int helper(int[] price, int length) {
        if(memo[length] != null) return memo[length];
        if(length == 0) return 0;
        if(length == 1) return price[length - 1];

        int res = 0;

        for(int i = 1; i <= length; i++) {
            res = Math.max(res, price[i - 1] + helper(price, length - i));
        }

        return memo[length] = res;
    }

    private static int bottomUp(int[] price, int length) {
        int[] dp = new int[length + 1];
        dp[0] = 0;

        for(int i = 1; i <= length; i++) {
            for(int j = 1; j <= i; j++) {
                dp[i] = Math.max(dp[i], price[j - 1] + dp[i - j]);
            }
        }

        return dp[length];
    }



    public static void main(String[] args) {
        int[] price = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        int length = 10;

        int res1 = topDown(price, length);

        System.out.println("top down res: " + res1);


        int res2 = bottomUp(price, length);

        System.out.println("bottom up res: " + res2);
    }

}
