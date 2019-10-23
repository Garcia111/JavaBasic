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
        //getName()------完全限定名
        System.out.println("Class name: "+cc.getName()+
                "is interface? ["+cc.isInterface() +"]");

        System.out.println("Simple name: "+cc.getSimpleName());
        //getCanonicalName()-------获取完全限定类名
        System.out.println("Canonical name :"+cc.getCanonicalName());

    }


    //写一个方法，令它接受任意对象作为参数，并能够递归打印出该对象所在的继承体系中的所有类。
    public static void getSuperClass(Object object) throws IllegalAccessException, InstantiationException {
        Class c = object.getClass();
        Class superClass = c.getSuperclass();
        if(superClass != null){
            System.out.println(superClass.getName());
            getSuperClass(superClass.newInstance());
        }
    }

    public static void main(String[] args){
        Class c = null;
        try{
            c = Class.forName("com.example.javabasic.reflection.FancyToy");
        }catch (ClassNotFoundException e){
            System.out.println("Can not find FancyToy");
            System.exit(1);
        }
        try {
            getSuperClass(c.newInstance());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        System.out.println(c);
        //获取类的接口  getInterfaces()获取的是Class对象
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
