package com.example.javabasic.reflection;

import java.util.List;
import java.util.Random;

public abstract class PetCreator {

    private Random rand = new Random(47);

    public abstract List<Class<? extends Pet>> types();

    public Pet randomPet(){
        int n = rand.nextInt(types().size());

        try{
            return types().get(n).newInstance();
        } catch (IllegalAccessException e) {
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

}
