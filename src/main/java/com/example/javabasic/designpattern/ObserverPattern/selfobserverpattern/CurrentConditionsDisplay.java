package com.example.javabasic.designpattern.ObserverPattern.selfobserverpattern;

/**
 * @author：Cheng.
 * @date：Created in 11:39 2019/11/6
 */
public class CurrentConditionsDisplay implements DisplayElement,Observer {

    /**订阅者需要持有一个主题的引用*/
    private Subject weatherData;

    public CurrentConditionsDisplay(Subject weatherData){
        this.weatherData = weatherData;
        //向weatherData 主题进行订阅
        weatherData.registerObserver(this);
    }


    @Override
    public void display() {
        System.out.println("Current conditions:"+weatherData);
    }

    @Override
    public void update(WeatherData data) {
        this.weatherData = data;
        display();
    }
}
