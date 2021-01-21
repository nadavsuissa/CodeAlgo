package Pizza_Problem;

public class Pizza_Sol {
    /**
     * returns the optimal division for the faster man
     * Complexity: O(1)
     */
    public static int getNumberOfPieces(double k) {
        if (k == (int) k) return (int) k + 1;
        return (int) k + 2;
    }
}