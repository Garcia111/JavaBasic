package xiangxue.concurrent.concurrent_in_action.chap4;

import com.example.javabasic.dto.Person;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.HashSet;
import java.util.Set;

/**
 * @author：Cheng.
 * @date：Created in 14:16 2020/2/1
 */
@ThreadSafe
public class PersonSet {

    //HashSet被限制在PersonSet中，唯一可以访问mySet的代码路径是addPerson与containPerson
    //执行它们都要获得PersonSet的锁，PersonSet的内部锁保护了它所有的状态，因而确保了PersonSet是线程安全的
    //本例中如果Person是可变的，那么访问从PersonSet中获得Person时，还需要额外的同步。
    //为了安全地使用Persson对象，最可靠的方法是让Person自身是线程安全的。
    @GuardedBy("this")
    private final Set<Person>  mySet = new HashSet<Person>();

    public synchronized void addPerson(Person p){
        mySet.add(p);
    }

    public synchronized boolean containPerson(Person p){
        return mySet.contains(p);
    }
}
