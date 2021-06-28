import java.util.HashMap;
import java.util.Map;

public class EggDrop {
    //https://leetcode.com/problems/super-egg-drop/

    public static void main(String[] args) {
        int egg = 2;
        int floor = 6;
        System.out.println(new EggDrop().superEggDrop(egg, floor));
    }

    private static class Pair {
        int first ;
        int second ;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != pair.first) return false;
            return second == pair.second;
        }

        @Override
        public int hashCode() {
            int result = first;
            result = 31 * result + second;
            return result;
        }
    }
    public int superEggDrop(int egg, int f) {
        Map<Pair, Integer> mem = new HashMap<>();
        return memoizedFind(egg, f, mem);
    }

    private int memoizedFind(int egg, int f, Map<Pair, Integer> mem) {
        System.out.println("egg = " + egg + ", f = " + f + " mem = ");
        int res = Integer.MAX_VALUE;
        if (egg == 1) {
            return f;
        }
        if (f == 0 || f == 1) {
            return f;
        }

        // for floor 1 and above
        if(mem.containsKey(new Pair(egg, f))){
            return mem.get(new Pair(egg, f));
        }
        for (int i = 1; i < f; i++) {
            res = Math.min(res,
                    Math.max(memoizedFind(egg - 1, i - 1, mem) // if egg breaks from this ith floor then search from floors lower than i
                            , memoizedFind(egg, f - i, mem) // if egg doesn't breaks from this ith floor then search for floors  above i
                    ) + 1 // +1 to add because one test is already done as part of this ith floor drop, Max because we are looking for worst case
            );
        }
        mem.put(new Pair(egg, f), res);
        return res;
    }
}
