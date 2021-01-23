package LCS;

public class LCS_LONGEST_RATZUF_NOHAZAROT {
    public static String greedy2(String X) {
        String res = "";
        int ind = 0;
        int letters[] = new int[26];
        for (int i = 0; i < X.length(); i++) {
            int place = (int) (X.charAt(i) - 'a');
            letters[place]++;
        }
        while (ind < X.length()) {
            char y = X.charAt(ind);
            int place = (int) (y - 'a');
            if (letters[place] > 0) {
                res = res + X.charAt(ind);
                letters[place] = 0;
            }
            ind = ind + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        String x = "abcabcbb";
        System.out.println(greedy2(x));
    }
}
