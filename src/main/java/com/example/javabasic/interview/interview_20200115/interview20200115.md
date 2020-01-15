死锁  活锁与饥饿：https://zhuanlan.zhihu.com/p/86267946

**隐藏死锁**
    隐藏的死锁情况
    真实世界里的死锁情况可能更难被发现。比如我们看下边这个考试场景，一个老师监考若干名学生，试卷一共有100道题，我们先看一下学生的java代码：

    public class Student {

        private Teacher teacher;

        private int process;    //答题进度

        public void setTeacher(Teacher teacher) {
            this.teacher = teacher;
        }

        public synchronized int getProcess() {
            return process;
        }

        public synchronized void setProcess(int process) {
            this.process = process;
            if (process == 100) {
                teacher.studentNotify(this);    //学生答完题，通知老师
            }
        }
    }
    每个Student对象里都维护一个Teacher对象，字段process代表当前的答题进度，可以调用getProcess来获取当前的答题进度，也可以通过setProcess来设置当前的答题进度，当process的值为100时就意味着完成了考试，可以调用Teacher对象的studentNotify方法来交卷。再看一下Teacher的代码：

    import java.util.List;

    public class Teacher {

        List<Student> students;

        public void setStudents(List<Student> students) {
            this.students = students;
        }

        public synchronized void studentNotify(Student student) {
            students.remove(student);   //将已完成考试的学生从列表中移除
        }

        public synchronized void getAllStudentStatus() {
            for (Student student : students) {
                System.out.println(student.getProcess());
            }
        }
    }
    每个Teacher对象里都维护了一个students字段，它代表若干个Student对象。每当有学生交卷调用自己的studentNotify方法时，都把该学生从students列表中删除。另外，Teacher还有一个getAllStudentProcess方法，可以获取某个时刻还在答题的各个学生的答题进度。

    这两个类貌似人畜无害，但是确暗藏玄机：

    Student的setProcess方法是同步方法，Teacher的studentNotify也是同步方法，而在setProcess方法中可能调用studentNotify方法。也就是说执行setProcess方法的线程需要先获得Student对象的锁，再获得Teacher对象的锁。
    Teacher的getAllStudentProcess方法是同步方法，Student的getProcess方法也是同步方法，而在getAllStudentProcess方法中调用了getProcess方法。也就是说执行getAllStudentProcess方法的线程需要先获得Teacher对象的锁，再获得Student对象的锁。
    如果线程t1执行setProcess方法，线程t2执行getAllStudentProcess方法。假设Student对象为s，Teacher对象为t，那么一种可能的执行时序就是：


    所以这两个线程最终可能造成死锁。

    在Student对象的setProcess里调用了Teacher对象的studentNotify方法，我们就称studentNotify方法是Studnet类的外部方法；同样的，getProcess方法也是Teacher类的外部方法。
    【需要我们注意的是：如果在持有锁的情况下调用了某个外部方法，那么就需要警惕死锁。】

    其他资源死锁
    只要出现下边这种情况系统都可能进入死锁状态：每个线程都拥有其他线程需要的资源，同时又等待其他线程已经拥有的资源，并且每个线程在获得全部需要的资源之前不会释放已经拥有的资源。

    我们说锁其实本身就是一种线程执行需要获取的资源，任何可以被共享的东西都可以被当作一种资源来对待。一个线程完成任务需要获取多个资源，并且多个资源是互斥的，也就是某个资源在同一时刻只能被一个线程拥有，典型的这种资源就是数据库连接。

