import java.util.Arrays;

class Solution {

    // Time: O(26*N)
    // Space: O(26)
    // 同向雙指針
    public int longestSubstring(String s, int k) {

        if (s.length() == 0 || s == null) {
            return 0;
        }

        int[] nums = new int[26]; // 有26種字母要計算次數
        int longest = 0;
        // 思考如何達到分段性，有分段性才能使用雙指針
        // https://mp.weixin.qq.com/s?__biz=MzU4NDE3MTEyMA==&mid=2247484728&idx=1&sn=c72b71bc0b6fce4cf29ca756a8a4eb36&chksm=fd9cae27caeb2731b785e76ae1966688f6a6661977916e25926a357117b7932caa3b51815361&scene=178&cur_album_id=1748659352518868992#rd
        for (int genLimit = 1; genLimit <= 26; genLimit++) { // 26個字母，每次可以符合的種類

            Arrays.fill(nums, 0);

            // 遍歷字串
            for (int i = 0, j = 0, totalGen = 0, meetGen = 0; i < s.length(); i++) { // 不用再每個寫int

                int currentChar = s.charAt(i) - 'a'; // 當前的字母
                nums[currentChar]++;
                if (nums[currentChar] == 1) {
                    totalGen++;
                }
                if (nums[currentChar] == k) {
                    meetGen++;
                }

                // 當當前的字串的字母種類數超過目前的gen限制
                while (totalGen > genLimit) {
                    int toRemoveChar = s.charAt(j) - 'a';
                    nums[toRemoveChar]--;
                    if (nums[toRemoveChar] == 0) {
                        totalGen--;
                    }
                    if (nums[toRemoveChar] == (k - 1)) {
                        meetGen--;
                    }
                    j++; // 左指針往前
                }

                if (totalGen == meetGen) {
                    longest = Math.max(longest, (i - j + 1));
                }
            }
        }

        return longest;
    }
}