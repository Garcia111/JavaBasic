package com.example.javabasic.collectionutil;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;

/**
 * @author：Cheng.
 * @date：Created in 17:26 2019/9/6
 */
public class GoogleCollectionUtils {

    @Test
    public void  collectionOperation(){
        HashSet<Integer> setF = Sets.newHashSet(7, 6, 6);
        HashSet<Integer> setT = Sets.newHashSet(1, 2, 3,4);
        //并集:结果【1,2,3,4,5】
        Sets.SetView<Integer> union = Sets.union(setF, setT);
        //差集:返回只存在于setF独有的数据, 结果【1,2】
        Sets.SetView<Integer> difference = Sets.difference(setF, setT);
        //交集:结果【3】
        Sets.SetView<Integer> intersection = Sets.intersection(setF, setT);
        if(intersection.size()>0){
            System.out.println("有交集元素");
        }else{
            System.out.println("无 交集");
        }

        System.out.println("并集:");
        for (Integer integer : union) {
            System.out.println(integer);
        }
        System.out.println("差集:");
        for (Integer integer : difference) {
            System.out.println(integer);
        }
        System.out.println("交集:");
        for (Integer integer : intersection) {
            System.out.println(integer);
        }
    }



    /**
     * 使用google的Lists中的partion接口对list进行分隔
     */
    @Test
    public void partion(){
        List<String> list= Lists.newArrayList();
        for(int i=0;i<51;i++){
            list.add(""+i);
        }
        Lists.partition(list,10).forEach(lists-> {
            System.out.println(lists);
        });
    }
}
