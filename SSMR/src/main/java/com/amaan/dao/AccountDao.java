package com.amaan.dao;

import com.amaan.pojo.Account;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 佛祖保佑，永无BUG
 * @Repository 需要在Spring中配置扫描地址，然后生成Dao层的Bean才能被注入到Service层中。
 * @Mapper 不需要配置扫描地址
 * @author AMAN
 * SpringTest
 * 2019-12-28 22:03
 */
@Repository
public interface AccountDao {

    String tableName = "account";
    String insertColumns = "name,money";
    String selectColumns = "id,"+insertColumns;

//    @Select("select * from account")
    List<Account> findAll();
    /**
     * 用户名模糊查询
     * #{}使用预编译处理，替换为？，使用PreparedStatement进行替换，需在调用时提供百分号，可防止sql注入，但%需拼接
     * ${}是字符拼接，字符转替换，直接将${}替换成变量的值，其中value为固定写法
     * 实际开发中应使用#{}
     * @return
     */
//    @Select("select "+selectColumns+" from "+tableName+" where name like #{accountName}")
    List<Account> findAnyByName(String accountName);

    /**
     * 当参数只有一个时，#{}中取名任意
     */
    @Select("select "+selectColumns+" from "+tableName+" where id = #{accountId}")
    Account findOneById(Integer accountId);

    @Insert("insert into "+tableName+"("+insertColumns+")"+"values(#{name},#{money})")
    void insert(Account account);

    @Update("update "+tableName+" set name=#{name}, money=#{money} where id=#{id}")
    void update(Account account);

    @Delete("delete from "+tableName+" where id=#{id}")
    void deleteById(Integer accountId);
}
