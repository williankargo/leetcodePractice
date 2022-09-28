import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    // Time: O(NlonN)
    // Space: O(N)
    public int[][] merge(int[][] intervals) {

        // quick sort to sort the array
        Arrays.sort(intervals, (i, j) -> (i[0] - j[0]));
        // System.out.println(Arrays.deepToString(intervals));

        List<int[]> result = new ArrayList<>();
        int[] isolatedInterval = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            // 可以合併
            if (isolatedInterval[1] >= intervals[i][0]) {
                // merge two interval
                isolatedInterval = new int[] { isolatedInterval[0], Math.max(isolatedInterval[1], intervals[i][1]) };
            } else {
                result.add(isolatedInterval);
                isolatedInterval = intervals[i];
            }
        }
        // last one
        result.add(isolatedInterval);

        // turn List to Array
        int[][] result_array = new int[result.size()][2];
        return result.toArray(result_array);
    }
}