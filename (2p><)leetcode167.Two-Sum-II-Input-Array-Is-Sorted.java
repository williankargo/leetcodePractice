class Solution {

    // Time: O(N)
    // Space: O(1)
    public int[] twoSum(int[] numbers, int target) {

        int leftPointer = 0;
        int rightPointer = numbers.length - 1;
        while (leftPointer < rightPointer) {
            int sum = numbers[leftPointer] + numbers[rightPointer];
            if (sum < target) {
                leftPointer++;
            } else if (sum > target) {
                rightPointer--;
            } else {
                return new int[] { leftPointer + 1, rightPointer + 1 };
            }
        }

        // in case there's no solution
        return new int[] { -1, -1 };
    }
}