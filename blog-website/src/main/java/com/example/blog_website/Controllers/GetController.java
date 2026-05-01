package com.example.blog_website.Controllers;

import com.example.blog_website.Entity.BlogEntity;
import com.example.blog_website.Repository.DBConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.Connection;
import java.util.ArrayList;

@RestController
@RequestMapping("/blog")
@CrossOrigin(origins = "*")
public class GetController {
    DBConnection database;
    Connection conn;

    @Autowired
    GetController(DBConnection database){
        this.database = database;
        this.conn = database.getConnection();
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
