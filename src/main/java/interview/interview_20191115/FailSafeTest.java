package interview.interview_20191115;

import org.junit.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author：Cheng.
 * @date：Created in 20:07 2019/11/17
 */
public class FailSafeTest {

    /**
     * 安全失败
     */
    @Test
    public void failSafeTest(){
        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<String,String>();
        map.put("name","张三");
        map.put("age","18");

        Iterator<Map.Entry<String,String>> mapiterator = map.entrySet().iterator();
        while(mapiterator.hasNext()){
            System.out.println(mapiterator.next().getValue());
            map.remove("name");
        }
        return;
    }


}
