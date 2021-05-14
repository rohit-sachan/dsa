package utils;

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
}