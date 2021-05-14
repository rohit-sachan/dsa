import utils.Utils;

public class LevenshteinDistance {
    public static void main(String[] args) {
//        System.out.println(findEditDistance("saturday", "sundays") ==4);
        System.out.println(levenshteinDistance("abc", "yabd") ==2);
    }

    public static int levenshteinDistance(String str1, String str2) {
        if(isNullOrEmpty(str1)){
            return str2.length();
        }
        if(isNullOrEmpty(str2)){
            return str1.length();
        }

        int[][] mem = new int[str1.length()][str2.length()];

        if (str1.charAt(0)== str2.charAt(0)){
            mem[0][0] = 0;
        } else{
            mem[0][0] = 1;
        }
        for (int r = 0; r < str1.length() ; r++) {
            for (int c = 0; c < str2.length(); c++) {
                if(r ==0 && c==0) continue; // need not to handle upper left start, already handled earlier

                else if(str1.charAt(r) == str2.charAt(c)) {
                    mem[r][c] = getMinOfThree(mem, r,c);
                } else {
                    mem[r][c] = getMinOfThree(mem, r,c) + 1;
                }
            }

        }
         Utils.print2DIntArray(mem);
        return mem[str1.length()-1][str2.length()-1];
    }

    private static boolean isNullOrEmpty(String str){
        return !(str != null && !str.trim().isEmpty());
    }

    private static int getMinOfThree(int[][] mem, int r, int c) {
        int diagonalVal = (r>0 && c>0) ? mem[r - 1][c - 1] : Integer.MAX_VALUE;
        int sameRowPrevVal =(c>0) ? mem[r][c - 1]: Integer.MAX_VALUE;
        int upperRowSameCol =(r>0 )? mem[r - 1][c]: Integer.MAX_VALUE;
        return Math.min(Math.min(diagonalVal, sameRowPrevVal), upperRowSameCol);
    }

}
