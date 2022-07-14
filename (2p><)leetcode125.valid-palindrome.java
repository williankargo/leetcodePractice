
// 相向雙指針 O(N)
class Solution {
    public boolean isPalindrome(String s) {

        int left = 0;
        int right = s.length() - 1;
        while (left < right) {

            while (left < right && !isValid(s.charAt(left))) {
                left++;
            }

            while (left < right && !isValid(s.charAt(right))) {
                right--;
            }

            if (left < right && !isEqual(s.charAt(left), s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // isLetter(char c) : 判斷這個char是英文字母
    // isDigit(char c) : 判斷這個char是數字
    private boolean isValid(char c) {
        return Character.isDigit(c) || Character.isLetter(c);
    }

    // Character.toLowerCase(char c)
    private boolean isEqual(char leftChar, char rightChar) {
        return Character.toLowerCase(leftChar) == Character.toLowerCase(rightChar);
    }
}