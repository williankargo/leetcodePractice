
/** 二分法 */
// 輸入的資料很大 -> 用 O(1) 或 O(logN) 解法
// 可以二分段：在答案集上二分
class Solution1 {

    // Time: O(總書頁log(總書頁))
    // Space: O(1)
    public int copyBooks(int[] pages, int k) {

        if (pages == null || pages.length == 0) {
            return 0;
        }
        if (k == 0) {
            return -1;
        }

        // 花的時間
        int start = getMaxPage(pages);
        int end = getTotalPage(pages);

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (getPeople(pages, mid) > k) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (getPeople(pages, start) <= k) {
            return start;
        }
        return end;
    }

    private int getMaxPage(int[] pages) {
        int max = 0;
        for (int page : pages) {
            max = Math.max(max, page);
        }
        return max;
    }

    private int getTotalPage(int[] pages) {
        int sum = 0;
        for (int page : pages) {
            sum += page;
        }
        return sum;
    }

    // 輸入總花費時間，得到需要幾個抄寫人
    private int getPeople(int[] pages, int limit) {

        int people = 0;
        int currentWorkLoad = 0;

        for (int page : pages) {
            currentWorkLoad += page;
            if (currentWorkLoad > limit) {
                people++;
                currentWorkLoad = page;
            }
        }
        people += 1; // 最後一個currentWorkLoad沒有算到people

        return people;
    }

}

/** 動態規劃 */
class Solution2 {
    
}