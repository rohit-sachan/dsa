import java.util.ArrayList;

public class PaintersPartition {

    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(2);
        arrayList.add(1);
        arrayList.add(5);
        arrayList.add(6);
        arrayList.add(2);
        arrayList.add(3);
        System.out.println(new PaintersPartition().paint(3,1, arrayList) + "Ans = "+ 8);
    }


    public int paint(int A, int B, ArrayList<Integer> C) {
        int hi = 0;
        int lo = -1;
        ArrayList<Integer> arr = new ArrayList<Integer>(C.size());
        for (Integer value : C) {
            arr.add(value * B);
        }
        for (Integer integer : arr) {
            hi = hi + integer;
            lo = Math.max(lo, integer);
        }

        return accommodatedPainters(lo, hi, A, arr);
    }

    /**
     * checks if possible to accommodate the mid time
     * @return -1 if lesser number of painters can be accommodated for the given [mid],
     * +1 if higher number of painters can be accommodated
     * */
    private int accommodatedPainters(int lo, int hi, int A, ArrayList<Integer> C){
        int midTime = (lo + hi)/2;
        System.out.println("------------- mid= " + midTime+ " lo="+ lo+" hi="+hi+" ------------------");

        long sum = 0;
        int accommodatedPainters = 0;
        for(int n=0; n< C.size(); n++) {
            System.out.println("idx=" + n + " block=" + C.get(n));
            sum = sum + C.get(n);
            if (sum > midTime) {
                System.out.println("current idx=" + n + " inclusion of current block EXCEEDS the midTime="+ midTime+", sum="+ sum );
                sum = 0;
                n--; // and use current block again in next set as this block inclusion results in higher sum
                accommodatedPainters++; // so one more painter is included
            }
            if (sum == midTime){
                System.out.println("current idx=" + n + " inclusion of current block EQUALS the midTime="+ midTime+", sum="+ sum );
                sum = 0;
                accommodatedPainters++;
            }
            if(sum < midTime && n == C.size() - 1){
                System.out.println("current idx=" + n + " inclusion of current block LESS THAN the midTime="+ midTime+", sum="+ sum );
                accommodatedPainters++;
            }
            if (n == C.size() - 1 && accommodatedPainters == A) { // if reached last then we dont need to reduce in inorder to reconsider
                System.out.println("current idx=" + n + " reached to the end of array, total blocks count=" + C.size());
                break;
            }
        }

        System.out.println("accommodated painters="+ accommodatedPainters + " midTime="+ midTime );

        // converged to solution
        if (lo >= hi && accommodatedPainters == A)
            return lo;
        else {

            // if equal lets get ahead of ourselves and try to minimize the time,
            // if less accommodatedPainters then obviously we can minimize the time and try to accommodate more painters
            if(lo<=hi) {
                if (accommodatedPainters <= A)
                    return accommodatedPainters(lo, midTime - 1, A, C);
                else {//  if more accommodatedPainters means time provided is not  enough and we need to increase the mid
                    return accommodatedPainters(midTime + 1, hi, A, C);
                }
            } else {
                return lo;
            }
        }
    }

}
