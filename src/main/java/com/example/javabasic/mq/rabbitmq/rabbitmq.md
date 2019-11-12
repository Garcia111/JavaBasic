RabbitMQ常用的交换器类型有fanout、direct、topic、headers这四种。
AMQP协议里还提到了另外两种类型：System和自定义。

1.fanout
    
    它会把【所有】发送到该交换器的消息路由到【所有】与该交换器绑定的队列中。
    
2.direct

    direct类型的交换器路由规则也很简单，它会将消息路由到那些BindingKey和RoutingKey完全匹配的队列中。
    
3.topic

    direct类型的交换器路由规则是完全匹配BindingKey和RoutingKey，但是这种匹配格式在很多情况下不能满足实际业务的需求。
    topic类型的交换器在匹配规则上进行了扩展，它与direct类型的交换器类似，也是将消息路由到BindingKey和RoutingKey
    相匹配的队列中。但是这里的匹配规则有些不同。它约定：
    1.RoutingKey为一个点号“.”分隔的字符串，如“com.rabbitmq.client”;
    2.BindingKey和RoutingKey 一样也是点号“.”分隔的字符串；
    3.BindingKey中可以存在两种特殊的字符串“*”和“#”用于做模糊匹配，其中“*”用于匹配一个单词，“#”用于匹配0个或者多个单词。
    
    
4.headers（headers类型的交换器性能很差，而且也不实用，基本上不会使用到）
    
    headers类型的交换器不依赖于路由键的匹配规则来路由消息，而是根据发送的消息内容中的headers属性进行匹配。在绑定队列和交换器时指定一组键值对。
    当发送消息到交换器时，RabbitMQ会获取到该消息的headers(也是一个键值对的形式)，对比其中的键值对是否完全匹配队列和交换器绑定时
    指定的键值对，如果完全匹配则消息会路由到该队列。
    