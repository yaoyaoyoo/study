package com.yy.array;


//给定一个未经排序的整数数组，找到最长且连续的的递增序列，并返回该序列的长度。
//        示例 1:
//        输入: [1,3,5,4,7]
//        输出: 3
//        解释: 最长连续递增序列是 [1,3,5], 长度为3。
//        尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。
//        示例 2:
//        输入: [2,2,2,2,2]
//        输出: 1
//        解释: 最长连续递增序列是 [2], 长度为1。
//        注意：数组长度不会超过10000。

public class FindLengthOfLCIS {

    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3, 1, 5};
        int result = findLengthOfLCIS(input);
        System.out.println(result);
    }

    /**
     *O(n)
     */
    public static int findLengthOfLCIS(int[] nums) {
        int maxLength = 1;
        int tempMax = 1;
        for(int i = 0; i < nums.length - 1; i++) {
            //此次循环的最大长度
            if (nums[i + 1] - nums[i] > 0) {
                //后一个元素大于前一个，最大长度+1
                tempMax++;
            } else {
                //后一个元素大于前一个，下次循环需要重新计算最大长度的序列，重置序列长度
                tempMax = 1;
            }
            //本次循环的最大长度和当前循环完找到的最大长度比较并赋值
            if (tempMax > maxLength) {
                maxLength = tempMax;
            }
        }
        return maxLength;
    }
}
