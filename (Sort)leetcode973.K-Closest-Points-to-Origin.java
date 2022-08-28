import java.util.Arrays;

class Solution {

    public int[][] kClosest(int[][] points, int k) {

        int[][] ans = new int[k][2];
        if (points.length == 0 || points == null || k == 0) {
            return ans;
        }

        Arrays.sort(points,
                (a, b) -> ((int) ((Math.pow(a[0], 2) + Math.pow(a[1], 2)) - (Math.pow(b[0], 2) + Math.pow(b[1], 2)))));

        for (int i = 0; i < k; i++) {
            ans[i] = new int[] { points[i][0], points[i][1] };
        }

        return ans;
    }
}