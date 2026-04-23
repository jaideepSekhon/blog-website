package com.example.blog_website.Repository;

import com.example.blog_website.Entity.BlogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.ArrayList;

@Component
public class Database {

    DBConnection  connection;
    ArrayList<BlogEntity> blogEntities=new ArrayList<>();

    @Autowired
    Database(DBConnection connection) {
        this.connection=connection;
    }

    public  ArrayList<BlogEntity> getBlog() {
        return blogEntities;
    }

    public void addBlog(String title, String content) {
        BlogEntity blog=new BlogEntity();
        blog.setTitle(title);
        blog.setContent(content);
        blogEntities.add(blog);
        System.out.println("Blog successfully added");
    }

    public void deleteBlog(int index) {
        blogEntities.remove(index);
        System.out.println("Blog successfully deleted");
    }

    public void updateBlog(int index,String title,String content){
        BlogEntity blog=getBlog().get(index);
        blog.setTitle(title);
        blog.setContent(content);

//        blogEntities.add(index,blog);
        System.out.println("Blog successfully updated");

    }
}
