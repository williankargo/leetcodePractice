import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {

    // Time: O(N)
    // Space: O(N)
    public String mostCommonWord(String paragraph, String[] banned) {

        Set<String> set = new HashSet<>();
        for (String ban : banned) {
            set.add(ban);
        }

        Map<String, Integer> map = new HashMap<>();

        // //W+ => 非字母 和 符號
        String[] array = paragraph.toLowerCase().split("\\W+");
        System.out.println(Arrays.toString(array));
        for (String word : array) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        int max = 0;
        String ans = "";
        for (String key : map.keySet()) {
            if (!set.contains(key) && map.get(key) > max) {
                max = map.get(key);
                ans = key;
            }
        }

        return ans;
    }
}