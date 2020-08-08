package com.yy.breadthfirstsearch;

import com.yy.common.TreeNode;

import java.util.*;

/**
 * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class LevelPrintBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(levelOrder(root));
        System.out.println(levelOrder2(root));
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        //每一层打印到一行，从左到右
        if (null == root) {
            return new ArrayList<>();
        }
        //每一层应该对应一个list，存放本层的节点
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        //建立一个hash表，存放每层的所有节点
        Map<Integer, List<TreeNode>> map = new HashMap<>();
        map.put(1, list);
        int layers = 1;
        //判断本层当前节点是否有子节点
        boolean hasNextLayerNode = hasNextLayerNode(root, false);
        //有子节点时才进行循环
        while (hasNextLayerNode) {
            //layers++，重新初始化hasNextLayerNode
            layers++;
            hasNextLayerNode = false;
            List<TreeNode> tempList = new ArrayList<>();
            //遍历上层节点的子节点，也就是本层节点
            for (TreeNode node : map.get(layers - 1)) {
                if (null != node.left && null != node.left.val) {
                    tempList.add(node.left);
                    hasNextLayerNode = hasNextLayerNode(node.left, hasNextLayerNode);
                }
                if (null != node.right && null != node.right.val) {
                    tempList.add(node.right);
                    hasNextLayerNode = hasNextLayerNode(node.right, hasNextLayerNode);
                }
            }
            map.put(layers, tempList);
        }
        //输出结果
        List<List<Integer>> res = new ArrayList<>();
        for (List<TreeNode> list1 : map.values()) {
            if (list1.isEmpty()) {
                continue;
            }
            List<Integer> temp = new ArrayList<>();
            for (TreeNode node : list1) {
                temp.add(node.val);
            }
            res.add(temp);
        }
        return res;
    }

    public static boolean hasNextLayerNode(TreeNode node, boolean hasNextLayerNode) {
        return hasNextLayerNode || node.left != null || node.right != null;
    }

    /**
     * 使用广度优先搜索（BFS）的常用解题思路，队列先入先出
     * @param root root node
     * @return result
     */
    public static List<List<Integer>> levelOrder2(TreeNode root) {
        //创建一个队列 存放各个节点。队列的使用只需要往队列尾部添加节点，所以使用链表
        Queue<TreeNode> nodes = new LinkedList<>();
        //存放结果的列表
        List<List<Integer>> result = new LinkedList<>();
        if (null == root) {
            return result;
        }
        nodes.add(root);
        //当队列中没有节点时，停止
        while (!nodes.isEmpty()) {
            //遍历队列每层节点 每一次循环 对应一层节点
            List<Integer> temp = new LinkedList<>();
            for (int i = nodes.size(); i > 0; i--) {
                //队列尾部出列
                TreeNode node = nodes.poll();
                temp.add(node.val);
                if (node.left != null) {
                    nodes.offer(node.left);
                }
                if (node.right != null) {
                    nodes.offer(node.right);
                }
            }
            if (!temp.isEmpty()) {
                //如果要求从叶子节点遍历，直接list.add(0, temp)插入列表头部即可
                result.add(temp);
            }
        }
        return result;
    }

}
