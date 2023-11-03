// solutionL: https://www.youtube.com/watch?v=7yF-U1hLEqQ&ab_channel=NeetCode
// be careful: s = "bbbaaaba", t = "aaabbbba"
// TC: O(N)
// SC: O(N)

import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean isIsomorphic(String s, String t) {

        Map<Character, Character> sMap = new HashMap<>();
        Map<Character, Character> tMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (sMap.containsKey(s.charAt(i)) && sMap.get(s.charAt(i)) != t.charAt(i) ||
                    tMap.containsKey(t.charAt(i)) && tMap.get(t.charAt(i)) != s.charAt(i)) {
                return false;
            }
            sMap.put(s.charAt(i), t.charAt(i));
            tMap.put(t.charAt(i), s.charAt(i));
        }

        return true;
    }
}