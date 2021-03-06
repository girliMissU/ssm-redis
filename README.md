## ssm-redis
ssm+redis脚手架

#### 应用架构分析
移动终端/电脑终端-->互联网-->防火墙-->负载均衡-->Web分布式服务器、NoSQL分布式服务器-->数据库分布式服务器
负载均衡器：
  1.对请求业务初步分析，决定是否分发到Web服务器，可配置禁止一些无效请求；
  2.提供路由算法，根据各个服务器的负载能力合理分发，降低单个服务器的压力，提高系统响应力；
  3.限流，避免高并发时刻出现宕机，雪崩，保证系统持续可用。

#### 高并发系统分析与设计
  1.无效请求
    1.1一个账号连续请求：首先，加入验证码，首次无验证码以便用户减少录入，第二次请求开始加入验证码；其次，使用短信服务，应在负载均衡器上完成；进一步地，比如限制用户在单位间的购买次数以压制其请求量。
    1.2一个人多个账户：提高账户等级，如通过实名制获取相关证件号码，将同一证件的多个账户归结为一人。
    1.3统一组织伪造有效请求：僵尸账号排除法，IP封禁（慎用！可能误伤）。
  2.系统设计
    高并发系统需要分布式的系统分摊请求的压力，就要使用负载均衡进行均衡分配请求。
    原则是：首先，服务器的负载均衡，要使每一个服务器都能比较平均地得到请求数量，从而提高系统的吞吐和性能。其次，业务简化，按照模块划分可以使得系统划分为各个子系统，业务单一化，容易理解和开发。
    服务器分法有水平和垂直以及结合分法，其中水平分法是按业务划分，各个系统由于存在关联，要通过RPC处理关联信息。
  3.数据库设计
    3.1使用分表或分库的技术，提高系统响应能力；
    3.2优化SQL；
    3.3读写分离
    3.4分布式数据库要保证数据一致性，目前流行的两端提交协议：XA、Paxos。
  4.动静分离技术
    可以使用CDN将静态数据缓存到网络CDN的节点中，也可以是静态HTTP服务器，尽量使用Cookie让客户端缓存数据，降低服务器的压力。对于动态数据，根据会员登录获取后台数据。
  5.锁和高并发
    悲观锁、乐观锁、使用Redis事务和Lua语言提供的原子性取代现有数据库技术（也属于乐观锁)。
    5.1悲观锁：select ... from ... where ... for update，主键查询->行锁，非主键查询->表锁，数据库内部提供，阻塞式。
    5.2乐观锁：基于CAS，先保存旧值，进行业务逻辑处理，然后比较数据库当前值与旧值，决定是否更新。会有ABA问题，可使用递增版本号记录更新次数解决。由于版本修改而放弃会导致大量更新失败，为提高成功率，使用重入机制，为避免过多重入导致大量SQL，加入限制，如按时间戳重入或按次数。
    5.3Redis：当红包金额为0或红包超时的时候，将红包数据保存到数据库。

#### 模拟抢红包
  20万元的红包，分为2万个可抢的小红包，3万人同时抢夺。
  Google浏览器3万个ajax，会出现失败与丢失。
