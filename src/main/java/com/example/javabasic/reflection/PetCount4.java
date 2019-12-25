package com.example.javabasic.reflection;

import org.hibernate.boot.registry.StandardServiceRegistry;

/**
 * @author：Cheng.
 * @date：Created in 14:07 2019/12/23
 */
public class PetCount4 {
    public static void main(String[] args) {
        TypeCounter counter = new TypeCounter(Pet.class);
        for(Pet pet: new LiteralPetCreator().createArray(20)){
            System.out.println(pet.getClass().getSimpleName()+" ");
            counter.count(pet);
        }
        System.out.println(counter);
    }

}
