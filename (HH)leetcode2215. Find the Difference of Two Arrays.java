import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {

    // TC: O(N)
    // SC: O(N)
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {

        List<List<Integer>> ans = new ArrayList<>();

        Set<Integer> set1 = new HashSet<>();
        Set<Integer> list1 = new HashSet<>();

        Set<Integer> set2 = new HashSet<>();
        Set<Integer> list2 = new HashSet<>();

        for (int i = 0; i < nums1.length; i++) {
            set1.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            set2.add(nums2[i]);
        }

        for (int i = 0; i < nums1.length; i++) {
            if (!set2.contains(nums1[i])) {
                list1.add(nums1[i]);
            }
        }
        ans.add(new ArrayList<>(list1));

        for (int i = 0; i < nums2.length; i++) {
            if (!set1.contains(nums2[i])) {
                list2.add(nums2[i]);
            }
        }
        ans.add(new ArrayList<>(list2));

        return ans;
    }
}