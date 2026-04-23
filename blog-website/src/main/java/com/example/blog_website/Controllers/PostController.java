package com.example.blog_website.Controllers;

import com.example.blog_website.Entity.BlogEntity;
import com.example.blog_website.Repository.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/blog")
public class PostController {
    public Database database;

    @Autowired
    PostController(Database database){
        this.database = database;
    }

    @PostMapping()
    public String post(@RequestBody BlogEntity blogEntity) {
        String title=blogEntity.getTitle();
        String content=blogEntity.getContent();

        database.addBlog(title,content);
        return "Blog successfully added";
    }
}
