package com.yy.strs;

//给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
//        注意：
//        num1 和num2 的长度都小于 5100.
//        num1 和num2 都只包含数字 0-9.
//        num1 和num2 都不包含任何前导零。
//        你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
public class AddStrings {

    public static void main(String[] args) {
        System.out.println(addStrings("0", "0"));
        System.out.println(addStrings("1", "9"));
        System.out.println(addStrings("9", "99"));

        System.out.println(addStrings("134", "444579"));

    }

    /**
     * '0' --> 48 '9' -->57
     * @param num1 num1
     * @param num2 num2
     * @return addStrings
     */
    public static String addStrings(String num1, String num2) {
        StringBuilder res = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1;
        //进位
        int carry = 0;
        while (i >= 0 || j >= 0) {
            //若指定位置上无数据 使用0填充给
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            //对应每一位的运算结果
            int tmp = n1 + n2 + carry;
            //和大于10 取商 == 1；小于10 取商 == 0
            carry = tmp / 10;
            //结果中的数应为和取余
            res.append(tmp % 10);
            i--;
            j--;
        }
        //如果计算后，最长数的位数会发生变化 新增最高位1  eg：1+9 = 10
        if (carry == 1) {
            res.append(1);
        }
        //添加顺序从低位开始，返回前翻转一下
        return res.reverse().toString();
    }
}
