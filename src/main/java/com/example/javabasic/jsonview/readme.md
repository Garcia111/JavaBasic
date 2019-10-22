@JsonView：
在某些请求返回的场景中，我们并不希望返回某些字段，而在另外一些请求中想要返回该字段。
这种情况，放在以前，我一般会多建几个DTO对象，最近看大牛的代码发现了一个有用的注解 @JsonView

@JsonView的使用步骤：
1.使用接口来声明多个视图
   eg:
   public interface UserSimpleView{};
   public interface UserDetailView extends UserSimpleView{};
2.在值对象的get方法上指定视图
3.在Controller的方法上指定视图

参看User 对象，想要在UserSimpleView视图中不返回password属性，想在UserDetailView视图中返回password

@JsonView使用的注意事项：
1.如果不在Controller方法上使用@JsonView注解声明需要向前端返回的视图，则会忽略类中的注解，返回所有的字段;
2.Controller根据声明的视图来过滤字段，如果在类中一些属性声明了视图，有一些没有，则没有声明的属性会被过滤掉，不会返回给前端；
3.如果将声明视图的类作为另外一个类的属性，则在Controller方法上声明的视图将会失效，返回的数据为空,什么都不会返回！！！
 eg：PersonInfo中包含User类，
 如果仍然想在返回中使用不同的视图，则需要继续在PersonInfo中User类的属性上添加@JsonView注解；
4.在声明视图的类中如果有其他自定义的类作为属性，且该属性类没有声明相同的视图，则该属性类中的属性会被过滤掉。
  eg:在PersonInfo类中有Student自定义类对象，PerosnInfo类中Student对象上加上了@JosnView注解，
    但是Student类中的属性没有加该注解，测试结果返回为空；
5.如果使用jpa搞数据库，并且出现了分页查询的业务请求，name可能就需要返回Page对象了，
  此时@JosonView失效了，明明查到了但是数据返回为空；
6.


