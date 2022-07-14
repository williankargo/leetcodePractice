class Solution {

    // mergeSort version
    // Time: O(NlogN)
    // Space: O(N)
    public void sortIntegers2(int[] a) {

        if (a == null || a.length == 0) {
            return;
        }

        int[] temp = new int[a.length];
        mergeSort(temp, 0, a.length - 1, a);
    }

    private void mergeSort(int[] temp, int start, int end, int[] a) {

        // exit
        // 一個單位
        if (start == end) {
            return;
        }

        // decide
        // 處理左半
        mergeSort(temp, start, (start + (end - start) / 2), a);
        // 處理右半
        mergeSort(temp, (start + (end - start) / 2 + 1), end, a);

        merge(temp, start, end, a);
    }

    private void merge(int[] temp, int start, int end, int[] a) {

        int middle = start + (end - start) / 2;
        int leftIndex = start;
        int rightIndex = middle + 1;
        int Index = leftIndex;

        while (leftIndex <= middle && rightIndex <= end) {
            if (a[leftIndex] < a[rightIndex]) {
                temp[Index++] = a[leftIndex++];
            } else {
                temp[Index++] = a[rightIndex++];
            }
        }

        // 如果一方已經被拿完了
        while (leftIndex <= middle) {
            temp[Index++] = a[leftIndex++];
        }
        while (rightIndex <= end) {
            temp[Index++] = a[rightIndex++];
        }

        for (int i = start; i <= end; i++) {
            a[i] = temp[i];
        }

    }
}