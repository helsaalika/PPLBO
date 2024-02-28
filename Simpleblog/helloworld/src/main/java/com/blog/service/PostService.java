package com.blog.service;

import com.blog.repository.PostJpaRepository;
import com.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

import com.blog.vo.Post;

@Service
public class PostService {
    private static List<Post> posts;

    @Autowired
    PostRepository postRepository;

    @Autowired
    private PostJpaRepository jpaRepository;

    public Post getPost(Long id) {
        Post post = jpaRepository.findOneById(id);
        return post;
    }

    public List<Post> getPosts() {
        List<Post> postList = jpaRepository.findAllByOrderByUpdtDateDesc();
        return postList;
    }

    public List<Post> searchPostByTitle(String query){
        List<Post> posts = postRepository.findPostLikeTitle(query);
        return posts;
    }

    public boolean savePost(Post post) {
        Post result = jpaRepository.save(post);
        boolean isSuccess = true;

        if(result == null){
            isSuccess = false;
        }

        return  isSuccess;
    }
}
