package com.yy.array;

//给你一个整数数组 arr 和两个整数 k 和 threshold 。
//请你返回长度为 k 且平均值大于等于 threshold 的子数组数目。
//示例 1：
//输入：arr = [2,2,2,2,5,5,5,8], k = 3, threshold = 4
//输出：3
//解释：子数组 [2,5,5],[5,5,5] 和 [5,5,8] 的平均值分别为 4，5 和 6 。其他长度为 3 的子数组的平均值都小于 4 （threshold 的值)。
//示例 2：
//输入：arr = [1,1,1,1,1], k = 1, threshold = 0
//输出：5
//子数组指的是连续的

public class NumOfSubarrays {

    public static void main(String[] args) {
        System.out.println(numOfSubarrays(new int[]{2,2,2,2,5,5,5,8}, 3, 4));
        System.out.println(numOfSubarrays2(new int[]{2,2,2,2,5,5,5,8}, 3, 4));
        System.out.println(numOfSubarrays2(new int[]{493,625,763,7114,5272,3874,607,2161,6828,
                        8657,465,3086,4777,5098,3219,674,3766,4016,3532,8423,8084,5458,145,5674,
                        8737,8389,7590,197,7155,6989,9273,3670,959,7240,9135,8311,3690,1305,6315,
                        1857,4276,2091,2911,3379,3456,5314,4823,5261,2943,3574,7535,3321,6,9730,654,
                        5869,6747,3078,8707,9590,1484,5550,8438,6766,3747,3183,8335,7802,4438,2946,152,
                        8969,9622,7389,6384,1296,3937,7380,5436,939,4388,8702,4513,2609,6917,6881,3887,8802,
                        5183,5686,4313,6096,7336,6982,5420,6526,297,2397,7023,9438,1443,7914,2092,591,878,
                        5665,3088,7726,9303,7175,2008,9465,27,9476,8100,1550,4405,381,507,6344,2715,3139,
                        8639,3279,1465,2286,3009,2562,6983,2233,6106,4845,7545,84,1556,6622,5212,8712,
                        9210,1009,4381,4903,9743,2928,4016,4578,8647,1086,1291,348,6981}
        ,44
        ,859));
    }

    public static int numOfSubarrays(int[] arr, int k, int threshold) {
        int left = 0, right = left + k,result = 0;

        //这里注意 right是小于并且等于arr.length的
        while (right <= arr.length) {
            int sum = 0;
            for (int i = left; i < right; i++) {
                sum+= arr[i];
            }
            //大于等于阈值
            if (sum / k >= threshold) {
                result++;
            }
            left++;
            right++;
        }
        return result;
    }

    public static int numOfSubarrays2(int[] arr, int k, int threshold) {
//        int left = 0, right = left + k, result = 0;
//        int sum = 0;
//        //好蠢的写法！！ int i = left只是说在循环刚开始left赋值给i，每次循环完只会执行left++，跟i没有关系！！！
//        for (int i = left; left < right; left++) {
//            sum += arr[i] - threshold;
//        }
        int sum = 0, result = 0;
        for (int i = 0; i < k; i++) {
            sum += arr[i] - threshold;
        }
        if (sum >= 0) {
            result++;
        }
        int temp = k;
        for (int i = 0; i < arr.length - k; i++) {
            sum += arr[temp] - arr[i];
            if (sum >= 0) {
                result++;
            }
            temp++;
        }
        return result;
    }
}
