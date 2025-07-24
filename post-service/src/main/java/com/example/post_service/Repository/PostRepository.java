package com.example.post_service.Repository;

import com.example.post_service.Model.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByUserId(Long userId);
    Optional<Post> findFirstByUserId(Long userId);

}
