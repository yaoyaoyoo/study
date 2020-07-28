package com.yy.array;

import java.util.Arrays;

//给你一个数组 arr ，请你将每个元素用它右边最大的元素替换，如果是最后一个元素，用 -1 替换。
//        完成所有替换操作后，请你返回这个数组。
//        示例：
//        输入：arr = [17,18,5,4,6,1]
//        输出：[18,6,6,6,1,-1]
//
public class ReplaceRightMaxElements {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(replaceElements(new int[]{17,18,5,4,6,1})));
    }

    public static int[] replaceElements(int[] arr) {
        if (null == arr || arr.length == 0) {
            return arr;
        }
        int max = arr[arr.length - 1];
        arr[arr.length - 1] = -1;
        for(int i = arr.length - 2; i >= 0; i--) {
            int tmp = arr[i];
            arr[i] = max;
            max = Math.max(tmp, max);
        }
        return arr;
    }
}
