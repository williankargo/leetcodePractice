import java.util.HashMap;
import java.util.Map;

class Solution {

    // Time: O(N)
    // Space: O(N)
    public int romanToInt(String s) {

        Map<String, Integer> map = new HashMap<>();

        // thousand
        map.put("M", 1000);
        map.put("MM", 2000);
        map.put("MMM", 3000);

        // hundred
        map.put("C", 100);
        map.put("CC", 200);
        map.put("CCC", 300);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("DC", 600);
        map.put("DCC", 700);
        map.put("DCCC", 800);
        map.put("CM", 900);

        // ten
        map.put("X", 10);
        map.put("XX", 20);
        map.put("XXX", 30);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("LX", 60);
        map.put("LXX", 70);
        map.put("LXXX", 80);
        map.put("XC", 90);

        // one
        map.put("I", 1);
        map.put("II", 2);
        map.put("III", 3);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("VI", 6);
        map.put("VII", 7);
        map.put("VIII", 8);
        map.put("IX", 9);

        int sum = 0;
        int j = 0;
        for (int i = 0; i < s.length(); i = j) {
            while (j < s.length() && map.containsKey(s.substring(i, j + 1))) {
                j++;
            }

            sum += map.get(s.substring(i, j));
            if (j >= s.length()) {
                return sum;
            }
        }

        return sum;
    }
}