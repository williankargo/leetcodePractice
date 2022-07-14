import java.util.HashMap;
import java.util.Map;

// https://mp.weixin.qq.com/s?__biz=MzU4NDE3MTEyMA==&mid=2247484130&idx=6&sn=6a6fd8a208ccd7f0b7d706a3ff5900d7&chksm=fd9ca9fdcaeb20eb43980e8c841294d8b366fdbc128dacf140a93740221691ba241fe4593501&scene=178&cur_album_id=1748659352518868992#rd
class Solution {

    // 同向雙指針
    // Time: O(N)
    // Space: O(N)
    public int lengthOfLongestSubstring(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        // 0. definition
        int j = 0; // j是尾部後一個字母
        int count;
        int maxLength = 0;
        // 1. dataStructure
        Map<Character, Integer> map = new HashMap<>(); // 紀錄每個字母出現的次數

        // 2. for i
        for (int i = 0; i < s.length(); i++) {

            // 3. while j
            while (j < s.length() && !(map.getOrDefault(s.charAt(j), 0) > 0)) { // 這裡記得要判斷『末尾的後一個』
                count = map.getOrDefault(s.charAt(j), 0) + 1;
                map.put(s.charAt(j), count);
                j++;
            }

            // 4. 討論
            // 尾部後一個字母發現是重複的，那先統計到尾部的長度
            if (j < s.length() && map.getOrDefault(s.charAt(j), 0) > 0) {
                maxLength = Math.max(maxLength, j - i);
            } else { // j走完了
                return Math.max(maxLength, (j - i));
            }

            // 5. slide
            // 把頭刪掉，要滑動窗口了
            count = map.get(s.charAt(i)) - 1;
            map.put(s.charAt(i), count);
        }

        return maxLength;
    }
}