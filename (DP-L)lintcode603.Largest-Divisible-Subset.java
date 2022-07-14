import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Time: O(N * factorNum * N^1/2)
// Space: O(N)
class Solution {

    public List<Integer> largestDivisibleSubset(int[] nums) {

        if (nums.length == 0 || nums == null) {
            return new ArrayList<Integer>();
        }

        // 0.
        // 以 dp.get(i) 為結尾的符合規定的數組長度
        Map<Integer, Integer> dp = new HashMap<>();
        // 記錄i是哪個j所成就的
        Map<Integer, Integer> prev = new HashMap<>();

        // sort
        // 因為後面的dp會依賴於前面的dp
        Arrays.sort(nums);

        // 1.
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            dp.put(nums[i], 1);
            prev.put(nums[i], -1);
        }

        // 2.
        int EndNum = nums[0];
        for (int num : nums) {
            for (int factor : getFactor(num)) { // for (j) 的概念
                if (!dp.containsKey(factor)) {
                    continue;
                }
                if (dp.get(factor) + 1 > dp.get(num)) {
                    dp.put(num, dp.get(factor) + 1);
                    prev.put(num, factor);
                }
            }

            if (dp.get(num) > dp.get(EndNum)) {
                EndNum = num;
            }
        }

        // 3.
        return resultOfLargest(EndNum, prev);
    }

    // 返回除了自己的factor
    private List<Integer> getFactor(int num) {

        List<Integer> result = new ArrayList<>();

        if (num == 1) {
            return result;
        }

        int factor = 1;
        // ex: 9: 1 , 3 (== 9^1/2，只需要遍歷到這裡), 9
        while (factor * factor <= num) {
            if (num % factor == 0) {
                result.add(factor);

                int anotherFactor = num / factor;
                // 排除自己 和 重複
                if (anotherFactor != num && anotherFactor * anotherFactor != num) {
                    result.add(anotherFactor);
                }
            }
            factor++;
        }

        return result;
    }

    private List<Integer> resultOfLargest(int EndNum, Map<Integer, Integer> prev) {

        List<Integer> result = new ArrayList<>();

        result.add(EndNum);
        while (prev.get(EndNum) != -1) {
            result.add(prev.get(EndNum));
            EndNum = prev.get(EndNum);
        }

        Collections.reverse(result);

        return result;
    }

}