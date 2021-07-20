package com.macky.springbootshardingjdbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.macky.springbootshardingjdbc.entity.Book;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Macky
 * @Title class BookMapper
 * @Description: TODO
 * @date 2019/7/12 20:46
 */
@Mapper
public interface BookMapper extends BaseMapper<Book> {
}
