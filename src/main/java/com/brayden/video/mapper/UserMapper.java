package com.brayden.video.mapper;

import com.brayden.video.entity.Account;
import com.brayden.video.entity.User;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

@Mapper
public interface UserMapper {

    @InsertProvider(type = SqlProvider.class, method = "addUser")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int addUser(User user);

    @SelectProvider(type = SqlProvider.class, method = "getUserById")
    public User getUserById(int id);


    public class SqlProvider {

        public String addUser(){
            SQL sql = new SQL();
            sql.INSERT_INTO("user");
            sql.VALUES("name", "#{name}");
            sql.VALUES("email", "#{email}");
            sql.VALUES("created_time", "#{createdTime}");
            return sql.toString();
        }

        public String getUserById(){
            SQL sql = new SQL();
            sql.SELECT("id");
            sql.SELECT("name");
            sql.SELECT("email");
            sql.SELECT("created_time");
            sql.SELECT("modified_time");
            sql.SELECT("flag");
            sql.FROM("user");
            sql.WHERE("id = #{arg0}");
            return sql.toString();
        }
    }
}
