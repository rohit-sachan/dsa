package utils;

import org.assertj.core.api.Assertions;

import java.util.List;

public class Utils {
    public static void print2DIntArray(int[][] arr){
        for (int r = 0; r < arr.length; r++) {         //for loop for row iteration.
            for (int c = 0; c < arr[r].length; c++) {   //for loop for column iteration.
                System.out.printf("%-4s", arr[r][c]== Integer.MAX_VALUE ? "\u221E": arr[r][c]+"" );
            }
            System.out.println();
        }
        System.out.println("------------------------------------- ");
    }

    public static <T> void print2DList(List<List<T>> arr) {
        for (List<T> ts : arr) {         //for loop for row iteration.
            for (T t : ts) {   //for loop for column iteration.
                System.out.print(t + "  ");
            }
            System.out.println();
        }
        System.out.println("------------------------------------- ");
    }

    public static void print2DIntArray(boolean[][] arr, char[] charsXAxis, char[] charsYAxis){
        System.out.print("   ");
        for (int i = 0; i < charsXAxis.length; i++) {
            System.out.print(charsXAxis[i] + "  ");
        }
        System.out.println();
        for (int i = 0; i < charsXAxis.length; i++) {
            System.out.print("---");
        }
        System.out.println();
        for (int r = 0; r < arr.length; r++) {         //for loop for row iteration.
            System.out.print(charsYAxis[r] + "| ");
            for (int c = 0; c < arr[r].length; c++) {   //for loop for column iteration.
                System.out.print((arr[r][c]?"T":"-") + "  ");
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
