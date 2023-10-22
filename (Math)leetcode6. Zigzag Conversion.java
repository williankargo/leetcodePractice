// draw on paper and analyze it !!!
// TC: O(N)
// SC: O(N)
class Solution {
    public String convert(String s, int numRows) {
        
        // be aware of writing the corner case !!!
        if (numRows == 1){
            return s;
        }

        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < numRows; row++){
            int increment = (numRows - 1) * 2;
            for (int i = row; i < s.length(); i += increment){
                sb.append(s.charAt(i));
                if (row != 0 && row != numRows - 1){ // be aware of "&&" or "||"
                    int sub_increment = increment - 2 * row;
                    if (i + sub_increment < s.length()){ // be aware of "boundary"
                        sb.append(s.charAt(i + sub_increment));
                    }
                }
            }
        }

        return sb.toString();
    }
}