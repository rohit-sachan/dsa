import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combinator {

    public static void main(String[] args) {
        System.out.println(combinations(Arrays.asList("P1", "P2", "P3")));
    }

    public static <T> List<List<T>> combinations(List<T> elements) {
        return findCombinations(0, new ArrayList<>(), new ArrayList<>(), elements);
    }

    private static <T> List<List<T>> findCombinations(int i, ArrayList<T> powerset, ArrayList<List<T>> result, List<T> elements) {
        if(i == elements.size()){
            // System.out.println(powerset);
            result.add(new ArrayList<T>(powerset));
            // System.out.println("added  "+ powerset + " in result "+ result);
            return result ;
        }
        //pick
        powerset.add(elements.get(i));
        findCombinations(i+1, powerset, result, elements);
        //not pick, element added in previous step needs to be backtracked and removed
        powerset.remove(powerset.size()-1);
        findCombinations(i+1, powerset, result, elements);
        return result;
    }
}
