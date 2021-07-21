package com.macky.springbootshardingjdbc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.macky.springbootshardingjdbc.entity.Book;

import java.util.List;

/**
 * @author Macky
 * @Title interface BookService
 * @Description: TODO
 * @date 2019/7/12 20:47
 */
public interface BookService extends IService<Book> {

    /**
     * 查询全部书籍信息
     *
     * @return
     */
    List<Book> getBookList();
}
