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
public class PostController {
    DBConnection database;
    Connection conn;

    @Autowired
    PostController(DBConnection database){
        this.database = database;
        this.conn = database.getConnection();
    }

    @PostMapping()
    public String post(@RequestBody BlogEntity blogEntity) {
        String title=blogEntity.getTitle();
        String content=blogEntity.getContent();

        return database.addBlog(title,content);
    }
}
