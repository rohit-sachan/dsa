import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PhoneNumberMnemonics {
    private Map<Character, char[]> menos_map = new HashMap<>();

    public PhoneNumberMnemonics() {
        menos_map.put('0', new char[]{'0'});
        menos_map.put('1', new char[]{'1'});
        menos_map.put('2', new char[]{'a', 'b', 'c'});
        menos_map.put('3', new char[]{'d', 'e', 'f'});
        menos_map.put('4', new char[]{'g', 'h', 'i'});
        menos_map.put('5', new char[]{'j', 'k', 'l'});
        menos_map.put('6', new char[]{'m', 'n', 'o'});
        menos_map.put('7', new char[]{'p', 'q', 'r', 's'});
        menos_map.put('8', new char[]{'t', 'u', 'v'});
        menos_map.put('9', new char[]{'w', 'x', 'y', 'z'});
    }


    public ArrayList<String> phoneNumberMnemonics(String phoneNumber) {
        // Write your code here.
        ArrayList<String> res = new ArrayList<String>();
        generate(phoneNumber, 0, new ArrayList<Character>(), res);
        return res;
    }

    public void generate(String phoneNumber, int i, ArrayList<Character> combo, ArrayList<String> res) {
        // System.out.println("PhoneNum=" + phoneNumber + " i="+i+" combo="+combo +" res="+res);
        if (i == phoneNumber.length()) {
            res.add(combo.stream().map(Object::toString).collect(Collectors.joining()));
            return;
        }
        char[] menos = menos_map.get(phoneNumber.charAt(i));
        for (int j = 0; j < menos.length; j++) {
            combo.add(menos[j]);
            generate(phoneNumber, i + 1, combo, res);
            // remove last added and try new one, because added one and its possibilities has been explored in previous line's recursive call
            combo.remove(combo.size() - 1);
        }
    }

}
