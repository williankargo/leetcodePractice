// solution: https://www.youtube.com/watch?v=nONCGxWoUfM&ab_channel=NeetCode
// TC: O(NlogN)
// SC:  O(logN) => Arrays.sort() in java use quicksort, SC is O(logN)

import java.util.Arrays;

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {

        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);

        int prevEnd = intervals[0][1];
        int count = 0;
        for (int i = 1; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            if (start < prevEnd) {
                count++;
                prevEnd = Math.min(prevEnd, end);
            } else {
                prevEnd = end;
            }
        }

        return count;
    }
}