package com.example.javabasic.util.beancopier;

import com.example.javabasic.dto.Department;
import com.google.common.base.Stopwatch;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

/**
 * @author：Cheng.
 * @date：Created in 20:59 2019/9/8
 */
public class BeanCopierTest {

    public static void testCglibBeanCopier(Department origin, Department destination){
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println();
        System.out.println("================cglib BeanCopier执行" + 1 + "次================");

        BeanCopier copier = BeanCopier.create(Department.class, Department.class, false);
        copier.copy(origin, destination, null);

        stopwatch.stop();

        System.out.println("testCglibBeanCopier 耗时: " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }


    public static void main(String[] args){

        OneDif oneDif = new OneDif();

        //复制过程中忽略empty集合的复制
        difCopyConvert();
    }



    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    // 属性名称、类型都相同，成功复制
    public static void sameCopy() {
        One one = new One();
        one.setId(1);
        one.setName("one");
        final BeanCopier copier = BeanCopier.create(One.class, Two.class, false);
        Two two = new Two();
        copier.copy(one, two, null);
        System.out.println(one.toString());
        System.out.println(two.toString());
    }

    // 属性名称相同,类型不同，类型不同的不能复制
    public static void difCopy() {
        One one = new One();
        one.setId(1);
        one.setName("one");
        final BeanCopier copier = BeanCopier.create(One.class, OneDif.class, false);
        OneDif oneDif = new OneDif();
        copier.copy(one, oneDif, null);
        System.out.println(one.toString());
        System.out.println(oneDif.toString());
    }

    // 属性名称相同,类型不同的解决方法，使用convert，注意要写上类型相同的情况
    public static void difCopyConvert() {
        Stopwatch stop = Stopwatch.createStarted();
        One one = new One();
        one.setId(1);
        one.setName("one");
        final BeanCopier copier = BeanCopier.create(One.class, OneDif.class, true);
        OneDif oneDif = new OneDif();
        copier.copy(one, oneDif, new Converter() {
            @Override
            public Object convert(Object value, Class target, Object context) {
                if (value instanceof Integer) {
                    return (Integer) value;
                } else if (value instanceof Timestamp) {
                    Timestamp date = (Timestamp) value;
                    return sdf.format(date);
                } else if (value instanceof BigDecimal) {
                    BigDecimal bd = (BigDecimal) value;
                    return bd.toPlainString();
                } else if (value instanceof String) {
                    return "" + value;
                }
                return null;
            }
        });
        System.out.println(one.toString());
        System.out.println(oneDif.toString());
        stop.stop();
        System.out.println("stop watch:"+stop.elapsed(TimeUnit.MILLISECONDS));
    }

    // 目标无setter，不能复制，注意此处，网上说会报错，但是新版本并无此报错
    public static void noSetterCopy() {
        One one = new One();
        one.setId(1);
        one.setName("one");
        final BeanCopier copier = BeanCopier.create(One.class, OneNoSetter.class, false);
        OneNoSetter oneNoSetter = new OneNoSetter();
        copier.copy(one, oneNoSetter, null);
        System.out.println(one.toString());
        System.out.println(oneNoSetter.toString());
    }

    // 源无setter，都不能赋值了好吧？
    public static void noSetterCopy2() {

    }

    // 目标少setter，少setter的没值
    public static void lessSetterCopy() {
        One one = new One();
        one.setId(1);
        one.setName("one");
        final BeanCopier copier = BeanCopier.create(One.class, OneLessSetter.class, false);
        OneLessSetter oneLessSetter = new OneLessSetter();
        copier.copy(one, oneLessSetter, null);
        System.out.println(one.toString());
        System.out.println(oneLessSetter.toString());
    }

    // 源少setter，只有有setter的可以复制
    public static void lessSetterCopy2() {
        OneLessSetter oneLessSetter = new OneLessSetter();
//        oneLessSetter.setId(1);
        oneLessSetter.setName("one");
        final BeanCopier copier = BeanCopier.create(OneLessSetter.class, One.class, false);
        One one = new One();
        copier.copy(oneLessSetter, one, null);
        System.out.println(oneLessSetter.toString());
        System.out.println(one.toString());
    }
}
