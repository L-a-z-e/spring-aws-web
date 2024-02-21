package com.laze.springawsweb.web.controller;

import com.laze.springawsweb.domain.posts.Posts;
import com.laze.springawsweb.domain.posts.PostsRepository;
import com.laze.springawsweb.web.dto.PostsSaveRequestDto;
import com.laze.springawsweb.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class) //@RunWith(SpringRunner.class) 어노테이션은 JUnit에게 이 테스트 코드는 Spring Runner를 사용하여 실행하도록 지시 Spring Runner는 Spring의 테스트 컨텍스트(TestContext)를 관리하고, Spring의 의존성 주입(Dependency Injection) 기능을 JUnit 테스트에서 사용할 수 있게 해줍
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // @SpringBootTest 어노테이션은 Spring Boot의 슬라이스 테스트(Slice Test) 대신에 전체 애플리케이션 컨텍스트를 로드하는 통합 테스트를 위한 어노테이션입니다. webEnvironment 속성으로 테스트에 사용할 웹 환경을 설정할 수 있음
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void PostsSave() throws Exception {

        //given
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto
                .builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);

    }

    @Test
    public void PostsUpdate() throws Exception {
        //given
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build()
        );

        Long updateId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        // HttpEntity<T> 에서 PostsUpdateRequestDto 타입
        // public HttpEntity(T body) { /* compiled code */ } requestDto 를 HTTP 요청 본문에 담는부분
        // { "title": "title2", "content": "content2" } JSON 형태로 변환 > HttpEntity의 Body 부분에 담음
        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }
}