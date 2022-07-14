import java.util.ArrayList;
import java.util.List;

class Solution {

    public static final String[] KEYBOARD = {
            "", // 0
            "", // 1
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"
    };

    // Recursion - BT
    // combination
    // Time: O(4^n)
    // Space: O(4^n)
    public List<String> letterCombinations(String digits) {

        List<String> combinations = new ArrayList<>();
        if (digits == null || digits == "") {
            return combinations;
        }

        dfs(0, "", combinations, digits);

        return combinations;
    }

    private void dfs(int index, String combination, List<String> combinations, String digits) {

        // exit
        if (index == digits.length()) {
            combinations.add(combination);
            return;
        }

        int number = digits.charAt(index) - '0'; // '2' - '0' = 2

        // 拆解
        for (int i = 0; i < KEYBOARD[number].length(); i++) {
            dfs(index + 1, combination + KEYBOARD[number].charAt(i), combinations, digits); // 如果用List就要 add remove了
        }
    }

}