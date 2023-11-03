// TC: O(N)
// SC: O(N)

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean wordPattern(String pattern, String s) {

        String[] sArray = s.split(" ");
        System.out.println(Arrays.toString(sArray));
        if (pattern.length() != sArray.length) {
            return false;
        }

        Map<Character, String> pMap = new HashMap<>();
        Map<String, Character> sMap = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            Character patternItem = pattern.charAt(i);
            String sItem = sArray[i];
            if (pMap.containsKey(patternItem) && !pMap.get(patternItem).equals(sItem) ||
                    sMap.containsKey(sItem) && sMap.get(sItem) != patternItem) {
                return false;
            }
            pMap.put(patternItem, sItem);
            sMap.put(sItem, patternItem);
        }

        return true;
    }
}