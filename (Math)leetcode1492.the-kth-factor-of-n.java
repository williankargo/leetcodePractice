import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    // Time: O(n)
    // Space: O(n)
    public int kthFactor(int n, int k) {

        List<Integer> factorList = getFactors(n);
        Collections.sort(factorList);

        if (factorList.size() < k) {
            return -1;
        }

        return factorList.get(k - 1);
    }

    private List<Integer> getFactors(int n) {
        List<Integer> list = new ArrayList<>();
        int factor = 1;
        while (factor * factor <= n) {
            if (n % factor != 0) {
                factor++;
                continue;
            }
            list.add(factor);
            int anotherFactor = n / factor;
            // 排除重複
            if (anotherFactor == factor) {
                factor++;
                continue;
            }
            list.add(anotherFactor);
            factor++;

        }
        return list;
    }

}