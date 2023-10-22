class Solution {
    public int lengthOfLastWord(String s) {

        String[] array = s.split("\\s+"); // 針對連續空格分割

        return array[array.length - 1].length();
    }
}