// solution: Greedy! https://www.youtube.com/watch?v=lJwbPZGo05A&ab_channel=NeetCode
// TC: O(N)
// SC: O(1)
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sum = 0;
        for (int i = 0; i < gas.length; i++) {
            sum += (gas[i] - cost[i]);
        }
        if (sum < 0) {
            return -1;
        }
        // 好，到這一步已經確定此題有答案，且根據題目只有一個答案

        int total = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            total += (gas[i] - cost[i]);
            if (total < 0) {
                total = 0; // 為什麼可以設成0?因為題目保證只有一個答案且一定有答案，這個start如果走剩下的都是正的，那他就是答案，後面的不可能再有第二個答案
                start = i + 1; // 如果從這邊出發不行，那可能可以從下一個出發
            }
        }

        return start;
    }
}