// TC: O(N)
// SC: O(N)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {

        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        int wordPerLen = words[0].length();
        int wordLen = words.length;
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i <= s.length() - wordPerLen * wordLen; i++) {

            Map<String, Integer> tempMap = new HashMap<>();
            for (int j = 0; j < wordLen; j++) {

                String tempWord = s.substring(i + j * wordPerLen, i + j * wordPerLen + wordPerLen);

                if (map.containsKey(tempWord)) {
                    tempMap.put(tempWord, tempMap.getOrDefault(tempWord, 0) + 1);
                } else {
                    break;
                }

                if (tempMap.get(tempWord) > map.get(tempWord)) {
                    break; // break inner loop only !
                }

                if (j + 1 == wordLen) {
                    result.add(i);
                }
            }
        }
        return result;
    }
}

// solution 2: optimal version
// TC: O(N)
// SC: O(N)
class Solution2 {
    public List<Integer> findSubstring(String s, String[] words) {

        Map<String, Integer> freqMap = new HashMap<>();
        for (String word : words) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }

        int wordLen = words.length;
        int wordPerLen = words[0].length();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < wordPerLen; i++) { // 每wordPerLen一個規律

            int left = i;
            int right = i;
            int count = 0;
            Map<String, Integer> tempMap = new HashMap<>();
            while (right + wordPerLen <= s.length()) {
                String tempWord = s.substring(right, right + wordPerLen);
                right += wordPerLen;

                if (freqMap.containsKey(tempWord)) {
                    tempMap.put(tempWord, tempMap.getOrDefault(tempWord, 0) + 1);
                    count += 1;

                    // sliding window => move forward, uptill tempMap is normal
                    while (tempMap.getOrDefault(tempWord, 0) > freqMap.getOrDefault(tempWord, 0)) {
                        String lefttemp = s.substring(left, left + wordPerLen);
                        tempMap.put(lefttemp, tempMap.getOrDefault(lefttemp, 0) - 1);
                        left += wordPerLen;
                        count -= 1;
                    }

                    if (count == wordLen) {
                        result.add(left);
                    }

                } else {
                    left = right;
                    count = 0;
                    tempMap.clear(); // clear map
                }
            }
        }

        return result;
    }
}