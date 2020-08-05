package spring.ioc;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


/**
 * dao是原型的，每次请求都会创建一个新的indexDao
 */
@Repository("dao")
@Scope("prototype")
public class IndexDaoImpl implements IndexDao{
}
