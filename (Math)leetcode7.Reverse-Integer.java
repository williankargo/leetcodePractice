class Solution {

    // Time: O(logX) ==> 大小為x的數字大約有logX位
    // Sapce: O(1)
    public int reverse(int x) {

        long ans = 0;
        while (x != 0) {
            ans = ans * 10 + x % 10;
            x = x / 10;
        }

        return (int) ans == ans ? (int) ans : 0;
    }

}