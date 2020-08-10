package com.yy.depthfirstsearch;

import com.yy.common.TreeNode;

/**
 * @author yy
 * @create 2020/8/10 21:56
 * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 * 示例 1:
 * 给定二叉树 [3,9,20,null,null,15,7]
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 * 示例 2:
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 */
public class CheckTreeIsBalanced {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(isBalanced(root));
    }

    /**
     * 既然进行树平衡性的判断，从根节点开始，要么发现深度大于1，则说明不平衡；要么遍历完整个树的深度
     * @param root rootNode
     * @return isBalanced
     */
    public static boolean isBalanced(TreeNode root) {
        return -1 != recurSubTree(root);
    }

    /**
     * 递归子树 后序遍历 + 剪枝 （从底至顶）
     * 时间复杂度 O(N)：  N为树的节点数；最差情况下，需要递归遍历树的所有节点。
     * 空间复杂度 O(N)： 最差情况下（树退化为链表时），系统递归需要使用 O(N)的栈空间。
     * @param root rootNode
     * @return -1 if not balance,or tree depth
     */
    public static int recurSubTree(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int leftDepth = recurSubTree(root.left);
        //子树不平衡，直接返回-1
        if (-1 == leftDepth) {
            return -1;
        }
        int rightDepth = recurSubTree(root.right);
        if (-1 == rightDepth) {
            return -1;
        }
        int diff = leftDepth - rightDepth;
        //左右深度差大于1，不平衡
        if (Math.abs(diff) > 1) {
            return -1;
        }
        //返回当前根节点的高度
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     *
     * @param root rootNode
     * @return isBalanced
     */
    public static boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        }
        //直接求左右子树的深度，满足平衡性要求之后再递归求左右子树的平衡性
        if (Math.abs(depth(root.left) - depth(root.right)) <= 1) {
            return isBalanced2(root.left) && isBalanced2(root.right);
        }else {
            return false;
        }
    }

    private static int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left != null && root.right != null) {
            return Math.max(depth(root.left), depth(root.right)) + 1;
        } else if (root.left != null) {
            return depth(root.left) + 1;
        } else {
            return depth(root.right) + 1;
        }
    }
}
