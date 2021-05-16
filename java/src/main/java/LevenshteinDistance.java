import utils.Utils;

public class LevenshteinDistance {
    public static void main(String[] args) {
        System.out.println(levenshteinDistance("saturday", "sundays") ==4);
//        System.out.println(levenshteinDistance("abc", "yabd") ==2);
//        System.out.println("LavenshteinDistance= " + levenshteinDistance("biting", "mitten"));
    }

    public static int levenshteinDistance(String sourceStr, String targetStr) {
        // +1 in size as we are considering EMPTY char as first then starting with strings
        int[][] mem = new int[targetStr.length() + 1][sourceStr.length() + 1];

        for (int i = 0; i < mem[0].length; i++) {
            // first row to be direct char's deletion in order to become EMPTY because sourceStr
            // is increasing by 1 char each in each col (row=0) and in order ot match EMPTY targetStr needs deletion
            mem[0][i] = i;
        }
        for (int i = 0; i < mem.length; i++) {
            // first col to be direct char's addition in order to become EMPTY, because target string is increasing by each row in col=0
            mem[i][0] = i;
        }

        for (int r = 1; r <= targetStr.length(); r++) {
            for (int c = 1; c <= sourceStr.length(); c++) {
                if (sourceStr.charAt(c-1) == targetStr.charAt(r-1)) {
                    // if same char then no need to do any operation just copy over
                    mem[r][c] = mem[r-1][c-1];
                } else {
                    mem[r][c] = getMinOfThree(mem, r, c) + 1;
                }
            }
        }
        Utils.print2DIntArray(mem);
        return mem[targetStr.length()][sourceStr.length()];
    }

    private static boolean isNullOrEmpty(String str) {
        return !(str != null && !str.trim().isEmpty());
    }

    private static int getMinOfThree(int[][] mem, int r, int c) {
        int diagonalVal = mem[r - 1][c - 1];
        int sameRowPrevVal = mem[r][c - 1];
        int upperRowSameCol = mem[r - 1][c];
        return Math.min(Math.min(diagonalVal, sameRowPrevVal), upperRowSameCol);
    }

}
