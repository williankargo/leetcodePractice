/**
 * Forward declaration of guess API.
 * 
 * @param num your guess
 * @return -1 if num is higher than the picked number
 *         1 if num is lower than the picked number
 *         otherwise return 0
 *         int guess(int num);
 */

// Binary Search
// TC: O(logN)
// SC: O(1)
public class Solution extends GuessGame {
    public int guessNumber(int n) {

        int low = 1;
        int high = n;

        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            int decide = guess(mid);
            if (decide == -1) {
                high = mid;
            } else if (decide == 1) {
                low = mid;
            } else {
                return mid;
            }
        }

        if (guess(high) == 0) {
            return high;
        }

        return low;
    }
}