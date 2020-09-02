package interview.interview_20200902;


import java.lang.reflect.Field;

/**
 * @author：Cheng.
 * @since： 20200902
 */
public class Test {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Class cache = Integer.class.getDeclaredClasses()[0];
        Field myCache = cache.getDeclaredField("cache");
        myCache.setAccessible(true);

        Integer[] newCache = (Integer[])myCache.get(cache);
        newCache[132] = newCache[133];
        //newCache[132]对应的数值是 132-128=4；
        //newCache[133]对应的数值是 133-128=5；
        //a+a应该是直接将下标值向后顺延了2个，取的newCache[132]中的值，但是newCache[132]中的值被重新赋值为5了，
        //所以出现了下面的结果：2+2 = 5；
        int a = 2;
        int b = a+a;
        System.out.printf("%d +%d = %d", a, a, b);
    }
}
