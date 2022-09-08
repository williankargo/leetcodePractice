import java.util.Arrays;

class Solution {

    // Time: O(N)
    // Space: O(1)
    // https://leetcode.com/problems/append-k-integers-with-minimal-sum/discuss/1823848/Java-solution-use-n*(n%2B1)2
    public long minimalKSum(int[] nums, int k) {

        Arrays.sort(nums);

        int prev = -1; // 避免使用 (加入元素的總和 - 原本的總和) 方法失效
        long sum = 0;
        long bound = k;
        for (int num : nums) {
            if (prev == num) {
                continue;
            }
            if (num > bound) {
                break;
            }

            // num < bound
            bound++; // 因為num小於bound，代表最後加入k個數的總和會少一個扣打，所以要提高bound
            sum += num;
            prev = num;
        }

        return (long) (((1 + bound) * bound / 2) - sum);
    }
}