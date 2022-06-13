package com.github.xuyh.batchservice.core.mapper;

import com.github.xuyh.batchservice.core.entity.TCoffee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TCoffeeMapper {
  @Insert(
      "insert into t_coffee (name, price, create_time, update_time) values (#{name}, #{price}, now(), now());")
  int insert(TCoffee coffee);

  TCoffee selectById(Long id);

  int deleteAll();
}
