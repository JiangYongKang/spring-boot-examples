# 一、这个模块做什么

这个模块演示用 Spring Data Redis 读写字符串和哈希，并在启动时监听一条 Redis Stream。

它是父工程的一个子模块，依赖 spring-boot-starter-web 和 spring-boot-starter-data-redis。没有 Controller。仓库总览写在根目录 README.md。

这份说明按当前源码静态整理，没有连接 Redis，也没有跑测试。

# 二、连接配置

连接写在 src/main/resources/application.properties。

```properties
spring.redis.host=localhost
spring.redis.port=6379
spring.redis.database=0
spring.redis.timeout=5000
spring.redis.lettuce.pool.max-idle=8
spring.redis.lettuce.pool.max-active=8
spring.redis.lettuce.pool.max-wait=-1ms
spring.redis.lettuce.pool.min-idle=0
spring.redis.lettuce.shutdown-timeout=100ms
```

host 是本机，端口 6379，使用 0 号库。timeout 的值 5000 按毫秒理解。Lettuce 池最大空闲和最大活跃都是 8，最小空闲是 0。max-wait 写成 -1ms，表示借连接时一直等到有连接为止。客户端关闭超时是 100 毫秒。

# 三、两个模板

RedisConfig 注册两个 Bean。

1. stringRedisTemplate 的类型是 StringRedisTemplate。key 和 hash key 使用 StringRedisSerializer。
2. redisTemplate 的类型是 RedisTemplate<String, Serializable>。key 和 hash key 同样使用字符串序列化，value 使用模板默认的 JDK 序列化。

# 四、测试里的读写

测试类是 ApplicationTests。三个测试都要能连上上面配置的 Redis。

1. stringRedisTemplateTest 把键 MSG 写成 Hi，再读出来，要求两者相等。
2. redisTemplateTest 向哈希 HMSG 写入字段 MSG、值 Hi，读出来转成字符串后再比较。测试字段声明成 RedisTemplate<String, Object>，@Resource 按字段名 redisTemplate 注入上面的同名 Bean。
3. redisStreamTest 往流 MESSAGE_EVENT 追加一条字段 MSG、值 Hi 的消息。这个测试没有断言，也没有等待监听器处理。

# 五、Stream 怎么监听

流的键和消费组写在 MessageEventListener 里。STREAM_KEY 是 MESSAGE_EVENT，STREAM_GROUP 是 MESSAGE_GROUP。

RedisStreamConfig 在创建监听容器之前，先用 hasKey 看这条流在不在。键不在时做三步。

1. 追加一条字段名和字段值都是空字符串的记录。
2. 用只传入键和组名的 createGroup 创建消费组 MESSAGE_GROUP。Spring Data Redis 2.7 里，这个方法把组建在流的最新位置，所以这条占位记录不会进入待消费范围。
3. 按记录编号删掉占位记录，并打一行创建日志。

键已经存在时，这段代码不会再建组。组如果已经被删掉、键还留着，启动后的监听会缺少消费组。

监听容器的参数如下。

1. 线程池是 Executors.newFixedThreadPool(16)。
2. 每批最多 10 条，轮询间隔 1 秒，序列化用 StringRedisSerializer。
3. 出错时打日志“Redis stream error!”。
4. 消费者名字是 DEFAULT_CONSUMER，组名是 MESSAGE_GROUP，偏移是 ReadOffset.lastConsumed()，从该组已消费位置之后继续读。
5. 调用的是 receive。消息进入监听器之后，要由监听器自己确认。容器在 Bean 创建过程中就启动。

MessageEventListener 实现 StreamListener。onMessage 先打“Receive message:”，再调用 acknowledge，两个参数是 STREAM_KEY 和整条消息。

Spring Data Redis 2.7 里，字符串加整条 Record 的这个重载，第一个参数是消费组名字，流的键从消息里取。这里传入的是 MESSAGE_EVENT。监听时加入的组是 MESSAGE_GROUP，两边不一致。MESSAGE_GROUP 里的待确认记录因此去不掉。这是按方法签名读出来的结论，没有连上 Redis 验证。

应用关闭时，RedisStreamConfig 实现了 DisposableBean。destroy 先停止监听容器，再最多等 5 秒让订阅结束。上面那个 16 线程的线程池没有配套的关闭调用。
