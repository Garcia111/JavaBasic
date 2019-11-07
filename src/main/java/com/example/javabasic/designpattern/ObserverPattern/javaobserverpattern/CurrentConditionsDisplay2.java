package com.example.javabasic.designpattern.ObserverPattern.javaobserverpattern;

import com.example.javabasic.designpattern.ObserverPattern.selfobserverpattern.DisplayElement;
import java.util.Observable;
import java.util.Observer;

/**
 * @author：Cheng.
 * @date：Created in 11:39 2019/11/6
 */
public class CurrentConditionsDisplay2 implements DisplayElement, Observer {

    /**订阅者需要持有一个主题的引用*/
    private Observable weatherData;

    public CurrentConditionsDisplay2(Observable weatherData){
        this.weatherData = weatherData;
        //向weatherData 主题进行订阅
        weatherData.addObserver(this);
    }


    @Override
    public void display() {
        System.out.println("Current conditions:"+weatherData);
    }



    @Override
    public void update(Observable o, Object arg) {
        this.weatherData = (WeatherData2) o;
            display();
        }
}
