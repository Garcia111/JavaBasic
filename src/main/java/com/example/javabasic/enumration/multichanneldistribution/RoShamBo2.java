package com.example.javabasic.enumration.multichanneldistribution;

import static com.example.javabasic.enumration.multichanneldistribution.Outcome.*;
/**
 * @author：Cheng.
 * @date：Created in 10:22 2019/9/17
 */
public enum RoShamBo2 implements Competitor<RoShamBo2> {

    //使用enum实现多路分发


    PAPER(DRAW, LOSE, WIN),
    SCISSORS(WIN, DRAW, LOSE),
    ROCK(LOSE, WIN, DRAW);

    private Outcome vPAPER, vSCISSORS, vROCK;

    RoShamBo2 (Outcome paper, Outcome scissors, Outcome rock){
        this.vPAPER = paper;
        this.vSCISSORS = scissors;
        this.vROCK = rock;
    }


    @Override
    public Outcome compete(RoShamBo2 it){
        switch (it){
            default:
            case PAPER: return vPAPER;
            case SCISSORS: return vSCISSORS;
            case ROCK: return vROCK;
        }
    }


    public static void main(String[] args){
        RoShamBo.play(RoShamBo2.class,20);
    }

   }



   interface Competitor<T extends Competitor<T>>{
       public Outcome compete(T it);
   }