package com.example.javabasic.enumration.constantenum;



/**
 * @author：Cheng.
 * @date：Created in 17:54 2019/9/16
 */
public enum OverrideConstantSpecific {

    NUT, BOLT,
    WASHER{
        @Override
        void f(){System.out.println("Overridden method");}
    };

    void f(){System.out.println("default method");}

    public static void main(String[] args){
        for(OverrideConstantSpecific ocs:values()){
            System.out.println(ocs+":");
            ocs.f();
        }
    }

}
