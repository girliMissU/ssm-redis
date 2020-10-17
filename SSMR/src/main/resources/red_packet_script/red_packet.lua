--缓存抢红包列表信息列表key
local listKey = 'red_packet_'..KEYS[1]
--当前被抢红包key
local redPacket = 'red_packet_'..KEYS[1]
--获取当前红包库存
local stock = tonumber(redis.call('hget', redPacket, 'stock'))
--没有库存，返回为0
if stock <= 0 then return 0 end
--库存减1
stock = stock -1
--保存当前库存
redis.call('hset', redPacket, 'stock', toString(stock))
--往链表中加入当前红包信息
redis.call('rpush', listKey, ARGV[1])
--如果是最后一个红包，则返回2，表示抢红包已经结束，需要将链表中的数据保存到数据库中
if stock == 0 then return 2 end
--如果并非最后一个红包，则返回1，表示抢红包成功
return 1

--测试前先在Redis上添加红包信息，是命令，不是脚本，要在数据库也建立对应的记录
--hset red_packet_5 stock 20000
--hset red_packet_5 unit_amount 10