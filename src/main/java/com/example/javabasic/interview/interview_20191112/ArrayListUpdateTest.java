package com.example.javabasic.interview.interview_20191112;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListUpdateTest {

    public static void main(String[] args) {
        List<String> arrayList1 = new ArrayList<String>();
        arrayList1.add("1");
        arrayList1.add("2");
        for (String s : arrayList1) {
            if ("1".equals(s)) {
                arrayList1.remove(s);
            }
        }


        List<String> arrayList2 = new ArrayList<String>();
        arrayList2.add("2");
        arrayList2.add("1");
        for (String s : arrayList2) {
            if ("1".equals(s)) {
                arrayList2.remove(s);
            }
        }
    }


    @Test
    public void testIterator(){
        List<String> arrayList2 = new ArrayList<String>();
        arrayList2.add("2");
        arrayList2.add("1");
        Iterator<String> strIt = arrayList2.iterator();
        while(strIt.hasNext()){
            String s = strIt.next();
            if("1".equals(s)){
                strIt.remove();
            }
        }
    }
}
