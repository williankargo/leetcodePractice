import java.util.HashMap;

class Solution {


    // Time: O(N)
    // Space: O(N)
    public int firstUniqueNumber(int[] nums, int number) {

        if (nums.length == 0 || nums == null) {
            return -1;
        }

        HashMap<Integer, Boolean> uniqueMap = new HashMap<>();
        for (int num : nums) {
            uniqueMap.put(num, !uniqueMap.containsKey(num));

            if (num == number) {
                break;
            }
        }

        if (!uniqueMap.containsKey(number)) {
            return -1;
        }

        for (int num : nums) {
            if (uniqueMap.get(num)) {
                return num;
            }
        }

        return -1;
    }
}