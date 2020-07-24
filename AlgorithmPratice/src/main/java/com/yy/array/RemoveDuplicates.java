package com.yy.array;

import java.util.Arrays;


//给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
//        不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
//        示例 1:
//        给定数组 nums = [1,1,2],
//        函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
//        你不需要考虑数组中超出新长度后面的元素。
//        示例 2:
//        给定 nums = [0,0,1,1,1,2,2,3,3,4],
//        函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
//        你不需要考虑数组中超出新长度后面的元素。
//        说明:
//        为什么返回数值是整数，但输出的答案是数组呢?
//        请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
//        你可以想象内部操作如下:
//        // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
//        int len = removeDuplicates(nums);
//        // 在函数里修改输入数组对于调用者是可见的。
//        // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
//        for (int i = 0; i < len; i++) {
//        print(nums[i]);
//        }
public class RemoveDuplicates {

    public static void main(String[] args) {
        int[] array = new int[]{1, 1, 2, 2, 2, 2, 3, 4};
        System.out.println(removeDuplicates(array));
        System.out.println(Arrays.toString(array));

        int[] array2 = new int[]{1, 1, 2, 2, 2, 2, 3, 4};
        System.out.println(removeDuplicates2(array2));
        System.out.println(Arrays.toString(array2));

    }

    /**
     *错误的思路 解题错误
     */
    public static int removeDuplicates(int[] nums) {
        //已排序数组，只可能相邻元素相等
        //两个指针 一个记录当前位置，一个记录重复元素的位置 找到之后指针后移 ，然后重复
        int length = nums.length;
        int duplicate = 0;
        int j = length - 2;
        int temp;
        for(int i = length - 1; i > 0; i--) {
            if (nums[i] == nums[j]) {
                //重复之后 重复的元素怎么办，不能影响后续元素的顺序
                //重复元素要移动到数组的最后 根据duplicate可以获取到应该移动到的索引处
                duplicate++;
                //i和i+1互换  换过之后数组元素顺序错乱 错误！
                if (i != length - 1) {
                    temp = nums[i];
                    nums[i] = nums[length - duplicate];
                    nums[length - duplicate] = temp;
                }
            }
            j--;
        }
        return length - duplicate;
    }

    /**
     *审题的时候理解有误，根据问题描述只需要数组的前length - duplicate个元素为不重复元素即可
     * 即只需要在遍历的时候把不重复的元素按顺序移动到数组左侧即可
     * 下次遇到这种问题 可以把理想的结果想写出来，然后对比题目的描述，观察二者的不同，方便理解题意
     */
    public static int removeDuplicates2(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int length = nums.length;
        int i = 0;
        int j = 1;
        while (j < length) {
            if (nums[i] != nums[j]) {
                nums[i + 1] = nums[j];
                i++;
            }
            j++;
        }
        return i + 1;
    }
}
