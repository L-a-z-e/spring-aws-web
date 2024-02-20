package com.laze.springawsweb.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<entity-class, pk-type>
// Entity Class 와 기본 Entity Repository는 함께 위치해야함
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
