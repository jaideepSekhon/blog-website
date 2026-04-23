package com.example.blog_website.Controllers;


import com.example.blog_website.Entity.BlogEntity;
import com.example.blog_website.Repository.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog")
public class PutController {
    Database database;

    @Autowired
    PutController(Database database){
        this.database = database;
    }

    @PutMapping("/{id}")
    public String updateBlog(@PathVariable("id") int index, @RequestBody BlogEntity blog){
        String title=blog.getTitle();
        String content=blog.getContent();
        database.updateBlog(index,title,content);
        return "Blog Updated Successfully";
    }
}
