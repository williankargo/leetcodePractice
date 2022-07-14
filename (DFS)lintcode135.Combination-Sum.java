import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

class Solution {

    // Recursion - BT
    // combination
    // Time: O(N^(taraget/min)), N為candidates中的個數，每個答案最多會target/min個值
    // Space: O(N^(taraget/min))
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }

        // 題目說要返回升序的，所以要排序
        // 且題目沒有說candidates中的數字是unique的，所以要去重
        int[] newCandidates = sortAndRemoveDuplicate(candidates);

        dfs(target, 0, new ArrayList<Integer>(), result, newCandidates);

        return result;
    }

    private int[] sortAndRemoveDuplicate(int[] candidates) {

        Set<Integer> set = new TreeSet<>();
        for (int element : candidates) {
            set.add(element);
        }
        int[] newCandidates = new int[set.size()];
        int i = 0;
        for (int element : set) {
            newCandidates[i++] = element;
        }

        // System.out.println(Arrays.toString(newCandidates));
        return newCandidates;
    }

    private void dfs(int target, int index, List<Integer> candidate, List<List<Integer>> result, int[] candidates) {

        // exit
        if (target == 0) {
            result.add(new ArrayList<Integer>(candidate));
            return;
        }

        // 因為結果集可以有duplicate element，所以index不用+1
        for (int i = index; i < candidates.length; i++) {

            // prunning
            if (target < candidates[i]) {
                break;
            }

            candidate.add(candidates[i]);
            dfs(target - candidates[i], i, candidate, result, candidates);
            candidate.remove(candidate.size() - 1);
        }
    }

}