package com.example.javabasic.reflection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author：Cheng.
 * @date：Created in 9:39 2019/12/23
 */
public abstract class PetCreator {

    private Random rand = new Random(47);

    public abstract List<Class<?extends Pet>> types();


    public Pet randomPet(){
        int n = rand.nextInt(types().size());

        try{
            return types().get(n).newInstance();
        } catch (IllegalAccessException e) {
            //使用newInstance方法创建对象的时候，如果默认构造器为private，则会报出此异常
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }



    public Pet[] createArray(int size){
        Pet[] result = new Pet[size];
        for(int i=0; i<size; i++){
            result[i] = randomPet();
        }
        return result;
    }


    public ArrayList<Pet> arrayList(int size){
        ArrayList<Pet> result = new ArrayList<Pet>();
        Collections.addAll(result,createArray(size));
        return result;
    }
}


class ForNameCreator extends PetCreator{

    private static List<Class<? extends Pet>> types = new ArrayList<Class<? extends Pet>>();

    private static String[] typeNames = {
            "com.example.javabasic.reflection.Mutt",
            "com.example.javabasic.reflection.Pug",
            "com.example.javabasic.reflection.EgyptianMau",
            "com.example.javabasic.reflection.Manx",
            "com.example.javabasic.reflection.Cymric",
            "com.example.javabasic.reflection.Rat",
            "com.example.javabasic.reflection.Mouse",
            "com.example.javabasic.reflection.Hamster"
    };

    @SuppressWarnings("unchecked")
    private static void loader(){
        try{
            for(String name: typeNames){
                types.add((Class<? extends Pet>)Class.forName(name));
            }
        }catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    static {
        loader();
    }
    @Override
    public List<Class<? extends Pet>> types() {
        return types;
    }
}


























