package xiangxue.concurrent.concurrent_in_action.chap4;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 监视器模式，机动车追踪器
 * @author：Cheng.
 * @date：Created in 19:45 2020/2/1
 */
@ThreadSafe
public class MonitorVehicleTracker {

    @GuardedBy("this")
    private final Map<String,MultablePoint> locations;


    public MonitorVehicleTracker(Map<String, MultablePoint> locations) {
        this.locations = locations;
    }


    //每次调用getLocation之前赋值全部数据会产生的副作用，即使真实的localtion已经改变，返回的容器的内容仍然不会改变
    public synchronized  Map<String, MultablePoint> getLocations(){
        return deepCopy(locations);
    }


    public synchronized MultablePoint getLocations(String id){
        MultablePoint loc = locations.get(id);
        return loc == null ? null : new MultablePoint(loc);
    }

    public synchronized void setLocations(String id, int x, int y){
        MultablePoint loc = locations.get(id);
        if(loc == null){
            throw new IllegalArgumentException("No such ID: "+id);
        }
        loc.x = x;
        loc.y = y;
    }

    private static Map<String,MultablePoint> deepCopy(Map<String,MultablePoint> m){
        Map<String, MultablePoint> result = new HashMap<String,MultablePoint>();
        //MultablePoint不是线程安全的，deepCopy不能仅仅用一个unModifiableMap包装Map，因为这样做只能
        //保护容器不被修改，并不能防止调用者修改存储在其中的可变对象。
        //因此在deepCopy通过一个拷贝构造函数生成HashMap同样是不正确的，因为这样做只是复制了MultablePoint的引用，
        //而不是MultablePoint对象本身
        for(String id : m.keySet()){
            result.put(id,new MultablePoint(m.get(id)));
        }
        return Collections.unmodifiableMap(result);
    }
}
















