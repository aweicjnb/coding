package org.coding.bin_tire;

import java.util.*;
import java.util.stream.Collectors;

public class SortTrie {
    public static void main(String[] args) {
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);
//        sort3(root);

        int[] root1 = new int[]{1,2,4,5,3};
        int[] root2 = new int[]{4,2,5,1,3};
        TreeNode node = buildTrie(root1, root2);
        node.right.right = new TreeNode(-1);
//        sort1(node);
//        sort2(node);
//        sort3(node);
        morris(node);
    }

    /**
     * 先序遍历
     */
    public static void sort1(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            ans.add(node.value);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        ans.forEach(System.out::println);
    }

    /**
     * 中序遍历
     */
    public static void sort2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                ans.add(root.value);
                root = root.right;
            }
        }
        ans.forEach(System.out::println);
    }

    /**
     * 后续遍历
     */
    public static void sort3(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            ans.add(node.value);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }

        Collections.reverse(ans);
        ans.forEach(System.out::println);
    }


    /**
     * Morris算法-先序遍历
     * 通过迭代的方式最好的时间O(N),空间O(N)
     * Morris算法可以把空间优化到O(1)
     *
     * morris算法是把叶子结点的右结点利用起来 指向中序遍历的下一个结点
     * 1将左子树的最后一个结点 与当前结点连接起来
     */
    public static void morris(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        while (root != null) {
            if (root.left != null) {
                TreeNode pre = root.left;
                while (pre.right != null && pre.right != root) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    list.add(root.value);
                    pre.right = root;
                    root = root.left;
                } else {
                    pre.right = null;
                    root = root.right;
                }
            } else {
                System.out.println("---");
                System.out.println(root.value);
                System.out.println("---");
                list.add(root.value);
                root = root.right;
            }
        }
        list.forEach(System.out::println);
    }

    /**
     * 前序遍历+中序遍历 ==> 构建一棵树
     */
    public static TreeNode buildTrie(int[] root1, int[] root2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < root2.length; ++i) {
            map.put(root2[i], i);
        }
        return buildTrie(root1, root2, 0, root1.length - 1, 0, root2.length - 1, map);
    }

    private static TreeNode buildTrie(
            int[] root1,
            int[] root2,
            int left1,
            int right1,
            int left2,
            int right2,
            Map<Integer, Integer> map
    ) {
        if (left1 > right1 || left2 > right2) {
            return null;
        }
        TreeNode node = new TreeNode(root1[left1]);
        int mid = map.get(root1[left1]);
        int leftNum = mid - left2;

        node.left = buildTrie(root1, root2, left1 + 1, left1 + leftNum, left2, mid - 1, map);
        node.right = buildTrie(root1, root2, left1 + 1 + leftNum, right1, mid + 1, right2, map);
        return node;
    }

}


