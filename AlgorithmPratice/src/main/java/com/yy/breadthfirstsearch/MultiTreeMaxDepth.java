package com.yy.breadthfirstsearch;

import com.yy.common.MultiTreeNode;
import javafx.util.Pair;

import java.util.*;

/**
 * @author yy
 * @create 2020/8/8 21:08
 * 给定一个 N 叉树，找到其最大深度。
 *
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 *
 * 例如，给定一个 3叉树 :
 *  *          1
 *  *        / | \
 *  *      3   2   4
 *  *    / \
 *  *   5   6
 * 我们应返回其最大深度，3。
 * 说明:
 * 树的深度不会超过 1000。
 * 树的节点总不会超过 5000。
 */
public class MultiTreeMaxDepth {

    public static void main(String[] args) {
        MultiTreeNode root = new MultiTreeNode(1);
        MultiTreeNode child1 = new MultiTreeNode(3);
        MultiTreeNode child2 = new MultiTreeNode(2);
        MultiTreeNode child3 = new MultiTreeNode(4);
        List<MultiTreeNode> children = new ArrayList<>();
        children.add(child1);
        children.add(child2);
        children.add(child3);
        root.setChildren(children);
        MultiTreeNode child4 = new MultiTreeNode(5);
        MultiTreeNode child5 = new MultiTreeNode(6);
        List<MultiTreeNode> children2 = new ArrayList<>();
        children2.add(child4);
        children2.add(child5);
        child1.setChildren(children2);
        System.out.println(maxDepth(root));
    }

    /**
     * 使用递归 说白了就是求树的深度
     * @param root rootNode
     * @return maxDepth
     */
    public static int maxDepth(MultiTreeNode root) {
        //递归条件：当前节点不是叶子节点  基本条件：当前节点为叶子节点
        if (null == root) {
            return 0;
        }
        List<MultiTreeNode> children = root.getChildren();
        if (null == children || children.isEmpty()) {
            //是叶子节点，return 1
            return 1;
        }
        int res = 0;
        //不是叶子节点，递归查询叶子节点的最大深度
        for (int i = 0; i < children.size(); i++) {
            int temp = maxDepth(children.get(i));
            if (temp > res) {
                res = temp;
            }
        }
        //返回最大深度
        return res + 1;
    }

    //递归
    public static int maxDepth2(MultiTreeNode root) {
        if (null == root) {
            return 0;
        }
        List<MultiTreeNode> children = root.getChildren();
        if (null == children || children.isEmpty()) {
            return 1;
        }
        List<Integer> heights = new LinkedList<>();
        for (MultiTreeNode item : root.getChildren()) {
            heights.add(maxDepth(item));
        }
        return Collections.max(heights) + 1;
    }

    //迭代
    public int maxDepth3(MultiTreeNode root) {
        Queue<Pair<MultiTreeNode, Integer>> stack = new LinkedList<>();
        if (root != null) {
            //Pair key-value结构 可以理解为map的一个entry
            stack.add(new Pair(root, 1));
        }

        int depth = 0;
        while (!stack.isEmpty()) {
            Pair<MultiTreeNode, Integer> current = stack.poll();
            root = current.getKey();
            int current_depth = current.getValue();
            if (root != null) {
                depth = Math.max(depth, current_depth);
                for (MultiTreeNode c : root.children) {
                    stack.add(new Pair(c, current_depth + 1));
                }
            }
        }
        return depth;
    }
}
