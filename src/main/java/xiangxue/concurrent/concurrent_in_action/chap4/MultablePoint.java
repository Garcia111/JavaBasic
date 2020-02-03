package xiangxue.concurrent.concurrent_in_action.chap4;

import net.jcip.annotations.NotThreadSafe;

/** 可变Point,非线程安全
 * @author：Cheng.
 * @date：Created in 19:50 2020/2/1
 */
@NotThreadSafe
public class MultablePoint {

    public int x, y;


    public MultablePoint(){
        x=0;
        y=0;
    }

    public MultablePoint(MultablePoint p){
        this.x = p.x;
        this.y = p.y;
    }





















}
