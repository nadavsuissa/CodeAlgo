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
}