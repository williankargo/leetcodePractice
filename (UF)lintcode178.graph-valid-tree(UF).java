import java.util.HashMap;
import java.util.Map;

class Solution {

    // Union Find Method
    // Space: O(N)
    // Time: O(N)
    public boolean validTree(int n, int[][] edges) {

        // 1. no loop: node # - 1 == edge #
        if (n - 1 != edges.length) {
            return false;
        }

        // 2. no island
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            uf.merge(edge[0], edge[1]);
        }

        if (uf.island != 1) {
            return false;
        }

        return true;
    }

}

class UnionFind {

    Map<Integer, Integer> father = new HashMap<>();
    int island = 0;

    UnionFind(int n) {
        for (int i = 0; i < n; i++) {
            father.put(i, i);
            island++;
        }
    }

    // find and compress tree
    int findRoot(int num) {
        int root = num;

        // 找到最根部的root
        while (root != father.get(root)) {
            root = father.get(root);
        }

        // 壓縮樹，把node都接到最根部的root
        while (father.get(num) != num) {
            father.put(num, root);
            num = father.get(num);
        }

        return root;
    }

    void merge(int n1, int n2) {

        int rootN1 = findRoot(n1);
        int rootN2 = findRoot(n2);

        if (rootN1 != rootN2) {
            father.put(rootN1, rootN2);
            island--;
        }
    }
}