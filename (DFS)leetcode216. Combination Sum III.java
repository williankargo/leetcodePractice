import java.util.ArrayList;
import java.util.List;

class Solution {

    // TC: O()
    // SC: O(k)
    public List<List<Integer>> combinationSum3(int k, int n) {

        List<List<Integer>> list = new ArrayList<>();

        dfs(0, 0, 1, new ArrayList<>(), k, n, list);

        return list;
    }

    private void dfs(int currSum, int currLayer, int currNum, List<Integer> temp, int standardLayer, int standardSum,
            List<List<Integer>> list) {

        if (currLayer == standardLayer) {
            if (currSum == standardSum) {
                list.add(new ArrayList<>(temp)); // 記得要新創造一個物件，不然list會被大改！
            }
            return;
        }

        for (int i = currNum; i <= 9; i++) {
            temp.add(i);
            dfs(currSum + i, currLayer + 1, i + 1, temp, standardLayer, standardSum, list);
            temp.remove(temp.size() - 1);
        }
    }
}