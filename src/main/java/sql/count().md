
Count函数的使用方法有COUNT(*)和COUNT(列名)，区别：
    1、性能上的区别；
    2、COUNT(*)可以用于NULL，而COUNT(列名)与其他聚合函数一样，要先排除掉NULL的行再进行统计