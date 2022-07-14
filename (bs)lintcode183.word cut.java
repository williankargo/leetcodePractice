
// https://www.lintcode.com/problem/183/

class Solution {
    /**
     * @param L: Given n pieces of wood with length L[i]
     * @param k: An integer
     * @return: The maximum length of the small pieces
     */

    // Time: O(Nlog(N))
    // Space: O(K)
    public int woodCut(int[] L, int k) {
        // 不一定每塊木頭都要被切到

        if (L == null || L.length == 0) {
            return 0;
        }

        // 木頭切塊最小 start cm, 最大 end cm
        int start = 1, end = 0;
        long sum = 0; // ***int相加時要注意可能會越位，所以用long來接***
        for (int l : L) {
            end = Math.max(end, l);
            sum += l;
        }

        end = (int) Math.min(sum / k, end);
        // Q1: 直接用sum / k 當答案就好了啊？ 不行，木塊不是均勻分佈的
        // Q2: 答案不會超過sum / k，那為什麼還要跟end比小？因為木塊不是均勻分佈的，可能會得到大於最大木塊的答案

        // 排除無解的可能性
        if (end < 1) {
            return 0;
        }

        // 對結果進行二分法
        // ＊＊二分法要注意等號要放哪裡＊＊
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (count(L, mid) >= k) { // 等號放在這裡
                start = mid;
            } else { // < k 如果等號放這裡，會限縮end的最大可能性
                end = mid;
            }
        }

        return (count(L, end) >= k) ? end : start;
    }

    // O(N)
    private int count(int[] L, int length) {
        int ans = 0;
        for (int l : L) {
            ans += (l / length);
        }
        return ans;
    }
}