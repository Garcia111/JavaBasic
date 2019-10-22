package com.example.javabasic.reflection;

/**
 * @author：Cheng.
 * @date：Created in 17:26 2019/10/22
 */
interface HasBatteries{}
interface Waterproof {}
interface Shoots{}


class Toy{
    Toy(){}
    Toy(int i){}
}

class FancyToy extends Toy implements HasBatteries,Waterproof,Shoots{
    FancyToy(){super(1);}
}


public class ToyTest {

    static void printInfo(Class cc){
        System.out.println("Class name: "+cc.getName()+
                "is interface? ["+cc.isInterface() +"]");
        System.out.println("Simple name: "+cc.getSimpleName());
        System.out.println("Canonical name :"+cc.getCanonicalName());
    }

    public static void main(String[] args){
        Class c = null;
        try{
            c = Class.forName("com.example.javabasic.reflection.FancyToy");
        }catch (ClassNotFoundException e){
            System.out.println("Can not find FancyToy");
            System.exit(1);
        }

        System.out.println(c);
        //获取类的接口
        for(Class face : c.getInterfaces()){
            System.out.println(face);
            //获取类的父类
            Class up = c.getSuperclass();
            Object obj = null;

            try{
                //通过Class对象，创建类的实例
                obj = up.newInstance();
            }catch (InstantiationException e){
                System.out.println("Can not instantiate");
                System.exit(1);
            }catch (IllegalAccessException e){
                System.out.println("Can not access");
                System.exit(1);
            }
            printInfo(obj.getClass());
        }
    }
}
