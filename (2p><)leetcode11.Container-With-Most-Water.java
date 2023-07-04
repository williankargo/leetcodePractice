// https://mp.weixin.qq.com/s?__biz=MzU4NDE3MTEyMA==&mid=2247484229&idx=2&sn=e3d730a608987d3c764f17578ab4e8af&chksm=fd9ca85acaeb214cb2ab0e66b3ee5f8b20a5159aef56e23b92669669b039187836d53ce48bb7&scene=178&cur_album_id=1748659352518868992#rd
class Solution {

    // 相向雙指針
    // Time: O(N)
    // Space: O(1)
    public int maxArea(int[] height) {

        if (height.length == 0 || height == null) {
            return 0;
        }

        int left = 0;
        int right = height.length - 1;
        int maxVol = 0;
        while (left < right) {
            int width = right - left;
            int tall = Math.min(height[left], height[right]);
            maxVol = Math.max(maxVol, width * tall);
            if (height[left] < height[right]) {
                left++;  // 這種情況下，l往前移動有可能會讓面積更大，r往後移動不可能讓面積變大
            } else {
                right--;
            }
        }

        return maxVol;
    }
}