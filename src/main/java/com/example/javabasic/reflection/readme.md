反射

    1.类字面常量
    简单明了地说，类字面常量指的是 类名.class,这样获取Class引用的当时更加安全，因为它在编译时就会受到检查。
    对于基本数据类型的包装类，还有一个标准字段TYPE，TYPE字段是一个引用，指向对应的基本数据类型的Class对象。
    boolean.class == Boolean.TYPE
    char.class == Character.TYPE
    byte.class == Byte.TYPE
    short.class == Short.TYPE
    int.class == Integer.TYPE
    long.class == Long.TYPE
    float.class == Float.TYPE
    double.class == Double.TYPE
    void.class == Void.TYPE
    
    Initable.java
    仅仅使用.class语法来获得对类的引用不会引发初始化。
    但是，为了产生Class应用，Class.forName()立即就进行了初始化.
    如果一个static final值是“编译期常量”，就像Initable.staticFinal那样，那么这个值不需要对Initable类进行初始化就可以被读取
    如果仅仅是将一个域设置为static和final的，还不能确保不初始化类就可以被读取。
    例如，对Initable.staticFinal2d的访问将强制进行类的初始化，因为它不是一个编译期常量。

    如果一个static域不是final的，那么在对它访问时，总是要求在它被读取之前，要先进行链接（为这个域分配存储空间）和初始化（初始化该存储空间）