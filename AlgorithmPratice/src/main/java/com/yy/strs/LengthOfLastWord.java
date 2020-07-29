package com.yy.strs;

//给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。
// 如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
//如果不存在最后一个单词，请返回 0 。
//说明：一个单词是指仅由字母组成、不包含任何空格字符的 最大子字符串。
//示例:
//输入: "Hello World"
//输出: 5

public class LengthOfLastWord {

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("Hello world"));
        System.out.println(lengthOfLastWord("a"));
        System.out.println(lengthOfLastWord("anc ab  "));
    }

    public static int lengthOfLastWord(String s) {
        String str = s.trim();
        int start = str.length() - 1;
        while(start > 0) {
            if (str.charAt(start) != ' ') {
                start--;
            } else {
                return str.length() -1 - start;
            }
        }
        return str.length();
    }
}
