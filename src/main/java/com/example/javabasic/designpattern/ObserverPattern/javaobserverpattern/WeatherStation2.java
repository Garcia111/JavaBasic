package com.example.javabasic.designpattern.ObserverPattern.javaobserverpattern;

import com.example.javabasic.designpattern.ObserverPattern.selfobserverpattern.CurrentConditionsDisplay;
import com.example.javabasic.designpattern.ObserverPattern.selfobserverpattern.StatisticsDisplay;
import com.example.javabasic.designpattern.ObserverPattern.selfobserverpattern.WeatherData;

/**
 * @author：Cheng.
 * @date：Created in 16:55 2019/11/6
 */
public class WeatherStation2 {

    public static void main(String[] args){
        WeatherData2 weatherData = new WeatherData2();

        CurrentConditionsDisplay2 currentDisplay = new CurrentConditionsDisplay2(weatherData);

        weatherData.setMeasurements(80,65,30.4f);
        weatherData.setMeasurements(82,70,29.2f);
        weatherData.setMeasurements(78,90,29.2f);
    }
}
