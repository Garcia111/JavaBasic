package com.example.javabasic.inner_class.extend_inner_class;

/**
 * @author：Cheng.
 * @date：Created in 16:48 2020/1/8
 */
class WithInner{
    //内部类的构造器必须连接到指向其外围类对象的引用
    class Inner{}
}

public class InheritInner extends WithInner.Inner{

    InheritInner(WithInner wi){
        //继承内部类的时候，指向外围类对象的秘密引用必须被初始化
        //必须使用此语法 enclosingClassReference.super();才能提供指向外围类对象的引用
        wi.super();
    }

    public static void main(String[] args) {
        WithInner wi = new WithInner();
        InheritInner ii = new InheritInner(wi);
    }
}









