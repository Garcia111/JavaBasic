package xiangxue.concurrent.concurrent_in_action.chap4;

import net.jcip.annotations.ThreadSafe;

import java.awt.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 将线程安全委托到ConcurrentHashMap
 * @author：Cheng.
 * @date：Created in 18:10 2020/2/2
 */

@ThreadSafe
public class DelegatingVehicleTracker {
    private final ConcurrentMap<String, Point> locations;
    private final Map<String,Point> unModifiableMap;

    public DelegatingVehicleTracker(Map<String,Point> points){
        locations = new ConcurrentHashMap<String, Point>(points);
        unModifiableMap = Collections.unmodifiableMap(locations);
    }

    //这里copy的只是引用，并不是创建了一个新的对象啊？所以这里返回的是实时的
    //基于委托的代码返回一个不可变的，但是确实“现场”的location视图
    public Map<String,Point> getLocations(){
        return unModifiableMap;
    }

    public Map<String,Point> getLocationsNotLive(){
        return Collections.unmodifiableMap(new HashMap<String,Point>(locations));
    }


    public Point getLocation(String id){
        return locations.get(id);
    }


    public void setLocation(String id, int x, int y){
        if(locations.replace(id,new Point(x,y)) == null){
            throw new IllegalArgumentException("invalid vehicle name: "+id);
        }
    }

}


















