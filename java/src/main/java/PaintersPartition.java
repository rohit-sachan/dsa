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
        System.out.println(new PaintersPartition().paint(3,1, arrayList));
    }


    public int paint(int A, int B, ArrayList<Integer> C) {
        int hi = 0;
        int lo = -1;
        ArrayList<Integer> arr = new ArrayList<Integer>(C.size());
        for(int  i = 0 ;  i< C.size(); i++){
            arr.add(C.get(i)*B);
        }
        for(int  i = 0 ;  i< arr.size(); i++){
            hi = hi + arr.get(i);
            lo = Math.max(lo, arr.get(i));
        }

        System.out.println("hi " + hi + ", lo " +lo );
        int mid = (hi+lo)/2;

        while(possiblyAccommodatedPainters(mid, A, arr) < A){

        }

        return mid;
    }

    /**
     * checks if
     * possible to accommodate the mid time
     **/
    private int possiblyAccommodatedPainters(int mid, int A, ArrayList<Integer> C){
        System.out.println("mid = " + mid);
        long sum = 0;
        int accommodatedPainters = 0;
        for(int n=0; n< C.size(); n++){
            System.out.println("idx="+ n+ " block="+C.get(n));
            sum = sum+C.get(n);
            if(sum > mid || n == C.size()-1){
                if(n == C.size()-1){
                    System.out.println("current idx=" + n +" reached to the end of array, blocks count=" + C.size());
                }else {
                    System.out.println("hmm, sum=" + sum + " exceeds " + mid + ", so we need to consider new set of blocks. current idx=" + n + ", blockSize=" + C.get(n));
                }
                sum = 0;
                accommodatedPainters++; // so one more painter is included
                if(n == C.size()-1){
                    continue;
                }
                n--; // and use current block again in next set as this block inclusion results in higher sum
            }
        }

        System.out.println("accommodated painters="+ accommodatedPainters + " minTime="+ mid );

        return accommodatedPainters;

    }

}
