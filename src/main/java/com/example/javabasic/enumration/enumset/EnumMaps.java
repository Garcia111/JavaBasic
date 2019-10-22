package com.example.javabasic.enumration.enumset;

import java.util.EnumMap;
import java.util.Map;

import static com.example.javabasic.enumration.enumset.AlarmPoints.*;


/**
 * @author：Cheng.
 * @date：Created in 15:59 2019/9/16
 */
public class EnumMaps {

    public static void main(String[] args){
        EnumMap<AlarmPoints,Command> em = new EnumMap<AlarmPoints, Command>(AlarmPoints.class);
        em.put(KITCHEN, new Command() {
            @Override
            public void action() {
                System.out.println("Kitchen fire!");
            }
        });
        em.put(BATHROOM, new Command() {
            @Override
            public void action() {
                System.out.println("Bathroom fire!");
            }
        });

        System.out.println(em.size());

        for(Map.Entry<AlarmPoints, Command> e: em.entrySet()){
            System.out.println(e.getKey()+": ");
            e.getValue().action();
        }
    }
}

interface Command{
    void action();
}
