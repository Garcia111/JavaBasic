package com.example.javabasic.enumration.multichanneldistribution;

import java.util.EnumMap;

import static com.example.javabasic.enumration.multichanneldistribution.Outcome.*;

/**
 * @author：Cheng.
 * @date：Created in 15:20 2019/9/17
 */
public enum RoShamBo5 implements Competitor<RoShamBo5>{
    //使用EnumMap实现两路分发、EnumMap是为enum专门设计的一种性能非常好的特殊Map
    //由于我们的目的是探索出两种位置的类型，所以可以使用一个EnumMap的EnumMap来实现两路分发。
    PAPER, SCISSORS, ROCK;

    static EnumMap<RoShamBo5, EnumMap<RoShamBo5,Outcome>> table =
            new EnumMap<RoShamBo5, EnumMap<RoShamBo5, Outcome>>(RoShamBo5.class);


    static{
        for(RoShamBo5 item :RoShamBo5.values()){
            table.put(item,new EnumMap<RoShamBo5, Outcome>(RoShamBo5.class));
        }
        initTable(PAPER,DRAW,WIN,LOSE);
        initTable(SCISSORS,WIN,DRAW,LOSE);
        initTable(ROCK,LOSE,WIN,DRAW);
    }


    public static void initTable(RoShamBo5 item,Outcome vPAPER,Outcome vSCISSORS,Outcome vROCK){
        table.get(item).put(PAPER,vPAPER);
        table.get(item).put(SCISSORS,vSCISSORS);
        table.get(item).put(ROCK,vROCK);
    }



    public static void main(String[] args){
        RoShamBo.play(RoShamBo5.class,20);
    }

    @Override
    public Outcome compete(RoShamBo5 it) {
        return table.get(this).get(it);
    }}
