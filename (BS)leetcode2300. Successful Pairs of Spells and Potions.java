// method 1: binary search

import java.util.Arrays;

class Solution1 {
    // TC: O(Nlogn)
    // SC: O(N)
    public int[] successfulPairs(int[] spells, int[] potions, long success) {

        int spellsLength = spells.length;
        Arrays.sort(potions);
        int[] ans = new int[spellsLength];

        for (int i = 0; i < spellsLength; i++) {

            // int the sorted positions, the number should be at least num
            long num = (long) Math.ceil((double) success / spells[i]);
            // 如果沒有轉換成double，結果會是long(整數)
            int count = find(potions, num);
            ans[i] = count;
        }
        return ans;
    }

    private int find(int[] potions, long num) {
        int low = 0;
        int high = potions.length;
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (potions[mid] >= num) {
                high = mid;
            } else if (potions[mid] < num) {
                low = mid;
            }
            // 因為要找最小符合的，所以不能返回不確定是不是最小的
            // else {
            // return potions.length - mid;
            // }
        }

        if (potions[low] >= num) {
            return potions.length - low;
        } else {
            return potions.length - high;
        }
    }
}