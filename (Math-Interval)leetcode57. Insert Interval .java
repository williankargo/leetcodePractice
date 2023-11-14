// solution: https://www.youtube.com/watch?v=A8NUOmlwOlM&ab_channel=NeetCode
// TC: O(N)
// SC: O(N)

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {

        if (intervals.length == 0 || intervals == null) {
            return new int[][] { newInterval };
        }

        List<int[]> result = new ArrayList<>();
        int i = 0;

        // totally beofre newInterval
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // merge
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        // the remaining part
        while (i < intervals.length) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][2]); // turn list to array
    }
}