package com.example.blog_website.Controllers;

import com.example.blog_website.Entity.BlogEntity;
import com.example.blog_website.Repository.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/blog")
public class GetController {
    Database database;

    @Autowired
    GetController(Database database){
        this.database = database;
    }

    @GetMapping("/{id}")
    public BlogEntity getBlogById(@PathVariable("id") int index){
        return database.getBlog().get(index);
    }

    @GetMapping()
    public ArrayList<BlogEntity> getBlog(){
        return database.getBlog();
    }


}
