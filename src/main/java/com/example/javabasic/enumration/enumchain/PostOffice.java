package com.example.javabasic.enumration.enumchain;

import com.example.javabasic.enumration.Enums;
import com.example.javabasic.enumration.enumclassify.Meal2;

import java.util.Iterator;

/**
 * @author：Cheng.
 * @date：Created in 18:09 2019/9/16
 */
//职责链设计模式：程序员以多种不同的方式来解决一个问题，然后将它们链接在一起。当一个请求到来时，它遍历这个链，直到链中的某个解决防范能够处理该请求。


class Mail{
    enum GeneralDelivery{YES,NO1,NO2,NO3,NO4,NO5}
    enum Scannability{UNSCANNABLE,YES1,YES2,YES3,YES4}
    enum Readability {ILLEGIBLE,YES1,YES2,YES3,YES4}
    enum Address {INCORRECT,OK1,OK2,OK3,OK4,OK5,OK6}
    enum ReturnAddress {MISSING,OK1,OK2,OK3,OK4,OK5}
    GeneralDelivery generalDelivery;
    Scannability scannability;
    Readability readability;
    Address address;
    ReturnAddress returnAddress;
    static long counter = 0;
    long id  =  counter++;


    @Override
    public String toString(){
        return "Mail"+id;
    }


    public String details(){
        return  toString() +
                ", General Delivery:"+generalDelivery +
                ", Address Scanability:"+scannability +
                ", Address Readability:"+readability +
                ", Address Address: "+address +
                ", Return Address: "+returnAddress;
    }

    public static Mail randomMail(){
        Mail m = new Mail();
        m.generalDelivery = Enums.random(GeneralDelivery.class);
        m.scannability = Enums.random(Scannability.class);
        m.readability = Enums.random(Readability.class);
        m.address = Enums.random(Address.class);
        m.returnAddress = Enums.random(ReturnAddress.class);
        return m;
    }

    public static Iterable<Mail> generator(final int count){
        return new Iterable<Mail>(){
            int n = count;

            @Override
            public Iterator<Mail> iterator() {
                return new Iterator<Mail>() {
                    @Override
                    public boolean hasNext() {
                        return n-- >0;
                    }

                    @Override
                    public Mail next() {
                        return randomMail();
                    }

                    @Override
                    public void remove(){
                        throw new UnsupportedOperationException();
                    }
                };
            }


        };
    }

}

public class PostOffice {
    enum MailHandler{

    }
}
