package chapter32;

/**
 * @author Weiduo
 * @date 2019/8/10 - 7:15 PM
 */
public class FiniteAutomata {



    static void search(char[] text, char[] pattern) {
        int n = text.length, m = pattern.length;

        int[][] TF = new int[m + 1][256];

        computeTF(pattern, TF);

        int i, state = 0;

        for(i = 0; i < n; i++) {
            state = TF[state][text[i]];
            if(state == m) System.out.println("Pattern found "
                + "at index " + (i - m + 1));
        }
    }

    static void computeTF(char[] pattern, int TF[][]) {
        for(int state = 0; state <= pattern.length; state++) {
            for(int x = 0; x < 256; x++) {
                TF[state][x] = getNextState(pattern, pattern.length, state, x);
            }
        }
    }

    static int getNextState(char[] pattern, int M, int state, int x) {
        if(state < M && x == pattern[state]) return state + 1;

        int ns, i;

        for(ns = state; ns > 0; ns--) {
            if(pattern[ns - 1] == x) {
                for(i = 0; i < ns - 1; i++) {
                    if(pattern[i] != pattern[state - ns + 1 + i]) break;
                }
                if (i == ns-1) return ns;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        char[] text = "AABAACAADAABAAABAA".toCharArray();
        char[] pattern = "AABA".toCharArray();
        search(text , pattern);
    }
}
