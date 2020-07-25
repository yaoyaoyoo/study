package com.yy.review.basics;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HashMapCase {
    //文档：HashMap.note
    //链接：http://note.youdao.com/noteshare?id=84aa23fdd221c415b2465fb0949038f0&sub=7BCBE2E39DC94062A1E802FE18C767D0
    public static void main(String[] args) {
        String value = "value";
        Map<Person, String> map = new HashMap<>();
        Person p1 = new Person("zhangsan", 21);
        Person p2 = new Person("lisi", 21);
        Person p3 = new Person("wangwu", 21);
        Person p4 = new Person("yy", 21);
        Person p5 = new Person("1", 21);
        Person p6 = new Person("2", 21);
        Person p7 = new Person("3", 21);
        Person p8 = new Person("4", 21);
        map.put(p1, value);
        map.put(p2, value);
        map.put(p3, value);
        map.put(p4, value);
        map.put(p5, value);
        map.put(p6, value);
        map.put(p7, value);
        map.put(p8, value);
    }

    /**
     * test class : Simulate hash conflict 模拟哈希冲突
     */
    static class Person {
        private String name;
        private int age;

        Person(String name, int age) {
         this.name = name;
         this.age = age;
        };

        @Override public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Person person = (Person) o;
            return age == person.age && Objects.equals(name, person.name);
        }

        @Override public int hashCode() {
            return Objects.hash(age);
        }

        @Override public String toString() {
            return "Person{" + "name='" + name + '\'' + '}';
        }
    }
}
