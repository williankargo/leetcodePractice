import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    // Recursion - BackTracking
    // permutation template
    // Time: O(N * N!) 構造每個方案的時間 * 方案總數，其中構造每個方案的時間為exit的deep copy
    // Space: O(N * N!) 共N!個，每個長度為N
    public List<List<Integer>> permuteUnique(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) {
            return result;
        }
        boolean[] visited = new boolean[nums.length]; // default => false
        Arrays.sort(nums); // 去重用，先排序好才可以使用到 nums[i - 1] == nums[i] 來判斷
        // 排序的意義：
        // 1. 可以按照字母序得到結果
        // 2. 相同的會在前後，nums[i - 1] == nums[i] 來判斷去重

        dfs(visited, new ArrayList<Integer>(), result, nums);
        return result;
    }

    private void dfs(boolean[] visited, List<Integer> temp, List<List<Integer>> result, int[] nums) {

        // exit
        if (temp.size() == nums.length) {
            result.add(new ArrayList<Integer>(temp));
            return;
        }

        // decide
        for (int i = 0; i < nums.length; i++) {

            if (visited[i]) {
                continue;
            }

            // avoid duplicate
            // 如果前一個一樣的還沒有被走到，就不能增加後一個一樣的到temp裡(也就是『前一個一樣的』一定要在『後一個一樣的』的前面)
            if (i > 0 && nums[i - 1] == nums[i] && !visited[i - 1]) {
                continue;
            }

            temp.add(nums[i]);
            visited[i] = true;

            dfs(visited, temp, result, nums); // 可以用index+1來取代visited，但是這裡要看有沒有走過來去重，所以要用visited

            visited[i] = false;
            // System.out.println("temp: " + temp);
            // System.out.println("i: " + i);
            // System.out.println("=====");
            temp.remove(temp.size() - 1); // 把位置為i的字母移除，但為什麼不能直接用 "i" ? 因為temp的大小會變動，可能會out of bound

        }
    }

}