package com.yy.strs;
//给你两个字符串，请你从这两个字符串中找出最长的特殊序列。
//        「最长特殊序列」定义如下：该序列为某字符串独有的最长子序列（即不能是其他字符串的子序列）。
//        子序列 可以通过删去字符串中的某些字符实现，但不能改变剩余字符的相对顺序。空序列为所有字符串的子序列，任何字符串为其自身的子序列。
//        输入为两个字符串，输出最长特殊序列的长度。如果不存在，则返回 -1。
//        示例 1：
//        输入: "aba", "cdc"
//        输出: 3
//        解释: 最长特殊序列可为 "aba" (或 "cdc")，两者均为自身的子序列且不是对方的子序列。
//        示例 2：
//        输入：a = "aaa", b = "bbb"
//        输出：3
//        示例 3：
//        输入：a = "aaa", b = "aaa"
//        输出：-1
//        提示：
//        两个字符串长度均处于区间 [1 - 100] 。
//        字符串中的字符仅含有 'a'~'z' 。
public class FindLargeUnusualSeqlength {

    public static void main(String[] args) {
        System.out.println(findLargeUnusualSequenceLength("aba", "cdc"));
        System.out.println(findLargeUnusualSequenceLength("aaa", "bbb"));
        System.out.println(findLargeUnusualSequenceLength("aaa", "aaa"));
        System.out.println(findLargeUnusualSequenceLength("aefawfawfawfaw", "aefawfeawfwafwaef"));
    }

    /**
     * 本题题意难于理解
     * 独有指的是只有自己有，另一个字符串没有
     * 举例说明，设两个字符串变量名分别为 a 和 b
     * a = 'c', b = 'cd'，'cd' 是 a 独有的，所以最长子序列为 'cd'，长度为 2
     * a = 'cd', b = 'cd', 'cd', 'c', 'd' 在两个字符串中都有，所以不存在独有的最长子序列，返回 -1
     * 通过举例分析，得出以下结论：
     * 如果两个字符串长度不一样，则较长的字符串本身不可能是短字符串的子序列，直接返回其长度即可
     * 如果两个字符串内容相等，那么他们独有的最长子序列不存在，返回 -1
     * WTF!
     * @param a a
     * @param b b
     * @return LUS
     */
    public static int findLargeUnusualSequenceLength(String a, String b) {
        if (a.equals(b)) {
            return -1;
        }
        return Math.max(a.length(), b.length());
    }
}
