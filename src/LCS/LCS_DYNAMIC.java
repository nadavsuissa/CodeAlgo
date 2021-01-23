package LCS;

import java.util.Vector;

/**
 * LCS - Longest Common Substring
 * returns the LCS of X and Y
 */
public class LCS_DYNAMIC {
    private int[][] mat;

    /**
     * Dynamic programming of LCS - length
     * Complexity: O(m*n) - |X| = n , |Y| = m
     */
    public int LCS_length(String X, String Y) {
        int n = X.length() + 1;
        int m = Y.length() + 1;
        mat = new int[n][m];
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    mat[i][j] = mat[i - 1][j - 1] + 1;
                } else {
                    mat[i][j] = Math.max(mat[i - 1][j], mat[i][j - 1]);
                }
            }
        }
        return mat[n - 1][m - 1];
    }

    /* Returns length of LCS for X[0..m-1], Y[0..n-1]
   and Z[0..o-1] */
    static int lcsOf3(String X, String Y, String Z, int m,
                      int n, int o) {
        int[][][] L = new int[m + 1][n + 1][o + 1];

        /* Following steps build L[m+1][n+1][o+1] in
           bottom up fashion. Note that L[i][j][k]
           contains length of LCS of X[0..i-1] and
           Y[0..j-1]  and Z[0.....k-1]*/
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= o; k++) {
                    if (i == 0 || j == 0 || k == 0)
                        L[i][j][k] = 0;

                    else if (X.charAt(i - 1) == Y.charAt(j - 1)
                            && X.charAt(i - 1) == Z.charAt(k - 1))
                        L[i][j][k] = L[i - 1][j - 1][k - 1] + 1;

                    else
                        L[i][j][k] = Math.max(Math.max(L[i - 1][j][k],
                                L[i][j - 1][k]),
                                L[i][j][k - 1]);
                }
            }
        }

        /* L[m][n][o] contains length of LCS for
          X[0..n-1] and Y[0..m-1] and Z[0..o-1]*/
        return L[m][n][o];
    }

    /**
     * Dynamic programming of LCS - string
     * Complexity: O(m*n) - build a matrix|X| = n , |Y| = m . O(m+n) - get the string
     */
    public String LCS_string(String X, String Y) {
        int len = LCS_length(X, Y);
        int i = X.length();
        int j = Y.length();
        String ans = "";
        while (len > 0) {
            if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                ans = X.charAt(i - 1) + ans;
                i--;
                j--;
                len--;
            } else {
                if (mat[i - 1][j] > mat[i][j - 1]) {
                    i--;
                } else {
                    j--;
                }
            }
        }
        return ans;
    }

    /**
     * Dynamic programming of LCS - string - Recursion
     * Complexity: O(m*n) - build a matrix|X| = n , |Y| = m . O(m+n) - get the string
     */
    public String LCS_string_Recursion(String X, String Y) {
        int len = LCS_length(X, Y);
        return LCS_string_Recursion(X, Y, X.length(), Y.length(), len);
    }

    private String LCS_string_Recursion(String X, String Y, int i, int j, int len) {
        if (len == 0) return "";
        if (X.charAt(i - 1) == Y.charAt(j - 1)) {
            return LCS_string_Recursion(X, Y, i - 1, j - 1, len - 1) + X.charAt(i - 1);
        } else {
            if (mat[i - 1][j] > mat[i][j - 1]) {
                return LCS_string_Recursion(X, Y, i - 1, j, len);
            } else {
                return LCS_string_Recursion(X, Y, i, j - 1, len);
            }
        }
    }

    /**
     * Dynamic programming of LCS - all substrings - Recursion
     * Complexity: O(m*n) - build a matrix|X| = n , |Y| = m . O(2^(m+n)) - get the strings
     */
    public Vector<String> LCS_Allstrings(String X, String Y) {
        int len = LCS_length(X, Y);
        Vector<String> ans = new Vector<String>();
        LCS_Allstrings(ans, X, Y, X.length(), Y.length(), len, "");
        return ans;
    }

    private void LCS_Allstrings(Vector<String> ans, String X, String Y, int i, int j, int len, String temp) {
        if (len == 0) {
            if (!ans.contains(temp)) ans.add(temp);
            return;
        }
        if (X.charAt(i - 1) == Y.charAt(j - 1)) {
            LCS_Allstrings(ans, X, Y, i - 1, j - 1, len - 1, X.charAt(i - 1) + temp);
        } else {
            if (mat[i - 1][j] > mat[i][j - 1]) {
                LCS_Allstrings(ans, X, Y, i - 1, j, len, temp);
            } else if (mat[i - 1][j] < mat[i][j - 1]) {
                LCS_Allstrings(ans, X, Y, i, j - 1, len, temp);
            } else {

                LCS_Allstrings(ans, X, Y, i - 1, j, len, temp);
                LCS_Allstrings(ans, X, Y, i, j - 1, len, temp);
            }
        }
    }

    public static void main(String[] args) {
        String X = "AGGT12";
        String Y = "12TXAYB";
        String Z = "12XBA";

        int m = X.length();
        int n = Y.length();
        int o = Z.length();

        System.out.println("Length of LCS is " +
                lcsOf3(X, Y, Z, m, n, o));

    }
}

