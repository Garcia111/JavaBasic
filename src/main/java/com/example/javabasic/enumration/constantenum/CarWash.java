package com.example.javabasic.enumration.constantenum;

import java.util.EnumSet;

/**
 * @author：Cheng.
 * @date：Created in 17:34 2019/9/16
 */
public class CarWash {

    public enum Cycle{
       UNDERBODY {
           @Override
           void action(){
               System.out.println("Spraying the underbody");
           }
       } ,
        WHEELWASH{
           @Override
           void action(){
               System.out.println("Washing the wheels");
           }
        },

        PREWASH{
           @Override
           void action(){
               System.out.println("Loosing the dirt");
           }
        },
        BASIC{
           @Override
           void action(){
               System.out.println("The basic wash");
           }
        },

        HOTWAX {
            @Override
            void action() {
                System.out.println("Applying hot wax");
            }
        },

        RINSE{
            @Override
            void action() {
                System.out.println("Rinsing");
            }
        },
        BLOWDRY{
            @Override
            void action() {
                System.out.println("Blowing dry");
            }
        };

       abstract void action();
    }

    EnumSet<Cycle> cycles =  EnumSet.of(Cycle.BASIC, Cycle.RINSE);
    public void add(Cycle cycle){
        cycles.add(cycle);
    }

    public void washCar(){
        for(Cycle c: cycles)
            c.action();
    }

    @Override
    public String toString(){return cycles.toString();}

    public static void main(String[] args){
        CarWash wash = new CarWash();
        System.out.println(wash);
    }


}
