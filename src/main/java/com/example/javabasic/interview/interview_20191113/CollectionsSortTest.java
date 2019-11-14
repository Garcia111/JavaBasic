package com.example.javabasic.interview.interview_20191113;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionsSortTest {

    @Test
    public void testSort1() {

        List<Weather> weatherList = Lists.newArrayList(new Weather(1.0, 3),
                new Weather(32.4, 1),
                new Weather(25.6, 4));
        System.out.println("原list:" + weatherList);
        Collections.sort(weatherList);
        System.out.println("sorted1 list:" + weatherList);


    }


    @Test
    public void testSort2() {
        List<Weather> weatherList = Lists.newArrayList(new Weather(1.0, 3),
                new Weather(32.4, 1),
                new Weather(25.6, 4));
        System.out.println("原list:" + weatherList);
        Collections.sort(weatherList, new WeatherComparator());
        System.out.println("sorted2 list:" + weatherList);
    }


    @Test
    public void testSort3() {
        List<Weather> weatherList = Lists.newArrayList(new Weather(1.0, 3),
                new Weather(32.4, 1),
                new Weather(25.6, 4));
        System.out.println("原list:" + weatherList);
        List<Weather> sortedList = weatherList.stream()
                .sorted(Comparator.comparingDouble(Weather::getTemperature))
                .collect(Collectors.toList());
        System.out.println("sorted3 list1:" + sortedList);
        List<Weather> sortedList2 = weatherList.stream()
                .sorted(Comparator.comparing(Weather::getTemperature))
                .collect(Collectors.toList());
        System.out.println("sorted3 list2:" + sortedList);
    }
}
