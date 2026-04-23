package com.example.blog_website.Controllers;

import com.example.blog_website.Repository.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
public class DeleteController {
    Database database;

    @Autowired
    public DeleteController(Database database) {
        this.database = database;
    }

    @DeleteMapping("/{id}")
    public String deleteBlog(@PathVariable("id") int index){
        database.deleteBlog(index);
        return "Blog successfully deleted";
    }
}
