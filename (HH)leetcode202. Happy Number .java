// solution: https://www.youtube.com/watch?v=ljz85bxOYJ0&ab_channel=NeetCode
// TC: O(K)
// SC: O(K)

import java.util.HashSet;
import java.util.Set;

class Solution {

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (!set.contains(n)) {
            set.add(n);
            n = findSum(n);
            if (n == 1) {
                return true;
            }
        }
        return false;
    }

    private int findSum(int num) {
        int output = 0;
        while (num != 0) {
            int digit = num % 10;
            int digitSum = digit * digit;
            output += digitSum;
            num /= 10;
        }

        return output;
    }
}