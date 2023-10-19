// solution: https://www.youtube.com/watch?v=eBJZ3HnU1FE&ab_channel=%E8%B4%BE%E8%80%83%E5%8D%9A
// TC: O(NlogN)
// SC: O(1)

import java.util.Arrays;

class Solution {
    public int hIndex(int[] citations) {

        Arrays.sort(citations);
        int h = 1;
        // 從尾做，也可以從頭做
        for (int i = citations.length - 1; i >= 0; i--) {
            if (citations[i] >= h) {
                h++;
            }
        }

        return h - 1;
    }
}