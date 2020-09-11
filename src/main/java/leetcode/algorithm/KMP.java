package leetcode.algorithm;

/**
 * @author：Cheng.
 * @since： 0911
 */
public class KMP {

    private int[][] dp;
    private String pat;

    /**
     * 构建状态转移数组dp
     *
     * @param pat
     */
    public KMP(String pat) {
        this.pat = pat;
        int M = pat.length();
        dp = new int[M][256];
        dp[0][pat.charAt(0)] = 1;
        // 影子状态
        int X = 0;
        for (int j = 1; j < M; j++) {
            for (int c = 0; c < 256; c++) {
                if (pat.charAt(j) == c) {
                    dp[j][c] = j + 1;
                } else {
                    dp[j][c] = dp[X][c];
                }
            }
            X = dp[X][pat.charAt(j)];
        }
    }

    public int search(String txt) {
        int N = txt.length();
        int j = 0;
        for (int i = 0; i < N; i++) {
            j = dp[j][txt.charAt(i)];
            if (j == pat.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        KMP kmp = new KMP("issip");
        System.out.println(kmp.search("mississippi"));

    }
}
