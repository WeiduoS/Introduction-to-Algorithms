package chapter32;

import java.util.Arrays;

/**
 * @author Weiduo
 * @date 2019/8/10 - 5:48 PM
 */
public class KnuthMorrisPratt {
    /*
     *  a b c a b y
     * [0 0 0 1 2 0]
     * */
    private static int[] computePrefix(char[] pattern) {
        int[] prefix = new int[pattern.length];
        int index = 0;

        for(int i = 1; i < pattern.length;) {
            if(pattern[i] == pattern[index]) {
                prefix[i] = index + 1;
                index++;
                i++;
            }else{
                if(index != 0) {
                    index = prefix[index - 1];
                }else{
                    prefix[i] = 0;
                    i++;
                }
            }
        }
        return prefix;
    }


    public static boolean KMP(char[] text, char[] pattern) {
        int[] prefix = computePrefix(pattern);
        System.out.println(Arrays.toString(prefix));
        int i = 0, j = 0;

        while(i < text.length && j < pattern.length) {
            if(text[i] == pattern[j]) {
                i++;
                j++;
            }else{
                if(j != 0) {
                    j = prefix[j - 1];
                }else{
                    i++;
                }
            }
        }
        return j == pattern.length;
    }

    public static void main(String[] args) {
        String str = "aabaaabaaac";
        String subString = 	"aabcabcab";
        boolean result = KMP(str.toCharArray(), subString.toCharArray());
        System.out.print(result);
    }
}
