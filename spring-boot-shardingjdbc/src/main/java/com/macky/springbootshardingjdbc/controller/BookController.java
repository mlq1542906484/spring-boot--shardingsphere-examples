package com.macky.springbootshardingjdbc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macky.springbootshardingjdbc.entity.Book;
import com.macky.springbootshardingjdbc.service.BookService;
import com.macky.springbootshardingjdbc.util.IdWorker;
import com.macky.springbootshardingjdbc.util.SnowFlakeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    private IdWorker worker = new IdWorker(1,1);

    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public List<Book> getItems() {
        return bookService.getBookList();
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public Boolean saveItem(Book book) {
//        Comparable<?> id = SnowFlakeUtils.getId(Long.parseLong("123"));
//        book.setId(Long.parseLong(id.toString()));
        Long id = worker.nextId();
        book.setId(id);
        return bookService.save(book);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public IPage<Book> page(Integer pageNum, Integer pageSize) {
        Page<Book> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("name", "孟令乾");
        queryWrapper.orderByAsc("id");
        return bookService.page(page, queryWrapper);
    }

}
