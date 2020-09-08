package interview.interview_20200907;

/**
 * @author：Cheng.
 * @since： 0907
 */
public class Interview200907 {

    public static void main(String[] args) {
        byte b = 2, e=3; //---此处不会报错，byte占用一个字节，8位，默认数据的范围为-128 ~ 127
//        byte f = b+3; // b+3为int类型，不能直接赋值为byte类型，必须要强制类型转换一下

        byte f = (byte) (b+3);
        System.out.println("f = " + f);
    }
}
