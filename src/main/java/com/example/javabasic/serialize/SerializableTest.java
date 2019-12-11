package com.example.javabasic.serialize;

import java.io.*;

/**
 * @author：Cheng.
 * @date：Created in 9:23 2019/12/11
 */
public class SerializableTest {


    public static void main(String[] args){
//        writeSerializableObject();
//        readSerializableObject();
        writeExternalizableObject();
        readExternalizableObject();
    }


    // Serializable：把对象序列化
    public static void writeSerializableObject() {
        try {
            Man man = new Man("huhx", "123456");
            Person person = new Person(man, "刘力", 21);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("output.txt"));
            objectOutputStream.writeObject("string");
            objectOutputStream.writeObject(person);
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Serializable：反序列化对象
    public static void readSerializableObject() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("output.txt"));
            String string = (String) objectInputStream.readObject();
            Person person = (Person) objectInputStream.readObject();
            objectInputStream.close();
            System.out.println(string + ", age: " + person.getAge() + ", man username: " + person.getMan().getUsername());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Externalizable的序列化对象
    public static void writeExternalizableObject() {
        User user = new User("huhx", 22);
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Externalizable.txt"));
            objectOutputStream.writeObject(user);
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Externalizable的反序列化对象
    public static void readExternalizableObject() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Externalizable.txt"));
            User user = (User) objectInputStream.readObject();
            objectInputStream.close();
            System.out.println("name: " + user.getUser() + ", age: " + user.getAge());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
