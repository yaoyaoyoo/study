package com.yy.array;

//给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
//        说明:
//        初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
//        你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
//        示例:
//        输入:
//        nums1 = [1,2,3,0,0,0], m = 3
//        nums2 = [2,5,6],       n = 3
//        输出: [1,2,2,3,5,6]

import java.util.Arrays;

public class MergeSortedArray {

    public static void main(String[] args) {
        int[] array1 = new int[]{1, 2, 4, 6, 0 , 0 , 0};
        int[] array2 = new int[]{0, 1, 2};
        merge(array1, 4, array2, 3);
        System.out.println(Arrays.toString(array1));;

    }

    /**
     * [1,2,3,4,0,0,0], [2,4,6]
     * 结果：预期[1,2,2,3,4,4,6]
     * 输入中，两个数组都是已排序过的，根据预期结果来看大容量的数组后面的0被填充上了第n大的数据
     * 考虑使用两个指针，从两个数组的最大元素开始，二者谁大则谁移动到数组右侧
     * 循环终止条件为小的数组数据完毕
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
      //nums1拥有足够空间,初始化指针
        int i = m - 1;
        int j = n - 1;
        //定义一个变量，记录值较大的数据应该存放的位置
        int position = nums1.length - 1;
        while (j >= 0) {
            //数组2的数大 j>=0不会越界，
            //i需要进行<0判断 防止越界(当两数组中最小的数在数组2时，当数组1的所有有效元素已移动完毕，i指针为-1，
            // 再次执行循环，移动数组2的最后元素时，数组访问越界)
            if (i < 0 || nums2[j] >= nums1[i]) {
                nums1[position] = nums2[j];
                j--;
            } else {
                nums1[position] = nums1[i];
                i--;
            }
            position--;
        }
    }
}
