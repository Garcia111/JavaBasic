package com.example.javabasic.designpattern.ObserverPattern;

import com.sun.deploy.net.proxy.WIExplorerAutoProxyHandler;

/**
 * @author：Cheng.
 * @date：Created in 16:55 2019/11/6
 */
public class WeatherStation {

    public static void main(String[] args){
        WeatherData weatherData = new WeatherData();

        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);

        weatherData.setMeasurements(80,65,30.4f);
        weatherData.setMeasurements(82,70,29.2f);
        weatherData.setMeasurements(78,90,29.2f);
    }
}
