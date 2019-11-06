package com.example.javabasic.designpattern.ObserverPattern;

/**
 * @author：Cheng.
 * @date：Created in 17:03 2019/11/6
 */
public class StatisticsDisplay implements Observer, DisplayElement {

    private WeatherData weatherData;

    private Float statisticsTempreature;
    private Float statisticsHumidity;
    private Float statisticsPressure;

    public StatisticsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }


    @Override
    public void display() {
        System.out.println("Statistics condition, statisticsTempreature:" + statisticsTempreature +
                             ", statisticsHumidity:" + statisticsHumidity +
                                ", statisticsPressure:" + statisticsPressure);
    }

    @Override
    public void update(WeatherData data) {
        this.weatherData = weatherData;
        this.statisticsTempreature = (this.statisticsTempreature == null ? weatherData.getTemperature() :
                (weatherData.getTemperature() + this.statisticsTempreature) / 2);
        this.statisticsHumidity = (this.statisticsHumidity == null ? weatherData.getHumidity() :
                (weatherData.getHumidity() + this.statisticsHumidity) / 2);
        this.statisticsPressure = (this.statisticsPressure == null ? weatherData.getPressure() :
                (weatherData.getPressure() + this.statisticsPressure) / 2);
        display();
    }
}
