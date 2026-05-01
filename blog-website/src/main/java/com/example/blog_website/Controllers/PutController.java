package com.example.blog_website.Controllers;


import com.example.blog_website.Entity.BlogEntity;
import com.example.blog_website.Repository.DBConnection;
import org.hibernate.boot.model.relational.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.Connection;

@RestController
@RequestMapping("/blog")
@CrossOrigin(origins = "*")
public class PutController {
    DBConnection database;
    Connection conn;

    @Autowired
    PutController(DBConnection database){
        this.database = database;
        this.conn = database.getConnection();
    }

    @PutMapping("/{id}")
    public String updateBlog(@PathVariable("id") int index, @RequestBody BlogEntity blog){
        String title=blog.getTitle();
        String content=blog.getContent();
        return database.updateBlog(index,title,content);

    }
}
