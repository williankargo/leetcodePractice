import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

class Solution {

    // 快速合併兩個集合 => 用 Union Find
    // Space: O(N), N為email的數目
    // Time: O(N log(N))
    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        List<List<String>> results = new ArrayList<>();
        Map<String, String> NameToEmail = new HashMap<>();
        Queue<String> queue = new PriorityQueue<>();

        UnionFind uf = new UnionFind();

        // add
        for (List<String> email : accounts) {
            for (int i = 1; i < email.size(); i++) {
                uf.add(email.get(i));
                NameToEmail.put(email.get(i), email.get(0));
            }
        }

        // merge
        for (List<String> email : accounts) {
            int emailSize = email.size();
            for (int i = 1; i < email.size(); i++) {
                if (emailSize > 2) {
                    uf.merge(email.get(i), email.get(i + 1));
                    emailSize--;
                }
            }
        }

        // compress the tree
        for (String node : uf.father.keySet()) {
            uf.find(node);
        }

        // 把roots找出來
        Set<String> roots = new HashSet<>();
        for (Map.Entry<String, String> father : uf.father.entrySet()) {
            roots.add(father.getValue());
        }

        for (String root : roots) {
            Set<String> set = new TreeSet<>();
            String name = NameToEmail.get(root);
            for (Map.Entry<String, String> father : uf.father.entrySet()) {
                if (father.getValue() == root) {
                    set.add(father.getKey());
                }
            }
            List<String> sonList = new ArrayList<>(set); // log(N) 從TreeSet中find()
            sonList.add(0, name);
            results.add(sonList);
        }

        return results;
    }
}

class UnionFind {

    Map<String, String> father = new HashMap<>();
    int islands = 0;
    Map<String, Integer> territory = new HashMap<>();

    public UnionFind() {
    }

    public void add(String node) {
        if (father.get(node) != null) {
            return;
        }
        father.put(node, node);
        islands++;
        territory.put(node, 1);
    }

    public void merge(String node1, String node2) {

        String node1Root = find(node1);
        String node2Root = find(node2);

        if (!node1Root.equals(node2Root)) {
            father.put(node1Root, node2Root);
            islands--;
            territory.put(node1Root, territory.get(node1Root) + territory.get(node2Root));
        }
    }

    // find & compress tree
    public String find(String node) {

        String root = node;
        // find the stem root
        while (!root.equals(father.get(root))) {
            father.put(root, father.get(root));
            root = father.get(root);
        }

        // compress the tree
        while (!node.equals(father.get(node))) {
            father.put(node, root);
            node = father.get(node);
            territory.put(node, territory.get(node) - 1);
        }

        return root;
    }
}