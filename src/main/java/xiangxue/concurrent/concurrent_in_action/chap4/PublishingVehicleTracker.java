package xiangxue.concurrent.concurrent_in_action.chap4;

import net.jcip.annotations.ThreadSafe;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author：Cheng.
 * @date：Created in 23:02 2020/2/2
 */
@ThreadSafe
public class PublishingVehicleTracker {

    private final Map<String, SafePoint> locations;
    private final Map<String, SafePoint> unmodifiableMap;

    public PublishingVehicleTracker(Map<String,SafePoint> locations){
        //使用concurrentHashMap来确保容器的线程安全
        this.locations = new ConcurrentHashMap<String,SafePoint>(locations);
        //这里直接复制的是SafePoint的索引，因此位置是实时发布的，并且因为SafePoint是线程安全的，因此可以安全发布
        this.unmodifiableMap = Collections.unmodifiableMap(this.locations);
    }


    public Map<String, SafePoint> getLocations(){
        return unmodifiableMap;
    }

    public void setLocation(String id, int x, int y){
        if(!locations.containsKey(id)){
            throw new IllegalArgumentException("invalid vehicle name: "+id);
        }
        locations.get(id).set(x,y);
    }
}
