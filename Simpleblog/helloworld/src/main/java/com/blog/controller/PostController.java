package com.blog.controller;

import com.blog.vo.Result;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.blog.service.PostService;
import com.blog.vo.Post;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("/post")
    public Post getPost(@RequestParam("id") Long id) {
        Post post = postService.getPost(id);
        return post;
    }

    @GetMapping("/posts")
    public List<Post> getPosts() {
        List<Post> posts = postService.getPosts();
        return posts;
    }

    @GetMapping("/post/{id}")
    public Post getPostPathParam(@PathVariable("id") Long id){
        Post post = postService.getPost(id);
        return post;
    }

    @GetMapping("/posts/search/title")
    public List<Post> searchByTitle(@RequestParam("query") String query) {
        List<Post> posts = postService.searchPostByTitle(query);
        return posts;
    }

    @PostMapping("/post")
    public Object savePost(HttpServletResponse response, @RequestBody Post postParam){
        Post post = new Post(postParam.getUser(), postParam.getTitle(), postParam.getContent());
        boolean isSuccess = postService.savePost(post);

        if(isSuccess){
            return new Result(200, "Success");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return new Result(500, "Fail");
        }
    }
}
