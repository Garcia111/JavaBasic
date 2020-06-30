package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author：Cheng.
 * @since： 2020/05/27
 */
@Component("B")
public class B {

    @Autowired
    A a;
}
