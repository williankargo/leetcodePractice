class Solution {

    // TC: O(N)
    // SC: O(1)
    public int compress(char[] chars) { // this question should modify chars !!!

        // required to use only constant space so should be inplace
        // StringBuilder sb = new StringBuilder(); => not inplace

        int i = 0;
        int j = 0;
        int index = 0;
        while (i < chars.length) {
            while (j < chars.length && chars[i] == chars[j]) {
                System.out.println("j: " + j);
                j++;
            }

            chars[index++] = chars[i];
            if (j - i > 1) {
                String count = j - i + "";
                for (char c : count.toCharArray()) {
                    chars[index++] = c;
                }
            }
            i = j;
        }

        return index;
    }
}