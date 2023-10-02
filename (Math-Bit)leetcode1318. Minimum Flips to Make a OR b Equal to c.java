// solution: leetcode
// TC: O(N)
// SC: O(1)
class Solution {
    public int minFlips(int a, int b, int c) {

        int count = 0;

        while (a != 0 | b != 0 | c != 0) {
            if ((c & 1) == 1) { // 這裡是比較c的最右位，是1
                if ((a & 1) == 0 && (b & 1) == 0) {
                    count++;
                }
            } else { // c是0
                if ((a & 1) == 1) { // 常用 & 1來判斷
                    count++;
                }
                if ((b & 1) == 1) {
                    count++;
                }
            }

            // 往右拉一位，實際上是往左跳一位
            a >>= 1;
            b >>= 1;
            c >>= 1;
        }

        return count;
    }
}