// Solution: https://www.youtube.com/watch?v=1IzCRCcK17A&ab_channel=NeetCodeIO
// TC: O(N)
// SC: O(N)
class Solution {
    public int candy(int[] ratings) {

        int[] result = new int[ratings.length];

        // Arrays.fill(result, 1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                result[i] = result[i - 1] + 1;
            }
        }

        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                result[i] = Math.max(result[i], result[i + 1] + 1);
            }
        }

        int total = ratings.length; // default + all 1
        for (int i = 0; i < ratings.length; i++) {
            total += result[i];
        }

        return total;
    }
}