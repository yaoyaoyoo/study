package com.yy.array;

//algorithms：ælgərɪðmz

import java.util.Arrays;

/**
 * @author yy
 * @create 2020/7/31 0:13
 * @desc common sort algorithms
 */
public class SortAlgorithms {

    public static void main(String[] args) {
        int[] array = new int[]{1,3,2};

        //        int[] array = new int[]{1,6,3,8,33,27,66,9,7,88};
//        selectionSort(array);
//        bubbleSort(array);
        quickSort(array);
    }

    /**
     * 冒泡排序
     * 1.相邻两个数两两相比，n[i]跟n[j+1]比，如果n[i]>n[j+1]，则将连个数进行交换，
     * 2.重复以上步骤，第一趟结束后，最大数就会被确定在最后一位，这就是冒泡排序又称大（小）数沉底，
     * 3.重复以上步骤，直到i=n-1结束，排序完成。
     * 复杂度
     * 1.不管原始数组是否有序，时间复杂度都是O（n^2），
     * 因为没一个数都要与其他数比较一次，（n-1）^2次，分解：n^2+2n-1,  去掉低次幂和常数，剩下n^2,所以最后的时间复杂度是n^2
     * 2.空间复杂度是O（1）,因为只定义了辅助变量，与n的大小无关，所以空间复杂度为O（1）
     * @param array array
     */
    public static void bubbleSort(int[] array) {
        if (null == array || array.length <= 1) {
            return;
        }
        int length = array.length;
        int temp;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }

    /**
     * 选择排序 :
     * 1.  第一个跟后面的所有数相比，如果小于（或小于）第一个数的时候，暂存较小数的下标，第一趟结束后，将第一个数，
     * 与暂存的那个最小数进行交换，第一个数就是最小（或最大的数）
     * 2.  下标移到第二位，第二个数跟后面的所有数相比，一趟下来，确定第二小（或第二大）的数
     * 不管原始数组是否有序，时间复杂度都是O（n2），
     * 因为没一个数都要与其他数比较一次，（n-1）^2次，分解：n^2-2n+1,  去掉低次幂和常数，剩n^2,所以最后的时间复杂度是n^2
     * 间复杂度是O（1）,因为只定义了辅助变量，与n的大小无关，所以空间复杂度为O（1）
     * @param array array
     */
    public static void selectionSort(int[] array) {
        if (null == array || array.length <= 1) {
            return;
        }
        int length = array.length;
        int temp;
        //外层循环length - 1次，最后一个元素肯定为最大的
        for (int i = 0; i < length - 1; i++) {
            //初始化的最小下标即为自己
            int index = i;
            //j = i + 1 第i个元素，只需要和自己后面的数据进行比较，自己前面的已经排序完毕
            for (int j = i + 1; j < length; j++) {
                if (array[i] > array[j]) {
                    index = j;
                }
            }
            //交换最小的元素
            temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
        System.out.println(Arrays.toString(array));
    }

    /**
     * 快速排序
     * 选一个数作为基数（这里选的是第一个数），大于这个基数的放到右边，小于这个基数的放到左边，等于这个基数的数可以放到左边或右边，这里放到了左边，
     * 一趟结束后，将基数放到中间分隔的位置，第二趟将数组从基数的位置分成两半，分割后的两个的数组继续重复以上步骤，
     * 选基数，将小数放在基数左边，将大数放到基数的右边，在分割数组，，，直到数组不能再分为止，排序结束。
     * 复杂度
     * 时间复杂度：最坏情况就是每一次取到的元素就是数组中最小/最大的，这种情况其实就是冒泡排序了(每一次都排好一个元素的顺序)
     * 这种情况时间复杂度就好计算了，就是冒泡排序的时间复杂度：T[n] = n * (n-1) = n^2 + n==O(n^2);
     * 平均复杂度为O（nlog2n）
     * 空间复杂度：快速排序使用的空间是O(1)的，也就是个常数级；而真正消耗空间的就是递归调用了，因为每次递归就要保持一些数据：
     * 最优的情况下空间复杂度为:O(log2n)；每一次都平分数组的情况
     * 最差的情况下空间复杂度为：O( n )；退化为冒泡排序的情况
     * 所以平均空间复杂度为O（log2n）
     * @param array array
     */
    public static void quickSort(int[] array) {
        if (null == array || array.length <= 1) {
            return;
        }
        int length = array.length;
        sort(array, 0, length - 1);
        System.out.println(Arrays.toString(array));
    }

    public static void sort(int[] array,int start,int end) {
        if (start < end) {
            int left = start;
            int right = end;
            //基准数
            int temp = array[start];
            //左右指针未重合时进行循环
            while (left < right) {
                //右边的数大于左边的基准数 右指针左移
                while (left < right && array[right] > temp) {
                    right--;
                }
                //右边的数字小于或等于基本数，将右边的数放到左边 此时基准数所在的位置为右边比基准数temp不大的数
                array[left] = array[right];
                //不能在这里就移动指针，如果第一个元素就是最小值，left==right，
                //后面再执行array[right] = array[left]会导致第一轮循环后数组未按预期进行分组;
                //left++;
                //此时右指针不动，当左边的数不大于基准数，左指针右移
                while (left < right && array[left] <= temp) {
                    left++;
                }
                //左边的数字大于基本数，将左边的数放到右边
                array[right] = array[left];
            }
            //一趟循环结束，此时left=right，将基数放到这个重合的位置，
            array[left] = temp;
            //递归基准数左右的两个数组
            sort(array, start, left);
            sort(array, left + 1, end);
        }
    }


}
