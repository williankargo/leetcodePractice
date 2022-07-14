// 相向雙指針 O(N)
class Solution {
    public boolean validPalindrome(String s) {

        if (s == null) {
            return false;
        }

        int left = 0;
        int right = s.length() - 1;
        Pair pair = findDifference(left, right, s);
        left = pair.left;
        right = pair.right;
        if (left >= right) {
            return true;
        }

        return findPalindrome(left + 1, right, s) || findPalindrome(left, right - 1, s);
    }

    // 返回多個值時 善用子類
    class Pair {
        int left, right;

        Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    private Pair findDifference(int left, int right, String s) {

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
            left++;
            right--;
        }
        return new Pair(left, right);
    }

    // pair.left >= pair.right 注意邊際條件
    private boolean findPalindrome(int left, int right, String s) {
        Pair pair = findDifference(left, right, s);
        if (pair.left >= pair.right) {
            return true;
        }
        return false;
    }
}