import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    // Time: O(Nklogk) k is the length of word
    // Space: O(N)
    // https://www.youtube.com/watch?v=ptgykfAEax8
    public List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {

            char[] str_array = str.toCharArray();
            Arrays.sort(str_array);
            String newStrs = new String(str_array);

            if (!map.containsKey(newStrs)) {
                map.put(newStrs, new ArrayList<>());
            }
            map.get(newStrs).add(str);
        }

        result.addAll(map.values());
        return result;
    }
}