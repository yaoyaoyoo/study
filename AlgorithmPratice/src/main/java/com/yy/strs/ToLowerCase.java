package com.yy.strs;

//实现函数 ToLowerCase()，该函数接收一个字符串参数 str，并将该字符串中的大写字母转换成小写字母，之后返回新的字符串。
//        示例 1：
//        输入: "Hello"
//        输出: "hello"
//        示例 2：
//        输入: "here"
public class ToLowerCase {

    public static void main(String[] args) {
        System.out.println(toLowerCase("Hello"));
        System.out.println(toLowerCase("here"));
        System.out.println(toLowerCase("LOVE"));
    }



    /**
     * 转化为小写
     * ASCII码 大小写差32，A从65开始，a从97开始，
     * @param str str
     * @return lower
     */
    public static String toLowerCase(String str) {
        if (null == str || str.trim().length() == 0) {
            return str;
        }
        int size = str.length();
        char[] result = new char[size];
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                c = (char)(c + 32) ;
            }
            result[i] = c;
        }
        return new String(result);
    }
}
