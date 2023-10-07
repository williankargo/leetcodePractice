// Solution: draw picture and think!
// 1 --- 6(prevEnd)
//   2(s) ----- 8
//         7(s) ----- 12(prevEnd)
//              10(s) ------- 16
// every time change prevEnd => +1, so should + 1 in the end because no prevEnd can be changed.

// TC: O(NlogN)
// SC:  O(logN) => Arrays.sort() in java use quicksort, SC is O(logN)

import java.util.Arrays;

class Solution {
    public int findMinArrowShots(int[][] points) {

        // sort by x_end
        Arrays.sort(points, (o1, o2) -> {
            // We can't simply use the o1[1] - o2[1] trick,
            // because simply subtracting o1[1] from o2[1] could cause an integer overflow
            if (o1[1] < o2[1]) {
                return -1;
            } else if (o1[1] > o2[1]) {
                return 1;
            } else {
                return 0;
            }
        });

        int prevEnd = points[0][1];
        int count = 0;

        for (int i = 1; i < points.length; i++) {
            int start = points[i][0];
            int end = points[i][1];
            if (start <= prevEnd) {

            } else {
                count++;
                prevEnd = end;
            }
        }

        count += 1;

        return count;
    }
}