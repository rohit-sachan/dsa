import java.util.ArrayList;
import java.util.List;

public class PowerSet {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(4);
        System.out.println("powerset " + powerset(list));

    }


    public static List<List<Integer>> powerset(List<Integer> array) {
        return findPowerset(0,new ArrayList<Integer>(),new ArrayList<List<Integer>>(), array);
    }

    public static ArrayList<List<Integer>> findPowerset(
            int i,
            ArrayList<Integer> powerset,
            ArrayList<List<Integer>> result,
            List<Integer> array) {

        //System.out.println("i="+i);
        if(i == array.size()){
            // System.out.println(powerset);
            result.add(new ArrayList<Integer>(powerset));
            // System.out.println("added  "+ powerset + " in result "+ result);
            return result ;
        }
        //pick
        powerset.add(array.get(i));
        findPowerset(i+1, powerset, result, array);
        //not pick, element added in previous step needs to be backtracked and removed
        powerset.remove(powerset.size()-1);
        findPowerset(i+1, powerset, result, array);
        return result;
    }

}
