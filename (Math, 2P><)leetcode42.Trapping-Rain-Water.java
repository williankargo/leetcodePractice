
// method 1: 最直觀的方法
class Solution1 {

    // method 1
    // [0,1,0,2,1,3,2]
    // 0 0 1 1 2 2 3 leftWall
    // 3 3 3 3 3 2 0 rightWall
    // min(left, right) - A[i] <= 0 => 0

    // Time: O(N)
    // Space:O(N)
    public int trap(int[] height) {

        // find the leftMost
        int[] leftMost = new int[height.length];
        int tempLeftMax = Integer.MIN_VALUE;
        for (int i = 1; i < height.length; i++) {
            tempLeftMax = Math.max(tempLeftMax, height[i - 1]);
            leftMost[i] = tempLeftMax;
        }

        // find the rightMost
        int[] rightMost = new int[height.length];
        int tempRightMax = Integer.MIN_VALUE;
        for (int i = height.length - 2; i >= 0; i--) {
            tempRightMax = Math.max(tempRightMax, height[i + 1]);
            rightMost[i] = tempRightMax;
        }

        // min(leftMost, rightMost) - height[i]
        int ans = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int temp = Math.max(Math.min(leftMost[i], rightMost[i]) - height[i], 0);
            ans += temp;
        }

        return ans;
    }
}

// method 2
// LP RP
// [0,1,0,2,1,3,2]
// leftPointer
// rightPointer
class Solution2 {

    // Time: O(N)
    // Space: O(1)
    // method 2
    // leftP
    // rightP
    // [0,1,0,2,1,0,1,3,2,1,2,1]
    // https://www.youtube.com/watch?v=StH5vntauyQ
    public int trap(int[] height) {

        // init leftP and rightP
        int leftP = 0, rightP = height.length - 1;
        int leftWall = height[leftP], rightWall = height[rightP];
        int ans = 0;

        while (leftP < rightP) {
            if (leftWall < rightWall) {
                leftP++;
                leftWall = Math.max(leftWall, height[leftP]); // check if wall height is smaller than current point
                ans += (leftWall - height[leftP]);
            } else {
                // reverse thinking
                rightP--;
                rightWall = Math.max(rightWall, height[rightP]);
                ans += (rightWall - height[rightP]);
            }
        }

        return ans;
    }
}