class Solution {

    // TC: O(N)
    // SC: O(1)
    public int largestAltitude(int[] gain) {

        int max = 0;
        int start = 0;
        for (int num : gain) {
            max = Math.max(start + num, max);
            start = start + num;
        }

        return max;
    }
}