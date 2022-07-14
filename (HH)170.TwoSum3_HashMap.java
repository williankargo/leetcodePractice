import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// 170. Two Sum 3
// https://www.cnblogs.com/grandyang/p/5184143.html
// HashMap實現
class solution {

    // getOrDefault() 可以避免取到空值發生錯誤
    // put() 來增加東西
    public HashMap<Integer, Integer> map;

    public void TwoSum() {
        map = new HashMap<Integer, Integer>();
    }

    // 由小排到大
    public void add(int number) {
        map.put(number, map.getOrDefault(number, 0) + 1);
    }

    public boolean find(int targetValue) {

        for (int num1 : map.keySet()) {
            int num2 = targetValue - num1;
            int count = 1;
            if (num2 == num1) {
                count = 2;
            }

            if (map.getOrDefault(num2, 0) >= count) {
                return true;
            }
        }
        return false;
    }

}