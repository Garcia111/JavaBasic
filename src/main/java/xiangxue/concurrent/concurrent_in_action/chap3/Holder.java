package xiangxue.concurrent.concurrent_in_action.chap3;

/**
 * @author：Cheng.
 * @date：Created in 15:19 2020/1/14
 */
public class Holder {

    private int n;

    public Holder(int n){
        this.n = n;
    }

    public void assertSanity(){
        if(n !=n ){
            throw new AssertionError("This statement is false.");
        }
    }
}
