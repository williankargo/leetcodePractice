// soulution: https://www.youtube.com/watch?v=FdzJmTCVyJU&ab_channel=NeetCode
// TC: O(NlogN)
// SC: O(N)

import java.util.Arrays;

class Solution {
    public int minMeetingRooms(int[][] intervals) {

        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];
        int s = 0, e = 0;

        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int count = 0;
        int ans = 0;
        while (s < start.length) {
            if (start[s] < end[e]) {
                count++;
                s++;
            } else {
                count--;
                e++;
            }
            ans = Math.max(ans, count);
        }

        return ans;
    }
}