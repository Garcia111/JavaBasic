package com.example.javabasic.interview.interview_20191113;

import java.util.Comparator;

public class WeatherComparator implements Comparator<Weather> {
    @Override
    public int compare(Weather o1, Weather o2) {
        if(o1.getTemperature() >o2.getTemperature()){
            return 1;
        }
        return -1;
    }
}
