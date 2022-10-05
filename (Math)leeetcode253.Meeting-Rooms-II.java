import java.util.Arrays;

class Solution {

    // 0 30
    // 5 10
    // 15 20
    //
    // start [0, 5, 15]
    // end [10, 20, 30]
    // https://leetcode.com/problems/meeting-rooms-ii/discuss/67855/Explanation-of-%22Super-Easy-Java-Solution-Beats-98.8%22-from-%40pinkfloyda

    // Time: O(NlogN)
    // Space: O(N)
    public int minMeetingRooms(int[][] intervals) {

        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];

        int c = 0;
        for (int[] interval : intervals) {
            start[c] = interval[0];
            end[c] = interval[1];
            c++;
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int room = 0;
        int ei = 0;
        for (int i = 0; i < start.length; i++) {
            if (start[i] < end[ei]) {
                room++;
            } else {
                ei++;
            }
        }

        return room;
    }
}