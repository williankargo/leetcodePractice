import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {

    // TC: O(N^2)
    // SC: O(N^2)
    public int equalPairs(int[][] grid) {

        int len = grid.length;
        int count = 0;

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            String key = Arrays.toString(grid[i]);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        for (int i = 0; i < len; i++) {
            int[] row = new int[len];
            for (int j = 0; j < len; j++) {
                row[j] = grid[j][i];
            }
            String checkKey = Arrays.toString(row);
            count += map.getOrDefault(checkKey, 0);
        }

        return count;
    }
}