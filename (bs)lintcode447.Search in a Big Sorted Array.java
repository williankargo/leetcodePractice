class Solution {
    /**
     * @param reader: An instance of ArrayReader.
     * @param target: An integer
     * @return: An integer which is the first index of target.
     */

    // 二分法
    // Time: log(K), K is the first index of the target number
    // Space: O(1)
    public int searchBigSortedArray(ArrayReader reader, int target) {
        // write your code here

        // 倍增法，log(K) K is the first index
        int range = 1;
        while (reader.get(range - 1) < target) {
            range = range * 2;
        }

        int start = 0;
        int end = range - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (reader.get(mid) < target) {
                start = mid;
            }
            // >=
            else {
                end = mid;
            }
        }

        if (reader.get(start) == target) {
            return start;
        }
        if (reader.get(end) == target) {
            return end;
        }

        return -1;
    }
}