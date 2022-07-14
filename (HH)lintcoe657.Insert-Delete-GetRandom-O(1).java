import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

class RandomizedSet {

    ArrayList<Integer> nums;
    HashMap<Integer, Integer> mirror; // value:index
    Random rand;

    public RandomizedSet() {
        nums = new ArrayList<>();
        mirror = new HashMap<>();
        rand = new Random();
    }

    public boolean insert(int val) {
        if (mirror.containsKey(val)) {
            return false;
        }

        mirror.put(val, nums.size());
        nums.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!mirror.containsKey(val)) {
            return false;
        }

        // DataStructure O(1) 小技巧
        // 把要移除的東西和尾部交換，以達到O(1)時間複雜度
        int lastIndex = nums.size() - 1;
        if (nums.get(lastIndex) != val) { // 如果要移除的東西不在尾部

            int lastValue = nums.get(lastIndex);
            int removeIndex = mirror.get(val);
            mirror.put(lastValue, removeIndex);
            nums.set(removeIndex, lastValue); // java List set
        }

        mirror.remove(val);
        nums.remove(lastIndex);
        return true;
    }

    public int getRandom() {
        return nums.get(rand.nextInt(nums.size())); // java rand
    }
}
