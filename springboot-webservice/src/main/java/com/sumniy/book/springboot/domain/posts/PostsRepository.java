package com.sumniy.book.springboot.domain.posts;


import org.springframework.data.jpa.repository.JpaRepository;

// Posts 클래스로 DB를 접근하게 해주는 JpaRepository
public interface PostsRepository extends JpaRepository<Posts, Long> {



}