**预防死锁的建议**

    前人已经认真的总结过产生死锁的几个必要条件：

    互斥条件：一个资源每次只能被一个线程使用。
    请求与保持条件：一个线程因请求资源而阻塞时，对已获得的资源保持不放。
    不剥夺条件：线程已获得的资源，在未使用完之前，不能强行剥夺。
    循环等待条件：若干线程之间形成一种头尾相接的循环等待资源关系。
    只有这4个条件全部成立，死锁的情况才有可能发生。听清楚了，我说的是才有可能发生。因为一般情况下，一个线程持有资源的时间并不会太长，所以一般并不会发生死锁情况，但是如果并发程度很大，也就是非常多的线程在同时竞争资源，如果这四个条件都成立，那么发生死锁的概率将会很大，重要并且可怕的是：一旦系统进入死锁状态，将无法恢复，只能重新启动系统。

    不过只要破坏上述4个条件中的任意一个，那么死锁的情况就不会发生，所以在设计代码的时候注意这么几点：

  1.线程在执行任务的过程中，最好进行开放调用。

    如果在调用某个方法的时候不需要持有锁，那么这种调用就称为开放调用。像上边Student类调用外部方法studentNotify的时候就已经持有锁了，所以我们可以这样改写一下：
      public void setProcess(int process) {
        synchronized (this) {
            this.process = process;
        }
            if (process == 100) {
                teacher.studentNotify(this);    //学生答完题，通知老师
            }
    }
    这样锁只用来保护共享变量，而把对studentNotify的调用改为开放调用，这样就不会有死锁的问题啦。同样的，我们可以这样改写getAllStudentProcess方法：

    public void getAllStudentStatus() {
        List<Student> copyOfStudents;
        synchronized (this) {
            copyOfStudents = new ArrayList(students);
        }
        for (Student student : copyOfStudents) {
            System.out.println(student.getProcess());
        }
    }


    我们把共享变量students在线程本地复制了一份，锁只用来保护复制students的时候不会有别的线程去修改它，从而把对getProcess的调用也改为了开放调用。开放调用其实是避免了循环等待条件，从而使死锁不可能发生。
    当然，如果代码没有了锁保护就会丧失原子性，如果某个外部调用需要被加锁保护来和其他一些操作共同组成某个大的原子性操作的话，就不能进行开放调用了～

  2.各个线程最好用固定的顺序来获取资源。
    我们可以改写一下最开始的那个例子，线程t1改写成这样：

    synchronized (lock1) {
        System.out.println("线程t1获取了 lock1锁");
        LockUtil.sleep(1000L);
     synchronized (lock2) {
            System.out.println("线程t1获取了 lock2锁");
        }
    }
    线程t2改写成这样：

    synchronized (lock1) {
        System.out.println("线程t2获取了 lock1锁");
        LockUtil.sleep(1000L);
     synchronized (lock2) {
            System.out.println("线程t2获取了 lock2锁");
        }
    }
    在一个线程获取lock1锁的时候，另一个线程就不能执行了，只能等待已经获取锁的线程把所有操作都做完后释放锁再执行。也就是说不存在循环等待条件了。

  3.可以让持有资源的时间有限。

    我们前边用到的synchronized加锁机制是没有超时时间的，也就是说如果一个线程获取锁之后，直到同步代码块中的代码执行完成之后才能释放锁。所以在死锁的情况下，一个线程是不会主动去释放锁的，如果我们让锁有了超时时间，就可以打破不剥夺条件。
    比如前边说的十字路哭堵车的例子，如果规定一个车如果30秒不能前行，则先倒车几秒钟后重试，这样就可以破解死锁的魔咒了。

**饥饿**

    如果一个线程因为处理器时间全部被其他线程抢走而得不到处理器运行时间，称为饥饿。一般是由高优先级线程吞噬所有的
    低优先级线程的处理器时间引起的。
   线程饥饿是一种活跃性问题，也可以使程序无法执行下去。

   java语言在Thread类中提供了修改线程优先级的成员方法setPriority，并且定义了10个优先级级别。
   不同操作系统有不同的线程优先级，java会把这10个级别映射到具体的操作系统线程优先级上边。
   操作系统的线程调度会按照自己的调度策略来轮番执行我们定义的线程。


   我们设置的线程优先级对操作系统来说只是一种建议，当我们尝试提高一个线程的优先级的时候，可能起不到任何作用，也可能使这个
   线程过度优先执行，导致别的线程得不到处理器分配的时间片，从而导致饿死。
   【所以我们尽量不要修改线程的优先级，具体效果取决于具体的操作系统，并且可能会导致某些线程饿死】


 **活锁**
   虽然不会像死锁那样因为获取不到资源而阻塞，也不会像饥饿那样得不到处理器时间而无可奈何，活锁仍旧可以让程序无法执行下去～
   比如在一间教室里，狗哥要出去，猫爷要进来，门只能容得下一个人进出，而它们在门口相遇了，所以狗哥往后退了一步意思是猫爷
   先进，而猫爷也退了一步意思是狗哥先进；之后狗哥往前走了一步，猫爷也往前走了一步，俩人又都堵在了门口，所以又都同时退一步，
   然后再同时进一步，同时退一步，同时进一步…..

   把狗哥和猫爷都比做一个线程的话，这两个线程虽然都没有停止运行，但是却无法向下执行，这种情况就是所谓的活锁。

   为了解决活锁问题，需要在遇到冲突重试时，引入一定的随机性。
   比如狗哥和猫爷在门口相遇都后退时，狗哥隔一秒后再前进，猫爷隔两秒后再前进，这样就不会有同时走到门口的尴尬了～