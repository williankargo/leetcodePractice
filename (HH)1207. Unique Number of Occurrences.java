import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {

    // TC: O(N)
    // SC: O(N)
    public boolean uniqueOccurrences(int[] arr) {

        Map<Integer, Integer> freq = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            freq.put(arr[i], freq.getOrDefault(arr[i], 0) + 1);
        }

        Set<Integer> set = new HashSet<>(freq.values());

        return freq.size() == set.size();
    }
}