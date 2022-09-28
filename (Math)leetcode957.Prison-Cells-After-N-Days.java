import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {

    // Time: O(2^6) 走完2^6種狀態也就是所有可能性，一定可以找到規律
    // Space: O(2^6) 總共有8位，頭尾不會變，就2^6種狀態
    // https://leetcode.com/problems/prison-cells-after-n-days/discuss/205684/JavaPython-Find-the-Loop-or-Mod-14
    public int[] prisonAfterNDays(int[] cells, int n) {

        // 找規律
        Map<String, Integer> patternToN = new HashMap<>();
        // int[] newcells = new int[8]; // 不可以寫在這裡，每次都要重新初始化一個物件，不然下面newcells改時cells會跟著改

        while (n > 0) {
            patternToN.put(Arrays.toString(cells), n--); // day0的pattern就要算進去
            int[] newcells = new int[8];
            for (int i = 1; i < 7; i++) {
                newcells[i] = (cells[i - 1] == cells[i + 1] ? 1 : 0);
            }
            cells = newcells;

            // 找到規律了！
            if (patternToN.containsKey(Arrays.toString(cells))) {
                n = n % (patternToN.get(Arrays.toString(cells)) - n);
            }
        }

        return cells;
    }
}