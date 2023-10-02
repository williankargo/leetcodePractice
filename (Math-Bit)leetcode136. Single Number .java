class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i : nums) {
            result ^= i;
            // A XOR 0 = A
            // A XOR A = 0
            // A XOR B XOR A = B
        }

        return result;
    }
}