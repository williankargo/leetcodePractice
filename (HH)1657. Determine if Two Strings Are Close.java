import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    // TC: O(N)
    // SC: O(1), character at most = 26
    public boolean closeStrings(String word1, String word2) {

        if (word1.length() != word2.length()) {
            return false;
        }

        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        for (int i = 0; i < word1.length(); i++) {
            map1.put(word1.charAt(i), map1.getOrDefault(word1.charAt(i), 0) + 1);
        }

        for (int i = 0; i < word2.length(); i++) {
            map2.put(word2.charAt(i), map2.getOrDefault(word2.charAt(i), 0) + 1);
        }

        if (!map1.keySet().equals(map2.keySet())) { // use keySet() to get the keys of map, and use equals() to compare
                                                    // 2 sets
            return false;
        }

        List<Integer> list1 = new ArrayList<>(map1.values());
        Collections.sort(list1); // use Collections to sort List
        List<Integer> list2 = new ArrayList<>(map2.values());
        Collections.sort(list2);

        return list1.equals(list2);
    }
}