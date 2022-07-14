import java.util.ArrayList;
import java.util.List;

// 170. Two Sum 3
// https://www.cnblogs.com/grandyang/p/5184143.html

// 相向雙指針（要先排序）
// 時O(N) 空O(N):存在List中
class solution {

    // list用 get()來取得值
    // list用 set() 來賦予值
    public List<Integer> nums;

    public void TwoSum() {
        nums = new ArrayList<Integer>();
    }

    // 由小排到大
    public void add(int number) {
        nums.add(number);
        int index = nums.size() - 1;
        while (index > 0 && nums.get(index - 1) > nums.get(index)) { // 注意index合理性！！！！
            // swap
            swap(nums, index);
            index--;
        }
    }

    private void swap(List<Integer> nums, int index) {
        int temp = nums.get(index);
        nums.set(index, nums.get(index - 1));
        nums.set(index - 1, temp);
    }

    public boolean find(int targetValue) {
        int left = 0;
        int right = nums.size() - 1;

        while (left < right) {
            int currentValue = nums.get(left) + nums.get(right);
            if (currentValue < targetValue) {
                left++;
            } else if (currentValue > targetValue) {
                right--;
            } else {
                return true;
            }
        }
        return false;
    }
}