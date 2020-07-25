package com.yy.array;

import java.util.HashMap;
import java.util.Map;

public class CountCharacters {

    public static void main(String[] args) {
        String[] word = new String[]{"cat","bt","hat","tree"};
        String chars = "atach";
        System.out.println(countCharacters(word, chars));

        String[] word2 = new String[]{"hello", "world", "leetcode"};
        String chars2 = "welldonehoneyr";
        System.out.println(countCharacters(word2, chars2));

    }

    /**
     *考虑用int[]数组
     */
    public static int countCharacters(String[] words, String chars) {
        //遍历单词 只要单词对应的字母在chars数组里能找到，且字母的重复次数不大于chars数字中字母的次数 即单词为掌握的
        Map<Character, Integer> map = new HashMap<>();
        for (char i : chars.toCharArray()) {
            if (map.get(i) == null) {
                map.put(i, 1);
            } else {
                map.put(i, map.get(i) + 1);
            }
        }
        //map怎么做到复用？ 统计每个对应char的出现次数，然后和map的数据进行比较？
        int result = 0;
        for (String str : words) {
            if (str.length() == 0) {
                continue;
            }
            Map<Character, Integer> tempMap = new HashMap<>();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (tempMap.get(c) == null) {
                    tempMap.put(c, 1);
                } else {
                    tempMap.put(c, tempMap.get(c) + 1);
                }
            }
            int correctCount = 0;
            for (Map.Entry<Character, Integer> entry : tempMap.entrySet()) {
                Character c = entry.getKey();
                int count = entry.getValue();
                if (null != map.get(c) && count - map.get(c) <= 0) {
                    correctCount++;
                }
            }
            //这里注意 正确的字符数量-> tempMap.size() 不一定等于str.length()
            if (correctCount == tempMap.size()) {
                result += str.length();
            }

        }
        return result;
    }

    /**
     *优化版 跟之前的桶排序有点相似之处
     */
    public int countCharacters2(String[] words, String chars) {
        int[] c = new int[26];
        for(char cc : chars.toCharArray()) {
            c[(int)(cc - 'a')] += 1;
        }
        int res = 0;
        a: for(String word : words) {
            int[] w = new int[26];
            for(char ww : word.toCharArray()) {
                w[(int)(ww - 'a')] += 1;
            }
            for(int i=0; i<26; i++) {
                if(w[i] > c[i]) {
                    continue a;
                }
            }
            res += word.length();
        }
        return res;
    }

}
