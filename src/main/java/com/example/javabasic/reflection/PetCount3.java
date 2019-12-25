package com.example.javabasic.reflection;

import org.apache.catalina.mapper.MappingData;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author：Cheng.
 * @date：Created in 10:47 2019/12/23
 */
public class PetCount3 {

    static class PetCounter extends LinkedHashMap<Class<? extends Pet>,Integer> {
        LinkedHashMap<Class<? extends Pet>,Integer> map = new LinkedHashMap<Class<? extends Pet>,Integer>();

        public PetCounter(){
            LiteralPetCreator.allTypes.forEach(type->map.put(type,0));
        }


        public void count(Pet pet){
            for(Map.Entry<Class<? extends  Pet>,Integer> pair :map.entrySet()){

                //isInstance 的作用等价于instanceof
                if(pair.getKey().isInstance(pet)){
                    put(pair.getKey(),pair.getValue()+1);
                }
            }
        }

        @Override
        public String toString(){
            StringBuilder result = new StringBuilder("{");
            for(Map.Entry<Class<? extends Pet>,Integer> pair : map.entrySet()){
                result.append(pair.getKey().getSimpleName());
                result.append("=");
                result.append(pair.getValue());
                result.append(",");
            }
            result.delete(result.length()-2,result.length());
            result.append("}");
            return result.toString();
        }
    }

    public static void main(String[] args) {
        PetCounter petCount = new PetCounter();
        for(Pet pet: new LiteralPetCreator().createArray(20)){
            System.out.println(pet.getClass().getSimpleName()+" ");
            petCount.count(pet);
        }
        System.out.println(petCount);
    }


}
