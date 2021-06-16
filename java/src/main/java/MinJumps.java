public class MinJumps {

    static int[] mem;
    public static int minNumberOfJumps(int[] array) {
        mem = new int[array.length];
        if(array.length == 1) return 0;
        return find(0,array);
    }

    private static int find (int i, int[] a) {
        System.out.println("Finding: index="+i);
        int minSteps = a.length;
        if(i >= a.length-1){
            return 0;
        } else {
            if(mem[i]>0) return mem[i];
            for(int k = 1; k<= a[i]; k++) {
                minSteps = Math.min(minSteps, 1+find(k+i,a));
            }
        }
        mem[i] = minSteps;
        return minSteps;
    }

    public static void main(String[] args) {
        int [] arr = {3, 4, 2, 1, 2, 3, 7, 1, 1, 1, 3, 2, 6, 2, 1, 1, 1, 1};
        System.out.println(MinJumps.minNumberOfJumps(arr));
    }
}
