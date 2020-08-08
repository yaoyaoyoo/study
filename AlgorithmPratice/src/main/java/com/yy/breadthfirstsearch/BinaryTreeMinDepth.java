package com.yy.breadthfirstsearch;

import com.yy.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yy
 * @create 2020/8/8 20:10
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明: 叶子节点是指没有子节点的节点。
 * 示例:
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最小深度  2.
 */
public class BinaryTreeMinDepth {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(minDepth(root));
        System.out.println(minDepth(root.right));
        System.out.println(minDepth(root.left));

        System.out.println(minDepth2(root));
        System.out.println(minDepth2(root.right));
        System.out.println(minDepth2(root.left));
    }

    /**
     *
     * @param root rootNode
     * @return minDepth
     */
    public static int minDepth(TreeNode root) {
        //叶子节点，即没有子节点的节点，一层一层的遍历
        // 最短路径上的节点数量，即最小深度
        if (root == null) {
            return 0;
        }
        int res = 0;
        //一层一层的遍历节点，如果找到叶子节点，返回当前深度
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.offer(root);
        //队列不为空时进行遍历
        while (!nodes.isEmpty()) {
            int size = nodes.size();
            for (int i = 0; i < size; ++i) {
                TreeNode node = nodes.poll();
                if (null == node.left && null == node.right) {
                    //查找到叶子节点，树的深度+1 并返回。
                    res++;
                    return res;
                } else {
                    if (null != node.left) {
                        nodes.offer(node.left);
                    }
                    if (null != node.right) {
                        nodes.offer(node.right);
                    }
                }
            }
            //本层节点遍历完毕，树的深度+1
            res++;
        }
        return res;
    }

    /**
     * 递归解法  此算法会把所有的节点都被遍历了
     * @param root rootNode
     * @return minDepth
     */
    public static int minDepth2(TreeNode root) {
        if (null == root) {
            return 0;
        }
        //递归条件 节点不是叶子节点  基本条件：节点为叶子节点
        if (null == root.left && null == root.right) {
            //叶子节点
            return 1;
        }
        //不是叶子节点，左孩子最少有一个不为空，计算左右孩子的深度
        int leftDepth = minDepth2(root.left);
        int rightDepth = minDepth2(root.right);

        //返回左右孩子的最小值 + 1
        //当左孩子或右孩子为空的时候应该返回depth + 1
        return root.left == null || root.right == null ?
                leftDepth + rightDepth + 1 :
                Math.min(leftDepth, rightDepth) + 1;
    }
}
