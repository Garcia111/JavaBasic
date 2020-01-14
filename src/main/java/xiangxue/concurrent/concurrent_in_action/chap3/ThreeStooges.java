package xiangxue.concurrent.concurrent_in_action.chap3;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.HashSet;
import java.util.Set;

/**
 * @author：Cheng.
 * @date：Created in 11:24 2020/1/14
 */
@Immutable
public final class ThreeStooges {

    private final Set<String> stooges = new HashSet<String>();


    public ThreeStooges(){
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }

    public boolean isStooge(String name){
        return stooges.contains(name);
    }

}
