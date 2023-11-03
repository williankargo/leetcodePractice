// TC: O(N)
// SC: O(N)

import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {

        Map<Character, Integer> magMap = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            magMap.put(magazine.charAt(i), magMap.getOrDefault(magazine.charAt(i), 0) + 1);
        }

        for (int j = 0; j < ransomNote.length(); j++) {
            if (magMap.containsKey(ransomNote.charAt(j)) && magMap.get(ransomNote.charAt(j)) > 0) {
                magMap.put(ransomNote.charAt(j), magMap.get(ransomNote.charAt(j)) - 1);
            } else {
                return false;
            }
        }

        return true;
    }
}