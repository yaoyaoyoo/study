package com.yy.strs;

//给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
//示例 1:
//输入: "Let's take LeetCode contest"
//输出: "s'teL ekat edoCteeL tsetnoc" 
//注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。

public class ReverseWords {

    public static void main(String[] args) {
        System.out.println(reverseWords("I love u"));
    }

    public static String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        String[] arr = s.split(" ");
        for (int j = 0 ; j < arr.length;j++) {
            char[] c = arr[j].toCharArray();
            int left = 0,right = c.length - 1;

            while (left < right) {
                char temp = c[left];
                c[left] = c[right];
                c[right] = temp;
                left++;
                right--;
            }
            sb.append(new String(c));
            sb.append(" ");
        }
        //trim()移除开头或结尾的空白！！
        return sb.toString().trim();
    }
}
