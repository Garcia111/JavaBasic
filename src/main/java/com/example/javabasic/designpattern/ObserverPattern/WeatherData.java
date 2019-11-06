package com.example.javabasic.designpattern.ObserverPattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

/**
 * @author：Cheng.
 * @date：Created in 11:33 2019/11/6
 */
@ToString
@AllArgsConstructor
@Data
public class WeatherData implements Subject{
    /**一堆订阅者的引用*/
    private ArrayList<Observer>  observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData(float temperature,float humidity,float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public WeatherData(){
        observers = new ArrayList();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if(i>0){
            observers.remove(i);
        }
    }

    @Override
    public void notifyObservers() {
        observers.stream().forEach(o -> o.update(new WeatherData(temperature, humidity,pressure)));
    }

    public void measurementsChanged(){
        notifyObservers();
    }


    public void setMeasurements(float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

}
