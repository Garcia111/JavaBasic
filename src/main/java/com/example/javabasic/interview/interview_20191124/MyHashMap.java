package com.example.javabasic.interview.interview_20191124;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

import static java.util.Objects.hash;

/**
 * @author：Cheng.
 * @date：Created in 10:35 2019/12/11
 */
public class MyHashMap<K,V> extends AbstractMap<K,V>implements Map<K,V>, Cloneable, Serializable {


    /**HashMap的初始大小，必须为2的幂次*/
    //todo ？？为什么必须是2的幂次
    static final int DEFAULT_INITAL_CAPACITY = 16;


    /**Hash表的最大长度*/
    static final int MAXIMUM_CAPACITY = 1 <<30;

    /**默认加载因子，未指定时，则默认0.75f*/
    static final float DEFAULT_LOAD_FACOTR = 0.75f;

    /**Hash表存储Entry对象的数组*/
    transient  Entry<K,V>[] table;

    /**map中存的k-v对的个数*/
    transient int size;

    /**阈值，当上面的size>=threshold时需要对哈希表进行扩容； threshold = table.length * loadFactor*/
    int threshold;

    /**加载因子*/
//    final float loadFactor;

    /**修改次数：记录了map中新增/删除k-v对，或者内部结构做了调整的次数，其主要作用是对Map的iterator()操作做一致性校验
     * 如果在iterator的操作过程中，map的数值有修改，直接抛出ConcurrentModificationException异常*/
    transient int mdnCount;


    /**
     * 首先拿put方法开刀
     * @return
     */
//    @Override
//    public V put(K key, V value) {
//        if(key==null){
//            return putForNullKey(value);
//        }
//        int hash = hash(key);
//        int i = indexFor(hash,table.length);
//        for(Entry<K,V> e = table[i];e!=null; e=e.next){
//            Object k;
//            if(e.hash )
//        }
//
//
//    }

    /**
     * 根据key的hash值和hashMap数组的长度，取得key对应bucket的下标
     * @param h  hash(key)
     * @param length
     * @return
     */
    static int indexFor(int h, int length){
        return h&(length-1);
    }


    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
