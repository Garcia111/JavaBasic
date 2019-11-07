package com.example.javabasic.java8.method_reference;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

public class MethodReferenceTest {

    @Test
    public static void main() {
        Person[] pArr = new Person[] {
                new Person("003", LocalDate.of(2016,9,1)),
                new Person("001", LocalDate.of(2016,2,1)),
                new Person("002", LocalDate.of(2016,3,1)),
                new Person("004", LocalDate.of(2016,12,1))
        };

        // 使用匿名类
        Arrays.sort(pArr, new Comparator<Person>() {
            @Override
            public int compare(Person a, Person b) {
                return a.getBirthday().compareTo(b.getBirthday());
            }
        });

        //使用lambda表达式
        Arrays.sort(pArr, (Person a, Person b) -> {
            return a.getBirthday().compareTo(b.getBirthday());
        });

        //使用方法引用，引用的是类的静态方法
        Arrays.sort(pArr, Person::compareByAge);
    }
}
