package com.example.javabasic.enumration.multichanneldistribution;

import static com.example.javabasic.enumration.multichanneldistribution.Outcome.*;

/**
 * @author：Cheng.
 * @date：Created in 15:51 2019/9/17
 */
public enum RoShamBo6 implements Competitor<RoShamBo6> {

    //使用二维数组实现多路分发
    PAPER,SCISSORS,ROCK;

    private static Outcome[][] table = {
            {DRAW,LOSE,WIN},
            {WIN,DRAW,LOSE},
            {LOSE,WIN,DRAW},

    };

    @Override
    public Outcome compete(RoShamBo6 other){
        return table[this.ordinal()][other.ordinal()];
    }

    public static void main(String[] args){
        RoShamBo.play(RoShamBo6.class,20);
    }


    }
