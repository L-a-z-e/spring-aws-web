package com.laze.springawsweb.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    // JUnit에서 단위 테스트가 끝날 때마다 수행되는 메서드 지정
    // 배포 전 전체 테스트 수행시 테스트간 데이터 침범을 막기위해 사용
    // H2에 데이터가 그대로 남아있어서 다음 테스트에 영향을 주는 경우를 방지
    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void posts_read(){

        //given
        String title = "테스트게시글";
        String content = "테스트 본문";

        // table posts에 insert / update 쿼리 실행
        // id o -> update / id x -> insert
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("Laze")
                .build()
        );

        //when
        // table posts의 모든 데이터를 조회해오는 메서드
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title); // import 오류가 좀 많은 것 같은데 org.assertj.core.api.Assertions.assertThat;
        assertThat(posts.getContent()).isEqualTo(content);

    }
}