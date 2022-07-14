import java.util.HashMap;
import java.util.Map;

class UnionFind {

    // 父root
    private Map<Integer, Integer> father;
    // 集合大小
    private Map<Integer, Integer> sizeOfSet;
    // 集合數量
    private int numOfSet;

    public UnionFind() {

        // 初始化
        father = new HashMap<Integer, Integer>();
        sizeOfSet = new HashMap<Integer, Integer>();
        numOfSet = 0;
    }

    public void add(int x) {

        if (father.containsKey(x)) {
            return;
        }

        father.put(x, null); // 初始化點的root為null
        sizeOfSet.put(x, 1); // 初始化該點所在集合大小為1
        numOfSet++;
    }

    public void merge(int x, int y) {

        // 找到兩個節點的root
        int rootX = find(x);
        int rootY = find(y);

        // 如果根不是同一個則連接
        if (rootX != rootY) {

            // 將一個點的根變成新的根
            father.put(rootX, rootY);
            numOfSet--;
            sizeOfSet.put(rootY, sizeOfSet.get(rootX) + sizeOfSet.get(rootY));
        }

    }

    public int find(int x) {

        int root = x; // 指針root指向被查找的點x

        // 不斷找到root的父親
        while (father.get(root) != null) {
            root = father.get(root);
        }

        // 將路徑上的所有點指向根節點root
        // 把樹壓平
        while (x != root) {
            int originalFather = father.get(x);
            father.put(x, root);
            x = originalFather;
        }

        return root;
    }

    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    public int getNumofSet() {
        return numOfSet;
    }

    // 獲得某個點所在之集合大小
    public int getSizeOfSet(int x) {

        return sizeOfSet.get(find(x));
    }

}