HashMap

   一定要看下面的文章啊
   https://mp.weixin.qq.com/s/lV4sp7S_c423pamK_yMadQ

  Q1:HashMap初始大小默认设置为16，为2的幂次，后面在扩容时，都是以2的倍数来扩容。
    为什么非要将HashMap的大小控制为2的幂次？


    原因1： 降低发生碰撞的概率，使散列更加均匀。根据key的Hash值计算下标位置时，使用“与”运算公式：
            hash(key)*(length-1)，当哈希表长度为2的幂次时，等同于使用表长度对Hash值取模，散列更加均匀；

    原因2：  表的长度为2的幂次，那么（length-1）的二进制最后一位一定是1，在对Hash值做“与”运算时，最后一位
            就可能为1，也可能为0，1&1=1,1&0=0,取模结果既有偶数，又有奇数。如果（length-1）为偶数，那么“与”
            运算后的值只能是0，奇数下标的bucket就永远散列不到，会浪费一半空间。

    相比JDK7,HashMap在JDK8中做链表结构做了优化（但是仍然线程不安全），在一定条件下将链表转为红黑树，提升查询效率。


  Q2：高并发情况下为什么HashMap可能会出现死锁？
        Rehash是HashMap在扩容的时候的一个步骤，扩容需要经过下面两个步骤：
        1.扩容：创建一个新的Entry空数组，长度是原数组的2倍；
        2.rehash：遍历原Entry数组，把所有的Entry重新Hash到新数组

        多线程环境中，HashMap的rehash操作可能会带来什么样的问题？rehash在并发情况下可能会形成链表环，当调用get()查找一个不存在的
        key时，这个key的hash恰好在链表环形成的Bucket所在的index，程序就会进入死循环。



  Q3：如何判断链表有环


  Q4：ConcurrentHashMap:在并发场景下ConcurrentHashMap是怎么保证线程安全的？又是怎么实现高性能读写的？

        想要避免HashMap的线程安全问题有很多种方法，比如改用HashTable或者Collections.synchronizedMap
        但是这两者都有共同的问题：性能，无论读操作还是写操作，它们都会给整个集合加锁，导致同一时间的其他操作为之阻塞。


        在并发环境下，如何能过兼顾线程安全和运行效率呢？这时候ConcurrentHashMap就应运而生了。
        Segment:ConcurrentHashMap是一个二级哈希表，在一个总的哈希表下面，有若干个子哈希表每个子哈希表称为一个Segment。
        ConcurrentHashMap的优势是采用了锁分段技术，每一个Segment就好比一个自治区，读写操作高度自治，Segment之间互不影响。
        不同Segment的写入是可以并发执行的，但是对同一Segment的并发写入会被阻塞。
        ConcurrentHashMap当中每个Segment各自持有一把锁。在保证线程安全的同时降低了锁的粒度，让并发操作效率更高。


        Get方法：
        1.为输入的key做Hash运算，得到hash值；
        2.通过hash值，定位到对应的Segment对象；
        3.再次通过hash值，定位到Segment当中数组的具体位置


        Put方法
        1.为输入的key做Hash运算，得到Hash值；
        2.通过Hash值，定位到对应的Segment对象；
        3.获得可重入锁；
        4.再次通过Hash值，定位到Segment当中数组的具体位置
        5.插入或者覆盖HashEntry对象；
        6.释放锁


        ConcurrentHashMap在读写时都需要二次定位，首先定位到Segment，之后定位到Segment内的具体数组下标。

        ConcurrentHashMap的size统计：
        ConcurrentHashMap的Size方法是一个嵌套循环,
        1.遍历所有的Segment;
        2.把Segment的元素数量累加起来；
        3.将Segment的修改次数累加起来；
        4.判断所有Segment的总修改次数是否大于上一次的总修改次数，如果大于，说明统计过程中有修改，重新统计，尝试次数+1；
          如果不是，说明没有修改，统计结束。
        5.如果尝试次数超过阈值，则对每一个Segment加锁，再重新统计。
        6.再次判断所有的Segment的总修改次数是否大于上一次的总修改次数，由于已经加锁，次数一定是相等的。（那这里为什么要再对比一下呢）
        7.释放锁，统计结束

        这种思想和乐观锁和悲观锁的思想如出一辙：
        为了尽量不锁住所有的Segment，首先乐观地假设Size统计过程中不会有修改，当尝试一定次数，才无奈转为悲观锁，锁住所有Segment保证强一致性、
