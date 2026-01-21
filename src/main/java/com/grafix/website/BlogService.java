package com.grafix.website;

import com.grafix.website.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.data.domain.Sort;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public List<BlogPost> getAllPosts() {
        return blogRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public void addPost(BlogPost post) {
        blogRepository.save(post);
    }

    public void deletePost(Long id) {
        blogRepository.deleteById(id);
    }

    public BlogPost getPostById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }
}
