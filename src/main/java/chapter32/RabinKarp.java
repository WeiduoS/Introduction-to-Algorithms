package chapter32;

/**
 * @author Weiduo
 * @date 2019/8/9 - 8:38 PM
 */
public class RabinKarp {
    private final static int prime = 101; // A prime number
    private final static int d = 256; // d is the number of characters in the input alphabet

    public static int patternSearch(char[] text, char[] pattern) {
        int m = pattern.length;
        int n = text.length;
        int h = 1;

        // The value of h would be "pow(d, M-1) % prime"
        for (int i = 0; i < m - 1; i++) {
            h = (h * d) % prime;
        }

        int patternHash = createHash(pattern, m - 1);
        int textHash = createHash(text, m - 1);

        for (int i = 0; i <= n - m; i++) {
            if (patternHash == textHash && checkEqual(text, i, i + m - 1, pattern, 0, m - 1)) return i;

            if (i < n - m) {
                textHash = recalculateHash(text, i, i + m, textHash, h);
            }
        }

        return -1;
    }

    private static int createHash(char[] str, int end) {
        int hash = 0;
        for (int i = 0; i <= end; i++) {
            hash = (hash * d + str[i]) % prime;
        }
        return hash;
    }

    private static boolean checkEqual(char[] str1, int start1, int end1, char[] str2, int start2, int end2) {
        for (int i = start1, j = start2; i <= end1 && j <= end2; i++, j++) {
            if (str1[i] != str2[j]) return false;
        }
        return true;
    }

    private static int recalculateHash(char[] text, int deleteIndex, int addIndex, int oldHash, int h) {
        int newHash = ((oldHash - text[deleteIndex] * h) * d + text[addIndex]) % prime;
        if(newHash < 0) newHash += prime;
        return newHash;
    }


    public static void main(String[] args) {
        System.out.println(patternSearch("caac".toCharArray(), "aa".toCharArray()));
        System.out.println(patternSearch("hello".toCharArray(), "ll".toCharArray()));
        System.out.println(patternSearch("TusharRoy".toCharArray(), "sharRoy".toCharArray()));
        System.out.println(patternSearch("TusharRoy".toCharArray(), "Roy".toCharArray()));
        System.out.println(patternSearch("TusharRoy".toCharArray(), "shas".toCharArray()));
        System.out.println(patternSearch("TusharRoy".toCharArray(), "usha".toCharArray()));
        System.out.println(patternSearch("TusharRoy".toCharArray(), "Tus".toCharArray()));
    }
}
