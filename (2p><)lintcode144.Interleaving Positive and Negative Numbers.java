// https://www.lintcode.com/problem/144/

// 相向雙指針 
// Time O(N)
// Space O(1)
class Solution {
    public void rerange(int[] A) {

        int negativeCount = countNegativeNumbers(A);
        int positiveCount = A.length - negativeCount;

        int start = negativeCount > positiveCount ? 1 : 0;
        int end = negativeCount > positiveCount ? A.length - 1 : A.length - 2;

        swap(A, start, end);
    }

    private int countNegativeNumbers(int[] A) {

        int left = 0;
        int right = A.length - 1;

        // 相向雙指針
        // 設定while(left <= right) 可以避免 left == right 跳出去的尷尬情況，我們想要跳出去時left > right
        while (left <= right) {

            while (left <= right && A[left] < 0) {
                left++;
            }

            while (left <= right && A[right] > 0) {
                right--;
            }

            // 有動到的地方就要設條件！！！
            if (left <= right) {
                int temp = A[left];
                A[left] = A[right];
                A[right] = temp;

                left++;
                right--;
            }
        }
        return left;
    }

    private void swap(int[] A, int start, int end) {

        int left = start;
        int right = end;

        while (left < right) {
            int temp = A[left];
            A[left] = A[right];
            A[right] = temp;
            left += 2;
            right -= 2;
        }
    }
}