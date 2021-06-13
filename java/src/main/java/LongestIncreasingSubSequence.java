import java.util.*;
import java.util.stream.Collectors;

public class LongestIncreasingSubSequence {
    public static void main(String[] args) {
//        LongestIncreasingSubSequence.longestCommonSubsequence("axy", "abyx");
        LongestIncreasingSubSequence.longestCommonSubsequence("8111111111111111142", "222222222822222222222222222222433333333332");
    }

    public static List<Character> longestCommonSubsequence(String str1, String str2) {
        if(str1.equals("") || str2.equals("")){
            return "".chars().mapToObj(c -> (char)c).collect(Collectors.toList());
        }
        Map<StringCouple, String> dp = new HashMap<>();
        String acc = lcs(str1, str2, dp);
         System.out.println("acc:" +acc);
        return acc.chars().mapToObj(c -> (char)c).collect(Collectors.toList());
    }

    public static String lcs(String s1, String s2, Map<StringCouple, String> dp) {
        if(s1.length()==0 || s2.length()==0 ) return "" ; //can't move

        StringCouple couple =  new StringCouple(s1, s2);
        System.out.println(couple+" Existing dp "+ dp);
        if(dp.get(couple) != null) {
            System.out.println("Found "+ dp.get(couple) + " for "+couple);
            return dp.get(couple);
        }

        if(s1.charAt(0) == s2.charAt(0)) {
            String result = s1.charAt(0) +	lcs(s1.substring(1,s1.length()), s2.substring(1,s2.length()), dp);
            dp.put(couple, result);
            return result;
        } else {
            String s1_moved = lcs(s1.substring(1,s1.length()), s2, dp);
            String s2_moved = lcs(s1, s2.substring(1,s2.length()), dp);
            String result =  s1_moved.length()>s2_moved.length() ? s1_moved : s2_moved;
            dp.put(couple, result);
            return result;
        }
    }


    static class StringCouple {
        String str1;
        String str2;

        public StringCouple(String str1, String str2) {
            this.str1 = str1;
            this.str2 = str2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            StringCouple that = (StringCouple) o;

            if (str1 != null ? !str1.equals(that.str1) : that.str1 != null) return false;
            return str2 != null ? str2.equals(that.str2) : that.str2 == null;
        }


        @Override
        public int hashCode() {
            int result = str1 != null ? str1.hashCode() : 0;
            result = 31 * result + (str2 != null ? str2.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "str1='" + str1 + "'str2='" + str2 + "'";
        }
    }
}
