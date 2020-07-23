package com.yy.array;

//
//一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
//        在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
//        示例 1:
//        输入: [0,1,3]
//        输出: 2
//        示例 2:
//
//        输入: [0,1,2,3,4,5,6,7,9]
//        输出: 8
//        限制：
//        1 <= 数组长度 <= 10000
//
public class MissingNumber {

    public static void main(String[] args) {
        int[] intput = {0,1,3};
        int result = missingNumber(intput);
        System.out.println("missingNumber: " +  result);

        int result2 = missingNumber2(intput);
        System.out.println("missingNumber2: " +  result);
    }

    public static int missingNumber(int[] nums) {
        //两层循环 查找不到则返回
        for (int i = 0; i < nums.length + 1; i++) {
            boolean exist = false;
            for (int num : nums) {
                if (num == i) {
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                return i;
            }
        }
        return 0;
    }

    /**
     *已排序数组，使用二分查找
     * 正确的顺序下中间位置的元素如果为m，说明异常数在右侧，移动指针，反之亦然
     */
    public static int missingNumber2(int[] nums) {
        int i = 0,j = nums.length - 1;
        while (i <= j) {
            int m = (i + j) / 2;
            if (nums[m] == m) {
                i = m + 1;
            } else {
                j = m - 1;
            }
        }
        return i;
    }
}
