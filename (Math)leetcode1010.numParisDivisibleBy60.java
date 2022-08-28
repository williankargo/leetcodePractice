// Time: O(N)
// Space: O(N)
class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        if (time.length == 0 || time == null) {
            return 0;
        }

        int count = 0;
        int[] previous = new int[60];

        for (int item : time) {
            if (item % 60 == 0) { // item == 0 or 60 個別討論，因為16行會顯示60 但18行會顯示0
                count += previous[0];
            } else {
                count += previous[60 - (item % 60)]; // 加上之前的補數個數
            }
            previous[item % 60]++;
        }

        return count;
    }
}