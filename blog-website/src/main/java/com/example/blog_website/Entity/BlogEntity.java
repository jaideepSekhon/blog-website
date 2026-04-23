package com.example.blog_website.Entity;

public class BlogEntity {

    public String title;
    public String content;

    public BlogEntity(){
        this.title="Default Title";
        this.content="Default Content";
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title=title;
    }

    public void setContent(String content) {
        this.content=content;
    }
}
