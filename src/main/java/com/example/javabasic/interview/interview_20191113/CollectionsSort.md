Jva集合排序工具
    Java集合的工具类Collections找那个提供了两种排序方法，分别是：
    
    1.Collections.sort(List list)-------List中的元素需要实现Comparable接口
    2.Collections.sort(List list, Comparator c)------需要实现一个实现了Comparator的类
    3.使用Stream的Sorted方法，最后需要collect(Collectors.toList())生成一个已经排序的列表，
    与上面两种方式不同，这种方法原来的list不会变化。
    测试类 CollectionsSortTest
    
    
比较Comparable 和Comparator 接口
    
    1.如果实现类没有实现Comparable接口，又想对两个类进行比较 或者实现类实现了Comparable接口，但是
    对compareTo 方法内的比较算法不太满意，可以实现Comparator接口，自定义一个比较器，写比较算法。
    
    2.解耦 
       实现Comparable接口的实现方式比实现Comparator接口的耦合性要强一些，如果要修改比较算法，要修改
       Comparable接口的实现类，而实现Comparator的类是在外部进行比较的，不需要对实现类有任何比较。
       从这个角度来说Comparable接口有点不太好，尤其是我们将实现类的 .class文件打包成一个jar文件提供
       给开发者使用的时候。