class Solution {
    /**
     * @param a: an integer array
     * @return: nothing
     */
    public void sortIntegers(int[] a) {
        quickSort(a, 0, a.length - 1);
    }

    // Time: average: O(NlogN) , worst: O(1/2N^2)
    // Space: O(1)
    // 都要自帶=
    private void quickSort(int[] a, int start, int end) {

        if (start >= end) {
            return;
        }

        int left = start; // index
        int right = end; // index
        int pivot = a[(start + end) / 2]; // value

        while (left <= right) {

            // left's value 要找 >= pivot
            while (left <= right && a[left] < pivot) {
                left++;
            }

            // right's value 要找 <= pivot
            while (left <= right && a[right] > pivot) {
                right--;
            }

            // swap
            if (left <= right) {
                int temp = a[left];
                a[left] = a[right];
                a[right] = temp;
                left++; // 交換後要繼續走下去
                right--;
            }

        }

        // 左右兩半繼續quicksort
        quickSort(a, start, right);
        quickSort(a, left, end);
    }
}