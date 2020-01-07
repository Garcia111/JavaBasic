package com.example.javabasic.inner_class;

/**
 * @author：Cheng.
 * @date：Created in 17:09 2020/1/7
 */
public class Parcel6 {

    private void internalTracking(boolean b){
        if(b){
            //定义在if()作用域的内部类
            //todo
            //内部类被嵌入在if语句的作用域内，这并不是说类的创建是有条件的，它其实与别的类一起编译过了。
            //然而在定义此内部类的作用域之外，此内部类是不可用的。
            class TrackingSlip{
                private String id;
                TrackingSlip(String s){
                    id = s;
                }
                String getSlip(){return id;}
            }

            TrackingSlip ts = new TrackingSlip("slip");
            String s = ts.getSlip();
        }
        //此范围位于if()语句外部，不能在此区域访问TrackingSlip内部类
    }

    public void track(){internalTracking(true);}

    public static void main(String[] args) {
        Parcel6 p = new Parcel6();
        p.track();
    }

}
