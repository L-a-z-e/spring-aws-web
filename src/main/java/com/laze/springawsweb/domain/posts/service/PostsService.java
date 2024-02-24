package com.laze.springawsweb.domain.posts.service;

import com.laze.springawsweb.domain.posts.Posts;
import com.laze.springawsweb.domain.posts.PostsRepository;
import com.laze.springawsweb.web.dto.PostsListResponseDto;
import com.laze.springawsweb.web.dto.PostsResponseDto;
import com.laze.springawsweb.web.dto.PostsSaveRequestDto;
import com.laze.springawsweb.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. 게시글 id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }
    @Transactional
    public Long delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. 게시글 id=" + id));
        postsRepository.delete(posts);

        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. 게시글 id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)  // 조회 기능만 남겨두기때문에 조회속도 개선
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc()
                .stream()
                .map(PostsListResponseDto::new) // .map(posts -> new PostsListResponseDto(posts)) 와 동일
                .collect(Collectors.toList());
    }
}
