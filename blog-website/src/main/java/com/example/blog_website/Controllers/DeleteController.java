package com.example.blog_website.Controllers;

import com.example.blog_website.Repository.DBConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.Connection;

@RestController
@RequestMapping("/blog")
@CrossOrigin(origins = "*")
public class DeleteController {
    DBConnection database;
    Connection conn;

    @Autowired
    public DeleteController(DBConnection database) {
        this.database = database;
        this.conn = database.getConnection();
    }

    @DeleteMapping("/{id}")
    public String deleteBlog(@PathVariable("id") int index){
        return database.deleteBlog(index);
    }
}
