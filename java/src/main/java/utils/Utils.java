package utils;

import org.assertj.core.api.Assertions;

public class Utils {
    public static void print2DIntArray(int[][] arr){
        for (int r = 0; r < arr.length; r++) {         //for loop for row iteration.
            for (int c = 0; c < arr[r].length; c++) {   //for loop for column iteration.
                System.out.print(arr[r][c] + "  ");
            }
            System.out.println();
        }
        System.out.println("------------------------------------- ");
    }

    public static boolean isNullOrEmpty(String str){
        return !(str != null && !str.trim().isEmpty());
    }

    public static void assertTrue(boolean b) {
        Assertions.assertThat(b).isTrue();
    }
}
