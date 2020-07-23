package com.yy.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
//
//        你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
//        示例:
//        给定 nums = [2, 7, 11, 15], target = 9
//        因为 nums[0] + nums[1] = 2 + 7 = 9
//        所以返回 [0, 1]
//        Related Topics
//        数组
//        哈希表

public class TwoSum {

    public static void main(String[] args) {
        int[] result = twoSum(new int[]{2, 4, 6}, 6);
        System.out.println(Arrays.toString(result));

        int[] result2 = twoSum2(new int[]{2, 4, 6}, 6);
        System.out.println(Arrays.toString(result2));
    }

    /**
     *O(n^2)
     */
    public static int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            for (int j = 1; j <= length - 1; j++) {
                if (target - nums[i] == nums[j]) {
                    return new int[] { i, j };
                }
            }
        }
        return new int[0];
    }

    /**
     * O(n)
     */
    public static int[] twoSum2(int[] nums, int target) {
        int length = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < length; i++) {
            int temp = target -nums[i];
            if (null != map.get(temp) && i != map.get(temp)) {
                return new int[]{i, map.get(target - nums[i])};
            }
        }
        return new int[0];
    }
}
