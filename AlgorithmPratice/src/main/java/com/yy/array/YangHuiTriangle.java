package com.yy.array;

import java.util.ArrayList;
import java.util.List;

//给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
//        在杨辉三角中，每个数是它左上方和右上方的数的和。
//        示例:
//        输入: 5
//        输出:
//        [
//        [1],
//        [1,1],
//        [1,2,1],
//        [1,3,3,1],
//        [1,4,6,4,1]

public class YangHuiTriangle {

    public static void main(String[] args) {
        System.out.println(generate(5));
    }

    public static List<List<Integer>> generate(int numRows) {
        if (numRows == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> init = new ArrayList<>();
        init.add(1);
        res.add(init);
        for (int rowNum = 1; rowNum < numRows; rowNum++) {
            List<Integer> aboveList = res.get(rowNum - 1);
            List<Integer> temp = new ArrayList<>();
//            temp.add(1);
//            //这里循环要注意 j是要小于i的！！！ i其实就是指当前的行数 从0开始计算的 i=1时 其实是第二行
//            for (int j = 1; j < i; j++) {
//                temp.add(aboveList.get(j) + aboveList.get(j - 1));
//            }
//            temp.add(1);
            for (int j = 0; j <= rowNum; j++ ) {
                if (j == 0 || j == rowNum) {
                    temp.add(1);
                } else {
                    temp.add(aboveList.get(j) + aboveList.get(j - 1));
                }
            }
            res.add(temp);
        }
        return res;
    }
}
