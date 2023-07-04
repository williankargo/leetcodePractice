class Solution {
    // TC: O(N)
    // SC: O(N)
    public String reverseVowels(String s) {

        char[] charArray = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {

            while (left < s.length() && !isVowel(s.charAt(left))) {
                left++;
            }

            while (right > 0 && !isVowel(s.charAt(right))) {
                right--;
            }

            if (left < right) {
                exchange(left++, right--, charArray);
            }

        }

        return new String(charArray);
    }

    private boolean isVowel(char c) {
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    private void exchange(int left, int right, char[] charArray) {
        char temp = charArray[right];
        charArray[right] = charArray[left];
        charArray[left] = temp;
    }
}