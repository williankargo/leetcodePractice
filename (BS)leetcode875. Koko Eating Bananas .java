class Solution {
    // TC: O(N)
    // SC: O(1)
    public int minEatingSpeed(int[] piles, int h) {

        int start = 1;
        int end = 0;
        for (int i = 0; i < piles.length; i++) {
            end = Math.max(end, piles[i]);
        }

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int count = 0;
            for (int i = 0; i < piles.length; i++) {
                count += Math.ceil((double) piles[i] / mid);
            }

            if (count > h) {
                start = mid;
            } else if (count <= h) {
                end = mid;
            }
        }

        int count = 0;
        for (int i = 0; i < piles.length; i++) {
            count += Math.ceil((double) piles[i] / start);
        }
        if (count <= h) {
            return start;
        }

        return end;
    }
}