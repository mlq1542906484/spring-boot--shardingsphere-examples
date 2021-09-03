package com.macky.springbootshardingjdbc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macky.springbootshardingjdbc.entity.Book;
import com.macky.springbootshardingjdbc.service.BookService;
import com.macky.springbootshardingjdbc.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    StringRedisTemplate redisTemplate;

    private IdWorker worker = new IdWorker(1, 1);

    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public List<Book> getItems() {
        return bookService.getBookList();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @Transactional
    public Boolean saveItem(Book book) {
//        Comparable<?> id = SnowFlakeUtils.getId(Long.parseLong("123"));
//        book.setId(Long.parseLong(id.toString()));
        Long id = worker.nextId();
        book.setId(id);
        Long count = redisTemplate.opsForValue().increment("count");
        String name = "mlq" + count;
        book.setName(name);
        book.setCount(Math.toIntExact(count));
        return bookService.save(book);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public IPage<Book> page(Integer pageNum, Integer pageSize) {
        Page<Book> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("name", "孟令乾");
        queryWrapper.orderByAsc("id");
        IPage<Book> res = bookService.page(page);
        return res;
    }

}
