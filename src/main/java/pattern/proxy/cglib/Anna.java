package pattern.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * 《诺丁山》茱莉亚罗伯茨
 * @author：Cheng.
 * @since： 2020/07/28
 */
public class Anna {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Hugrant.class);
        enhancer.setCallback(new HugrantFriends());
        //通过creat()得到代理对象，对这个对象的所有非final方法的调用，都会转发给MethodInterceptor.intercept()方法
        Hugrant hugrant = (Hugrant)enhancer.create();

        System.out.println("Anna: I'm just a girl stand before a boy asking him to love her");
        System.out.println(hugrant.sayQingHua());
    }
}
