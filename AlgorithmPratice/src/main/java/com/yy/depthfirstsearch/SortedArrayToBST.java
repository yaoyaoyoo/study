package com.yy.depthfirstsearch;

import com.yy.common.TreeNode;

/**
 * @author yy
 * @create 2020/8/8 22:23
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * 示例:
 *
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */

public class SortedArrayToBST {

    public static void main(String[] args) {
        int[] array = new int[] { -10, -3, 0, 5, 9 };
        TreeNode node = sortedArrayToBST(array);
        System.out.println("end");
    }

    /**
     * 对于二叉排序（查找）树来说，进行中序遍历就可以得到一个有序数组，现需要根据有序数组倒推出树结构
     * @param nums array
     * @return tree
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        return generator(nums, 0, nums.length - 1);
    }

    public static TreeNode generator(int[] array, int start, int end) {
        //递归的取数组的中间节点为树的根节点，当left > right时 结束递归
        if (start <= end) {
            //此处选择中间位置左边的数字作为根节点
            int mid = (start + end) / 2;
            //选择中间位置右边的数字作为根节点 int mid = (left + right + 1) / 2;
            //选择任意一个中间位置数字作为根节点 int mid = (left + right + rand.nextInt(2)) / 2;
            TreeNode root = new TreeNode(array[mid]);
            root.left = generator(array, start, mid - 1);
            root.right = generator(array, mid + 1, end);
            return root;
        }
        return null;
    }

}
