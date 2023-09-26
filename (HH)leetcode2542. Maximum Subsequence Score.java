import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {

    // https://www.youtube.com/watch?v=ax1DKi5lJwk&ab_channel=NeetCodeIO
    // TC: O(NlogN)
    // SC: O(N)
    public long maxScore(int[] nums1, int[] nums2, int k) {

        // bind nums1 and nums2
        int[][] pairs = new int[nums1.length][2];
        for (int i = 0; i < nums1.length; i++) {
            pairs[i] = new int[] { nums1[i], nums2[i] };
        }

        // sort pairs
        Arrays.sort(pairs, (a, b) -> b[1] - a[1]); // desc

        // pq
        Queue<Integer> pq = new PriorityQueue<>(k); // asc

        // get a sample up to k
        long sum = 0;
        for (int i = 0; i < k; i++) {
            sum += pairs[i][0];
            pq.offer(pairs[i][0]);
        }
        long ans = sum * pairs[k - 1][1];

        // than compare the ans with other ans
        // 確保可以iterate所有可能性
        for (int i = k; i < pairs.length; i++) {
            int smallest = pq.poll();
            int added = pairs[i][0];
            pq.offer(added);
            sum = (sum - smallest) + added;
            ans = Math.max(ans, sum * pairs[i][1]);
        }

        return ans;
    }
}
