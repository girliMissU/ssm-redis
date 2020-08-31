/**
* 红包表
 */
create table T_RED_PACKET(
    id int(12) not null auto_increment,
    user_id int(12) not null,
    amount decimal(16,2) not null,
    send_date timestamp not null,
    total int(12) not null,
    unit_amount decimal(12) not null,
    stock int(12) not null,
    version int(12) default 0 not null,
    note varchar(256) null,
    primary key clustered (id)
);

/**
* 用户抢红包表
 */
 create table T_USER_RED_PACKET(
    id int(12) not null auto_increment,
    red_packet_id int(12) not null,
    user_id int(12) not null,
    amount decimal(16,2) not null,
    grab_time timestamp not null,
    note varchar(256) null,
    primary key clustered (id)
 );

 insert into T_RED_PACKET(user_id, amount, send_date, total, unit_amount, stock, note)
    values (1,200000.00,now(),20000,10.00,20000,'20万元金额，2万个小红包，每个10元');

select a.amount, a.stock, id from T_RED_PACKET a where id = 1
union all
select sum(b.amount), count(*), max(user_id) from T_USER_RED_PACKET b where b.red_packet_id = 1;