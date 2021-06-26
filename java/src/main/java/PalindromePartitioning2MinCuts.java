import utils.Utils;

import java.util.Arrays;

public class PalindromePartitioning2MinCuts {
    //Palindrome Partitioning II

    public static void main(String[] args) {
        int minCuts = -1;
//        PalindromePartitioning2MinCuts.palindromePartitioningMinCuts("ggbobanabob");
        minCuts =  PalindromePartitioning2MinCuts.palindromePartitioningMinCuts("ababbbabbababa");
//        minCuts =  PalindromePartitioning2MinCuts.palindromePartitioningMinCuts("cbbbcc");
        System.out.println(minCuts);
    }
    public static int palindromePartitioningMinCuts(String str) {
        boolean[][] m = new boolean[str.length()][str.length()];
        for (int r = 0; r < str.length(); r++) {
            for (int c = r; c < str.length() ; c++) {
                if(c == r) {
                    m[r][c] = true;
                } else {
                    m[r][c] = isPalindrome(r, c, str);
                }
            }
        }
        Utils.print2DIntArray(m, str.toCharArray(), str.toCharArray());

        Integer[] section = new Integer[str.length()];
        for (int i = 0; i < str.length(); i++) {
            //check from start till this `i`, if its palindrome then no cut needed
            boolean checkIfPalindromeFromStart = checkIfPalindromeFromStart(m[0], i);
            int inMiddlePalindorm = -1;
            if(checkIfPalindromeFromStart) {
                System.out.println("Start to End palindrome "+ i);
                section[i] = 0;
            } else if (previousIndexWasPalindrom(section,i)){
                System.out.println("Next to palindrome "+ i);
                section[i] = section[i-1]+1;
            }
            // this is tricky section, whatever palindrome we find in from the middle ,
            // we need to take the cut count at the the index before that + add this palindrome, which is cut count 1
            else if((inMiddlePalindorm = checkIfAnyPalindromFromSomeWhereAfterStart(m, i)) > 0 && inMiddlePalindorm != i) {
                System.out.println("Forms palindrome from middle "+ inMiddlePalindorm + ", " + i);
                section[i] = Math.min(section[i-1]+1, section[inMiddlePalindorm-1]+1);
            } else {
                System.out.println("Default " + i);
                section[i] = section[i-1]+1;
            }
            System.out.println(" cut section ="+ Arrays.deepToString(section));
        }
        return section[str.length()-1];
    }

    private static boolean previousIndexWasPalindrom(Integer[] section, int i) {
        return section[i-1] == 0;
    }

    private static int checkIfAnyPalindromFromSomeWhereAfterStart(boolean[][] m, int c) {
        for (int r = 0; r < m.length; r++) {
            if(m[r][c]){
                return r;
            }
        }
        return -1;
    }

    private static boolean checkIfPalindromeFromStart(boolean[] booleans, int i) {
        return booleans[i];
    }

    public static boolean isPalindrome(int s, int e,  String str) {
        boolean result = true;
        while (s <= e){
            if(str.charAt(s) != str.charAt(e)){
                result = false;
            }
            s++;
            e--;
        }
        return result;
    }

}
