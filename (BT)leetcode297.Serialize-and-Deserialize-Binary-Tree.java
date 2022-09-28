import java.util.ArrayDeque;
import java.util.Queue;

import javax.swing.tree.TreeNode;

public class Codec {

    // 創造空值的技巧！
    int INF = -2000;
    TreeNode emptyNode = new TreeNode(INF);

    // Time: O(N)
    // Space: O(N)
    public String serialize(TreeNode root) {

        if (root == null) {
            return "";
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            sb.append(curr.val + "_");
            if ((curr.val) != (emptyNode.val)) {
                queue.offer(curr.left != null ? curr.left : emptyNode);
                queue.offer(curr.right != null ? curr.right : emptyNode);
            }
        }

        return sb.toString();
    }

    // Time: O(N)
    // Space: O(N)
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        if ("".equals(data)) {
            return null;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        String[] array = data.split("_");
        TreeNode root = new TreeNode(Integer.parseInt(array[0]));
        queue.offer(root);

        for (int i = 1; i < array.length - 1; i += 2) {

            TreeNode curr = queue.poll();
            int left = Integer.parseInt(array[i]);
            int right = Integer.parseInt(array[i + 1]);

            // 要注意不要隨便比較物件！換成原始型別比較可以比較保險！
            if (left != INF) {
                curr.left = new TreeNode(left);
                queue.offer(curr.left);
            }

            if (right != INF) {
                curr.right = new TreeNode(right);
                queue.offer(curr.right);
            }

        }

        return root;
    }
}