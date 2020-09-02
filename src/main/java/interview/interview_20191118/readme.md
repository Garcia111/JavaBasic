1.如何将Array转ArrayList
    
    当需要将Array转ArrayList的时候，如果使用下面这个方法：
    List<String> list = Arrays.asList(arr);
    Arrays.asList()会返回一个ArrayList，但是这个ArrayList是Arrays类的静态内部类，并不是java.util.ArrayList类。
    
    java.util.Arrays.ArrayList类实现了set() get() contains()方法，但是并没有实现增加元素的方法，
    如果你调用add方法，则会抛出UnsupportedOperationException异常，因此它的大小也是固定不变的，为了
    创建一个真正的java.util.ArrayList,应该使用下面的方法：
    
    ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(arr));
    
    ArrayList的构造方法可以接收一个Collection类型，而java.util.Arrays.ArrayList已经实现了该接口。
    
    
    
    
    
    
    
    
    
    