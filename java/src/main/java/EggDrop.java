public class EggDrop {
    //https://leetcode.com/problems/super-egg-drop/

    public static void main(String[] args) {
        System.out.println(new EggDrop().superEggDrop(2, 100));
    }

    public int superEggDrop(int egg, int f) {
        int res = Integer.MAX_VALUE;
        if (egg == 1) {
            return f;
        }
        if (f == 0 || f == 1) {
            return f;
        }
        for (int i = 1; i < f; i++) {
            res = Math.min(res,
                    Math.max(superEggDrop(egg - 1, i - 1) // if egg breaks from this ith floor then search from floors lower than i
                            , superEggDrop(egg, f - i) // if egg doesn't breaks from this ith floor then search for floors  above i
                    ) + 1 // +1 to add because one test is already done as part of this ith floor drop, Max because we are looking for worst case
            );
        }
        return res;
    }
}
