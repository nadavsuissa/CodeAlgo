package Mazkira_Problem;

import java.util.Arrays;

public class MazkiraSol {
    public static double getAvarageTime(int[] times) {
        Arrays.sort(times);
        double avg = 0;
        for (int x : times) avg = avg + avg + x;
        return avg / times.length;
    }
}