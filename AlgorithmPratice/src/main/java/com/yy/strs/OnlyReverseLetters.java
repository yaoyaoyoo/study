package com.yy.strs;

import java.util.Stack;

/**
 * @author yy
 * @create 2020/8/4 0:13
 *  给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。
 *  示例 1：
 *  输入："ab-cd"
 *  输出："dc-ba"
 *  示例 2：
 *  输入："a-bC-dEf-ghIj"
 *  输出："j-Ih-gfE-dCba"
 *  示例 3：
 *  输入："Test1ng-Leet=code-Q!"
 *  输出："Qedo1ct-eeLg=ntse-T!"
 *
 *  提示：
 * S.length <= 100
 * 33 <= S[i].ASCIIcode <= 122
 * S 中不包含 \ or "
 */
public class OnlyReverseLetters {

    public static void main(String[] args) {
        System.out.println(onlyReverseLetters("ab-cd"));
        System.out.println(onlyReverseLetters2("a-bC-dEf-ghIj"));
        System.out.println(onlyReverseLetters2("Test1ng-Leet=code-Q!"));
        System.out.println(onlyReverseLetters3("z<*zj"));
    }

    /**
     * @param str
     * @return
     */
    public static String onlyReverseLetters(String str) {
        if (null == str || str.trim().length() <= 1) {
            return str;
        }
        //双指针
        int length = str.length();
        int start = 0;
        int end = length - 1;
        char[] temp = new char[length];
        while (start <= end) {
            char startChar = str.charAt(start);
            char endChar = str.charAt(end);
            if (!isLetter(startChar)) {
                temp[start] = startChar;
                start++;
                continue;
            }
            if (!isLetter(endChar)) {
                temp[end] = endChar;
                end--;
                continue;
            }
            temp[start] = endChar;
            temp[end] = startChar;
            start++;
            end--;
        }
        return new String(temp);
    }

    /**
     * A~Z  65~90 a~z 97~122
     */
    public static boolean isLetter(char c) {
        return (c >= 65 && c <= 90) ||  (97 <= c && c <= 122);
    }


    /**
     * @param str
     * @return
     */
    public static String onlyReverseLetters2(String str) {
        if (null == str || str.trim().length() <= 1) {
            return str;
        }
        //双指针
        int length = str.length();
        int start = 0;
        int end = length - 1;
        char[] chars = str.toCharArray();
        while (start < end) {
            char startChar = chars[start];
            char endChar = chars[end];
            while(start < end && !Character.isLetter(startChar)) {
                start++;
            }
            while (start < end && !Character.isLetter(endChar)) {
                end--;
            }
            if (start < end) {
                chars[start] = endChar;
                chars[end] = startChar;
                start++;
                end--;

            }
        }
        return new String(chars);
    }

    /**
     * 使用栈 后入先出
     * @param str
     * @return
     */
    public static String onlyReverseLetters3(String str) {
        Stack<Character> letters = new Stack();
        for (char c: str.toCharArray()) {
            //如果是字母 入栈
            if (Character.isLetter(c)) {
                letters.push(c);
            }
        }

        StringBuilder ans = new StringBuilder();
        for (char c: str.toCharArray()) {
            //是字母，从栈里出一个
            if (Character.isLetter(c)) {
                ans.append(letters.pop());
            } else {
                //不是字母，顺序不变
                ans.append(c);
            }
        }
        return ans.toString();
    }
}
