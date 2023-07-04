class Solution {
    // TC: O(N)
    // SC: O(N)
    public String reverseWords(String s) {

        s = s.trim();
        String[] sArray = s.split("\\s+"); // one or more space

        int left = 0;
        int right = sArray.length - 1;

        while (left < right) {
            String temp = sArray[left];
            sArray[left] = sArray[right];
            sArray[right] = temp;
            left++;
            right--;
        }

        return String.join(" ", sArray);
    }
}