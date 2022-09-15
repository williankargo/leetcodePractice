import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<Integer> partitionLabels(String s) {

        Map<Character, Integer> charToIndex = new HashMap<>();
        char[] s_char = s.toCharArray();
        int length = s.length();

        // record the last index of certain char
        for (int i = 0; i < length; i++) {
            charToIndex.put(s_char[i], i);
        }

        List<Integer> list = new ArrayList<>();
        int maxIndex = 0;
        int prevIndex = -1;
        for (int i = 0; i < length; i++) {
            maxIndex = Math.max(maxIndex, charToIndex.get(s_char[i]));
            if (maxIndex == i) { // !!!!!!
                list.add(maxIndex - prevIndex);
                prevIndex = i;
            }
        }

        return list;
    }
}