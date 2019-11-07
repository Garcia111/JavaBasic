package com.example.javabasic.designpattern.ObserverPattern.javaobserverpattern;


import com.example.javabasic.designpattern.ObserverPattern.selfobserverpattern.Observer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Observable;

/**
 * @author：Cheng.
 * @date：Created in 8:51 2019/11/7
 */
@Data
@EqualsAndHashCode
@ToString
public class WeatherData2 extends Observable {


    private float temperature;
    private float humidity;
    private float pressure;


    public void setMeasurements(float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        setChanged();
        notifyObservers();
    }
}
