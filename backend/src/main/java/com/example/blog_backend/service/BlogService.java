package com.example.blog_backend.service;

import com.example.blog_backend.model.Blog;
import com.example.blog_backend.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public Optional<Blog> getBlogById(Long id) {
        return blogRepository.findById(id);
    }

    public Blog createBlog(Blog blog) {
        if(blog.getCreatedAt() == null) {
            blog.setCreatedAt(LocalDateTime.now());
        }
        return blogRepository.save(blog);
    }

    public Blog updateBlog(Long id, Blog blogDetails) {
        return blogRepository.findById(id).map(blog -> {
            blog.setTitle(blogDetails.getTitle());
            blog.setContent(blogDetails.getContent());
            return blogRepository.save(blog);
        }).orElseThrow(() -> new RuntimeException("Blog not found with id " + id));
    }

    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
