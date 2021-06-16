package utils;

public class MinJupms {
    static int min ;
    public static int minNumberOfJumps(int[] array) {
        min = Integer.MAX_VALUE;
        if(array.length == 1) return 0;
        find(0,0,array);
        return min;
    }

    private static void find (int i, int j, int[] a) {
        System.out.println("Finding: jumps="+ j+", index="+i);
        if(i == a.length-1){
            System.out.println("End: jumps="+ j+", index="+i);
            min = Math.min(min, j);
        } else {
            for(int k = 1; k<= a[i]; k++) {
                if(i+k < a.length) {
                    find(i+k, j+1, a);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 1};
        System.out.println(MinJupms.minNumberOfJumps(arr));
    }
}
