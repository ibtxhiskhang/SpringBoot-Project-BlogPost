package com.win.blogapp.blogpost;

import org.springframework.data.repository.CrudRepository;

public interface BlogPostRepo extends CrudRepository<BlogPost, Long> {
    
}