package com.example.javabasic.enumration.multichanneldistribution;

import java.util.Random;

import static com.example.javabasic.enumration.multichanneldistribution.Outcome.*;

/**
 * @author：Cheng.
 * @date：Created in 9:34 2019/9/17
 */


class Paper implements Item{

    @Override
    public Outcome compete(Item it){
        return it.eval(this);
    }

    @Override
    public Outcome eval(Paper p){
        return DRAW;
    }

    @Override
    public Outcome eval(Scissors s){
        return WIN;
    }

    @Override
    public Outcome eval(Rock r){
        return LOSE;
    }

    @Override
    public String toString(){
        return "Paper";
    }
}


class Scissors implements Item{
    @Override
    public Outcome compete(Item it){
        return it.eval(this);
        }

    @Override
    public Outcome eval(Paper p) {
        return LOSE;
    }

    @Override
    public Outcome eval(Scissors s) {
        return DRAW;
    }

    @Override
    public Outcome eval(Rock r) {
        return WIN;
    }

    @Override
    public String toString(){
        return "Scissors";
    }
}


class Rock implements Item{

    @Override
    public Outcome compete(Item it) {
        return it.eval(this);
    }

    @Override
    public Outcome eval(Paper p) {
        return WIN;
    }

    @Override
    public Outcome eval(Scissors s) {
        return LOSE;
    }

    @Override
    public Outcome eval(Rock r) {
        return DRAW;
    }

    @Override
    public String toString(){
        return "Rock";
    }
}




public class RoShamBo1{
    static final int SIZE = 20;
    private static Random rand = new Random(47);
    public static Item newItem(){
        switch (rand.nextInt(3)){
            default:
            case 0: return new Scissors();
            case 1: return new Paper();
            case 2: return new Rock();
        }
    }

    public static void match(Item a, Item b){
        System.out.println(a +" vs. "+ b +":"+ a.compete(b));
    }

    public static void main(String[] args){
        for(int i=0; i<SIZE; i++){
            match(newItem(),newItem());
        }
    }
}

